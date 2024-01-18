package com.cy.store.service;

import com.cy.store.entity.User;
import com.cy.store.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

// @RunWith(SpringRunner.class)表示啟動這個單元測試(正常單元測試是不能用的), 需要傳一個SpringRunner型別的參數
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTests {
    @Autowired
    private IUserService userService;

//----------------------------------------------------------------//

    @Test
    public void reg() {
        try {
            User user = new User();
            user.setUsername("test02");
            user.setPassword("123");
            userService.reg(user);
            System.out.println("OK");
        } catch (ServiceException e) {
            //得到異常對象的名稱
            System.out.println(e.getClass().getSimpleName());
            //得到異常的訊息
            System.out.println(e.getMessage());
        }
    }
//----------------------------------------------------------------//

    @Test
    public void login() {

        User user = userService.login("test01", "123");
        System.out.println(user);
    }

    //----------------------------------------------------------------//
    @Test
    public void changePassword() {
        userService.changePassword(2, "改密碼測試", "321", "123");

    }
//----------------------------------------------------------------//

    @Test
    public void getByUid() {
//        Integer uid = 7;
//        User user = userService.getByUid(uid);
//        System.out.println(user);
        System.out.println(userService.getByUid(7));
    }

    //----------------------------------------------//

    @Test
    public void changeInfo() {
        User user = new User();
        user.setPhone("09876543210");
        user.setEmail("changeInfoTest@gmail.com");
        user.setGender(0);
        userService.changeInfo(2, "新增資料測試", user);
    }

    //----------------------------------------------------------------//
//    @Test
//    public void changeAvatar() throws IOException {
//
//        // 讀取圖片內容的 FileInputStream
//        FileInputStream fis = new FileInputStream("/image/avatar/icons8-2.png");
//
//        // 將圖片內容讀取到 byte 陣列
//        byte[] avatar = new byte[fis.available()];
//        fis.read(avatar);
//
//        Integer uid = 1;
//        String username = "Service測試";
//
//
//        // 調用 userService.changeAvatar() 方法
//        userService.changeAvatar(uid, avatar, username);
//
//        // 在這裡可以添加斷言或其他驗證步驟，確認頭像是否成功更改
//        // 例如，可以調用 userService.getAvatarByUid() 方法檢索頭像資料並驗證其正確性
//
//        fis.close();

    }


//    @Test
//    public void changeAvatar(){
//        userService.changeAvatar(
//                7,
//                "/image/avatar/icons8-2.png",
//                "Service測試");
//    }

//----------------------------------------------------------------//



