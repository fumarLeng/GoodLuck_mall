package com.cy.store.controller;

import com.cy.store.controller.ex.*;
import com.cy.store.entity.User;
import com.cy.store.service.IUserService;
import com.cy.store.util.JsonResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;


@RestController // 等於 @Component + @ResponseBody
@RequestMapping("users")
public class UserController extends BassController {

    @Autowired
    private IUserService userService;

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

//        if (data.getAvatar() != null) {
//            // 將String轉換為 Base64 編碼字符串
//            String avatar64 = Base64.getEncoder().encodeToString(data.getAvatar());
//
//            // 設置 Base64 到用戶的 avatar 屬性
//            data.setAvatar64(avatar64);
//        }

         // 把數據放到session裡面
        session.setAttribute("uid", data.getUid());
        session.setAttribute("username", data.getUsername());
        session.setAttribute("avatar",data.getAvatar());
        session.setAttribute("gender",data.getGender());

        //測試拿不拿的到session裡面的數據
//        System.out.println(getuidFromSession(session));
//        System.out.println(getUsernameFromSession(session));

        return new JsonResult<User>(OK, data);
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
//
//    //接到前端檔案的方法, MultipartFile file 對應到前端上傳表單的 name 屬性名稱要一致
//    //  <input type="file" name="file">
//    // 如果出現前後端不一致使用@RequestParam("")來解決 裡面放前端表單的name屬性名
//
//    //限制檔案上傳的大小
//    public static final int AVATAR_MAX_SIZE = 10 * 1024 * 1024;
//    //限制上傳檔案類型,放在集合裡面
//    public static final List<String> AVATAR_TYPE = new ArrayList<>();
//
//    static {
//        AVATAR_TYPE.add("image/jpeg");
//        AVATAR_TYPE.add("image/png");
//        AVATAR_TYPE.add("image/bmp");
//        AVATAR_TYPE.add("image/gif");
//    }
//
//    /**
//     * MultipartFile是springMVC提供的介面,可以用來接收任何類型的檔案
//     * Springboot整合了SpringMVC 所以處理請求的參數上用這個參數類型
//     * Springboot會自動將前端來的數據傳給這個參數類型的變數
//     * //@RequestParam("")  裡面放前端表單的name屬性名
//     */
//    @RequestMapping("change_avatar")
//    public JsonResult<String> changeAvatar(
//            @RequestParam("file") MultipartFile file,
//            HttpSession session) {
//        //先判斷檔案是不是空值
//        if (file.isEmpty()) {
//            throw new FileEmptyException("沒有檔案");
//        }
//
//        //判斷檔案有沒有超過大小限制
//        if (file.getSize() > AVATAR_MAX_SIZE) {
//            throw new FileSizeException("檔案大小超過限制");
//        }
//
//        //判斷文件類型
//        String contentType = file.getContentType();
//        //contains是集合裡面的方法,
//        //判斷AVATAR_TYPE集合裡面有沒有包含contentType傳進來的類型
//        //有包含回傳true,不包含丟異常
//        if (!AVATAR_TYPE.contains(contentType)) {
//            throw new FileTypeException("檔案類型錯誤");
//        }
//
//        //上傳的檔案存放位置.../upload/檔案.png
//        //獲取在Servlet環境中取得名為 "upload" 的相對路徑在實際檔案系統中的真實路徑
//        String parent = session.getServletContext().getRealPath("upload");
//        System.out.println(parent);
//        //檢查File要放的這個upload資料夾存不存在,不存在就創建
//        File dir = new File(parent);
//        if (!dir.exists()) {
//            dir.mkdirs();
//        }
//
//        //得到的檔案名稱,放到upload資料夾裡面時要改名避免重複檔名
//        //用UUID方法來生成新的檔案名稱
//
//        String originalFilename = file.getOriginalFilename();
//        System.out.println("originalFilename" + originalFilename);
//
//        // 取得檔案名稱中最後一個點（.）之後的部分，就是副檔名
//        int index = originalFilename.lastIndexOf(".");
//        String suffix = originalFilename.substring(index);
//        // 亂數產生的大寫英數字變成檔案存放的名稱,後面在 + suffix 裡面的副檔名
//        String filename =
//                UUID.randomUUID().toString().toUpperCase() + suffix;
//
//        // 先在創建的upload資料夾裡面新增一個filename變數裡面的(亂數名稱+副檔名)檔案
//
//        File dest = new File(dir, filename); //空檔案
//
//        // 然後再寫入把前端傳來的檔案資料流( file )寫入這個空檔案中
//
//        try {
//            // MultipartFile 裡面的方法 transferTo
//            // 把file檔案裡面的資料寫入到dest中
//            // 副檔名要一致,不然會失敗
//            file.transferTo(dest);
//        } catch (FileStateException e) {
//            throw new FileStateException("檔案狀態異常");
//        } catch (IOException e) {
//            throw new FileUploadIOException("檔案讀取異常");
//        }
//
//        Integer uid = getuidFromSession(session);
//        String username = getUsernameFromSession(session);
//
//        //回傳大頭貼的路徑,相對路徑  /upload/XXX.png...
//        String avatar = "/upload/" + filename;
//        userService.changeAvatar(uid, avatar, username);
//        return new JsonResult<>(OK, avatar);
//    }


//------------------------------------------------------------------------//


}
