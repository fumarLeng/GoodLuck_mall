package com.cy.store.mapper;

import com.cy.store.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;


//會員資料
public interface UserMapper {
//----------------------------------------------//

    /**
     * 新增使用者數據
     *
     * @param user 會員
     * @return 產生多少筆資料
     */
    Integer insert(User user);

//----------------------------------------------//

    /**
     * 根據會員名稱查詢會員資料
     *
     * @param username 會員名稱
     * @return 產生多少筆資料
     */
    User findByUsername(String username);


    //----------------------------------------------//

    /**
     * 根據會員uid來修改密碼
     *
     * @param uid          會員id
     * @param password     會員輸入的新密碼
     * @param modifiedUser 修改人
     * @param modifiedTime 修改時間
     * @return 被更改的資料筆數
     */
    Integer updatePasswordByUid(Integer uid,
                                String password,
                                String modifiedUser,
                                Date modifiedTime);

//----------------------------------------------//

    /**
     * 根據會員id查詢會員資料
     *
     * @param uid 會員id
     * @return 有找到就回傳, 沒有就回傳null
     */
    User findByUid(Integer uid);

//----------------------------------------------//

    /**
     * 更新會員資料
     * @param user 會員的參數
     * @return 回傳值是更新的筆數
     */
   Integer updateInfoByUid(User user);

//----------------------------------------------//
    //
    /**
     * Param
     * 如果xml裡面的SQL語法#{}跟我們要對應的介面方法參數不一樣
     * 這個Param會將設定的參數注入到xml對應的#{}裡面
     * 根據會員uid更改會員大頭貼
     * @param uid 會員uid
     * @param avatar 會員大頭貼
     * @param modifiedUser  更改者
     * @param modifiedTime  更改時間
     * @return
     */
   Integer updateAvatarByUid(
           @Param("uid")Integer uid,
           @Param("avatar") byte[] avatar,
           @Param("modifiedUser") String modifiedUser,
           @Param("modifiedTime")Date modifiedTime);


}