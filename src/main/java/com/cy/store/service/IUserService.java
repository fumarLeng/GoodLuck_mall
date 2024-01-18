package com.cy.store.service;

import com.cy.store.entity.User;
import org.apache.ibatis.annotations.Param;

// 會員功能業務邏輯層介面

public interface IUserService {

    /**
     *  會員註冊方法
     * @param user 會員的資料
     */
    void reg(User user);
//--------------------------------------------------------//

    /**
     *  會員登錄功能
     * @param username 會員名稱
     * @param password 會員密碼
     * @return 當前收到的會員資料 如果沒有就返回null
     */
   User login(String username,String password);
//--------------------------------------------------------//

    /**
     * 更改密碼功能
     * @param uid 會員id
     * @param username 會員名稱
     * @param oldPassword 舊密碼
     * @param newPassword 新密碼
     */
    void changePassword(Integer uid,
                         String username,
                         String oldPassword,
                         String newPassword);
//--------------------------------------------------------//

    /**
     * 用id查詢會員的資料
     * @param uid 會員id
     * @return 會員資料
     */
    User getByUid(Integer uid);
//--------------------------------------------------------//

    /**
     * 更新會員資料操作
     * @param uid 會員id
     * @param username 會員名稱
     * @param user 會員的資料
     */
    void changeInfo(Integer uid, String username,User user);
//--------------------------------------------------------//

    /**
     * 修改會員大頭貼
     * @param uid 會員id
     * @param avatar 會員大頭貼
     * @param username 會員名稱
     */
    void changeAvatar( Integer uid,
                       byte[] avatar,
                       String username);
    //--------------------------------------------------------//







}
