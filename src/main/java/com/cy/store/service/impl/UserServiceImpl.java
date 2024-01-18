package com.cy.store.service.impl;

import com.cy.store.entity.User;
import com.cy.store.mapper.UserMapper;
import com.cy.store.service.IUserService;
import com.cy.store.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.sql.Blob;
import java.util.Date;
import java.util.UUID;

/* 會員功能業務邏輯層的實作 */
@Service //Service註解 : 將當前的類別交給Spring管理, 自動實體化跟維護
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    //------------------------------------------------------------------------------------------------------//
    @Override
    public void reg(User user) {
        //通過user參數拿到傳過來的username
        String username = user.getUsername();

        // 使用findByUsername(username) 判斷會員有沒有被註冊過
        User result = userMapper.findByUsername(username);

        // 判斷回傳結果是否為null, 不為null就丟出會員名稱已被使用的異常
        if (result != null) {
            //丟出異常
            throw new UsernameDuplicateException("已註冊過的會員名稱");
        }

        //密碼加密實作 : MD5方式
        //( 隨機字串(鹽) + 密碼 + 隨機字串(鹽) )----再交由MD5連續加載三次
        String oldPassword = user.getPassword();

        //得到鹽值(隨機生成)來加密
        String salt = UUID.randomUUID().toString().toUpperCase();

        //補全數據 ,鹽值記錄到SQL
        user.setSalt(salt);

        //再把密碼跟鹽值弄成一個整體進行加密, 忽略原有密碼提升數據的安全性
        String md5Password = getMd5Password(oldPassword, salt);

        //將加密之後的密碼重新設定到user類別中
        user.setPassword(md5Password);

        // 補齊數據 is_delete 設定成0
        user.setIsDelete(0);
        // 補齊數據 4個紀錄的屬性
        user.setCreatedUser(user.getUsername());
        user.setModifiedUser(user.getUsername());
        Date date = new Date();
        user.setCreatedTime(date);
        user.setModifiedTime(date);


        //執行註冊業務功能的實作 (rows == 1)
        Integer rows = userMapper.insert(user);
        if (rows != 1) {
            throw new InsertException("在註冊會員時產生未知的錯誤");
        }


    }
//------------------------------------------------------------------------------------------------------//

    @Override
    public User login(String username, String password) {

        //呼叫 mapper 層的 findByUsername 方法
        //在根據username查詢會員是否存在, 不再就丟異常
        User result = userMapper.findByUsername(username);

        if (result == null) {
            throw new UserNotFoundException("會員不存在");
        }
        //判斷會員有沒有被停權 is_delete  1等於停權
        if (result.getIsDelete() == 1) {
            throw new UserNotFoundException("會員不存在");
        }


        //在和User傳過來的密碼對比
        //先取得鹽值,上次註冊薪增到資料庫的鹽值
        String salt = result.getSalt();

        //將User傳的密碼按照一樣的md5算法進行加密
        String md5Password = getMd5Password(password, salt);
        //密碼比對有沒有相同
        if (!result.getPassword().equals(md5Password)) {
            throw new PasswordNotMatchException("密碼錯誤");
        }


        //這樣把一層到一層之間傳輸的數據變少了
        //例如登錄之後的頁面上方顯示只需要這些數據
        User user = new User();
        user.setUid(result.getUid());
        user.setUsername(result.getUsername());
        //回傳用戶頭像
        user.setAvatar(result.getAvatar());

        //在把資料回傳給user, 為了之後數據能帶到其他頁面使用
        return user;
    }
//------------------------------------------------------------------------------------------------------//
@Override
public void changePassword(Integer uid,
                           String username,
                           String oldPassword,
                           String newPassword) {
    //更改密碼先檢查會員有沒有被停權
    User result = userMapper.findByUid(uid);
    if (result == null || result.getIsDelete() == 1) {
        throw new UserNotFoundException("會員不存在");
    }
    //開始比對舊密碼跟新密碼
    String oldMd5Password =
            getMd5Password(oldPassword, result.getSalt());
    if (!result.getPassword().equals(oldMd5Password)) {
        throw new PasswordNotMatchException("密碼錯誤");
    }
    //再把新密碼加密之後更改
    String newMd5Password = getMd5Password(newPassword, result.getSalt());
    Integer rows = userMapper.updatePasswordByUid(uid, newMd5Password, username, new Date());
    if (rows != 1) {
        throw new UpdateException("更新時產生未知的錯誤");
    }

}
//------------------------------------------------------------------------------------------------------//

    @Override
    public User getByUid(Integer uid) {
        User result = userMapper.findByUid(uid);
        if (result == null || result.getIsDelete() == 1) {
            throw new UserNotFoundException("會員不存在");
        }
        //new 一個新的User實體
        //把上面查到的的username/phone/email/gender
        //都放進去
        User user = new User();
        user.setUsername(result.getUsername());
        user.setPhone(result.getPhone());
        user.setEmail(result.getEmail());
//----------------------圖片------------------------------------
        user.setAvatar(result.getAvatar());
//--------------------------------圖片---------------------------------------

        return user;
    }

//------------------------------------------------------------------------------------------------------//

    /**
     * user物件中的數據只有phone/email/gender
     * 要在手動將uid/username封裝進去user物件
     */
    @Override
    public void changeInfo(Integer uid, String username, User user) {
        User result = userMapper.findByUid(uid);
        if (result == null || result.getIsDelete() == 1) {
            throw new UserNotFoundException("會員不存在");
        }
        user.setUid(uid);
//        user.setUsername(username);
        user.setModifiedUser(username);
        user.setModifiedTime(new Date());

        Integer rows = userMapper.updateInfoByUid(user);
        if (rows != 1) {
            throw new UpdateException("更新時產生未知的錯誤");

        }
    }
//------------------------------------------------------------------------------------------------------//
//    頭像方法測試
public void changeAvatar(Integer uid, byte[] avatar,String username) {
    User result = userMapper.findByUid(uid);
    if (result == null || result.getIsDelete() == 1) {
        throw new UserNotFoundException("會員不存在");
    }


        Integer rows = userMapper.updateAvatarByUid(uid, avatar, username  , new Date());
        if (rows != 1) {
            throw new UpdateException("更新會員大頭貼時產生未知的異常");
        }

}


//------------------------------------------------------------------------------------------------------//
//
//
//    @Override
//    public void changeAvatar(Integer uid, String avatar, String username) {
//
//        //先查會員數據再不再
//        User result = userMapper.findByUid(uid);
//        if (result == null || result.getIsDelete() == 1) {
//            throw new UserNotFoundException("會員不存在");
//        }
//
//        Integer rows = userMapper.updateAvatarByUid(uid, avatar, username, new Date());
//        if (rows != 1) {
//            throw new UpdateException("更新會員大頭貼時產生未知的異常");
//        }
//
//    }

//------------------------------------------------------------------------------------------------------//

    /**
     * md5加密方法
     **/
    private String getMd5Password(String password, String salt) {
        for (int i = 0; i < 3; i++) {
            //md5加密算法方法在轉成大寫, 然後連續加密三次
            password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toUpperCase();

        }
        //回傳加密之後的密碼
        return password;

    }
//------------------------------------------------------------------------------------------------------//


}
