package com.cy.store.controller;

import com.cy.store.controller.ex.*;
import com.cy.store.entity.User;
import com.cy.store.service.IUserService;
import com.cy.store.service.impl.CaptchaService;
import com.cy.store.util.JsonResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

import java.io.File;
import java.io.IOException;
import java.util.*;


@RestController // 等於 @Component + @ResponseBody
@RequestMapping("users")
public class UserController extends BassController {

    @Autowired
    private IUserService userService;
    @Autowired
    private CaptchaService captchaService;



    //-----------------------------註冊-------------------------------------------//
    @RequestMapping("reg")
    //方法的回傳結果以json格式進行回傳給前端
    public JsonResult<Void> reg(User user) {
        userService.reg(user);
        return new JsonResult<Void>(OK);
    }
//---------------------------------登入---------------------------------------//

    @RequestMapping("login")
    public JsonResult<User> login(String username,
                                  String password,
                                  HttpSession session) {
        User data = userService.login(username, password);

         // 把數據放到session裡面
        session.setAttribute("uid", data.getUid());
        session.setAttribute("username", data.getUsername());
        session.setAttribute("avatar",data.getAvatar());
        session.setAttribute("gender",data.getGender());


        return new JsonResult<User>(OK, data);
    }


//    驗證碼
    @RequestMapping("/captcha")
    public JsonResult<Map<String,String>> generateCaptcha() {
    // 调用service生成验证码
        Map<String,String> captchaData  = captchaService.createCaptcha();
        System.out.println(captchaData);
     return new JsonResult<>(OK,captchaData);
}

//------------------------------登出------------------------------------------//

    @RequestMapping("logout")
    public JsonResult<Void> logout(HttpSession session){
        session.invalidate();
        return new JsonResult<Void>(OK);
    }


//------------------------------更改密碼------------------------------------------//

    @RequestMapping("change_password")
    public JsonResult<Void> changePassword(String oldPassword,
                                           String newPassword,
                                           HttpSession session) {
        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSession(session);
        userService.changePassword(uid, username, oldPassword, newPassword);
        return new JsonResult<>(OK);
    }
//------------------------------------------------------------------------//

    //設定一打開頁面就送出查詢當前uid的請求
    @RequestMapping("get_by_uid")
    public JsonResult<User> getByUid(HttpSession session) {
        User data = userService.getByUid(getuidFromSession(session));


//--------------------------------圖片---------------------------------------
        session.setAttribute("avatar",data.getAvatar());
//--------------------------------圖片---------------------------------------
        return new JsonResult<>(OK, data);
    }
//-----------------------------更改個人資料-------------------------------------------//

    //會員點擊更新資料按鈕連到這裡
    @RequestMapping("change_info")
    public JsonResult<Void> changeInfo(User user,
                                       HttpSession session) {
        //User物件裡面原本有:username,phone,email,gender
        //沒有uid在另外從session找出來放到user物件中

        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSession(session);
        userService.changeInfo(uid, username, user);
        return new JsonResult<>(OK);
    }

//------------------------------------------------------------------------//
//測試控制器方法


    //限制檔案上傳的大小
    public static final int AVATAR_MAX_SIZE = 10 * 1024 * 1024;
    //限制上傳檔案類型,放在集合裡面
    public static final List<String> AVATAR_TYPE = new ArrayList<>();

    static {
        AVATAR_TYPE.add("image/jpeg");
        AVATAR_TYPE.add("image/png");
        AVATAR_TYPE.add("image/bmp");
        AVATAR_TYPE.add("image/gif");
    }

    @RequestMapping("change_avatar")
    public JsonResult<String> changeAvatar(
            @RequestParam("file") MultipartFile file,
            HttpSession session) {
        //先判斷檔案是不是空值
        if (file.isEmpty()) {
            throw new FileEmptyException("沒有檔案");
        }

        //判斷檔案有沒有超過大小限制
        if (file.getSize() > AVATAR_MAX_SIZE) {
            throw new FileSizeException("檔案大小超過限制");
        }

        //判斷文件類型
        String contentType = file.getContentType();
        //contains是集合裡面的方法,
        //判斷AVATAR_TYPE集合裡面有沒有包含contentType傳進來的類型
        //有包含回傳true,不包含丟異常
        if (!AVATAR_TYPE.contains(contentType)) {
            throw new FileTypeException("檔案類型錯誤");
        }
        try {
            byte[] fileBytes = file.getBytes();
            Integer uid = getuidFromSession(session);
            String username = getUsernameFromSession(session);

            // 呼叫service將圖片存到資料庫的 BLOB 欄位
            userService.changeAvatar(uid, fileBytes, username);

            // 將圖片的資料轉換為 Base64 字串
            String avatar = Base64.getEncoder().encodeToString(fileBytes);
            return new JsonResult<>(OK, avatar);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return new JsonResult<>();

    }


//------------------------------------------------------------------------//


//------------------------------------------------------------------------//


}
