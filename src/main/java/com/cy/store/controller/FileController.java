package com.cy.store.controller;

import com.cy.store.controller.ex.*;
import com.cy.store.util.JsonResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("files")
public class FileController extends BassController {
    //接到前端檔案的方法, MultipartFile file 對應到前端上傳表單的 name 屬性名稱要一致
    //  <input type="file" name="file">
    // 如果出現前後端不一致使用@RequestParam("")來解決 裡面放前端表單的name屬性名

    //限制檔案上傳的大小
    public static final int AVATAR_MAX_SIZE = 100 * 1024 * 1024;
    //限制上傳檔案類型,放在集合裡面
    public static final List<String> AVATAR_TYPE = new ArrayList<>();
    static {
        AVATAR_TYPE.add("image/jpeg");
        AVATAR_TYPE.add("image/png");
        AVATAR_TYPE.add("image/bmp");
        AVATAR_TYPE.add("image/gif");
    }
    /**
     * MultipartFile是springMVC提供的介面,可以用來接收任何類型的檔案
     * Springboot整合了SpringMVC 所以處理請求的參數上用這個參數類型
     * Springboot會自動將前端來的數據傳給這個參數類型的變數
     * //@RequestParam("")  裡面放前端表單的name屬性名
     */
    @RequestMapping("/fileUp")
    public JsonResult<String> changeProImage(
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

        String parent = "C:\\Users\\T14 Gen 3\\Desktop\\GoodLuck_mall\\src\\main\\resources\\static\\images\\portal";
        System.out.println(parent);
        //檢查File要放的這個upload資料夾存不存在,不存在就創建
        File dir = new File(parent);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String originalFilename = file.getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        System.out.println("originalFilename" + originalFilename);

        String randomDir = UUID.randomUUID().toString();
        File parentDir = new File("C:\\upload", randomDir);
        if (!parentDir.exists()) {
            parentDir.mkdirs();
        }

        String filename = "collect" + extension;
        File dest = new File(parentDir, filename); //新檔案的儲存路徑

        try {
            // MultipartFile 裡面的方法 transferTo
            // 把file檔案裡面的資料寫入到dest中
            // 副檔名要一致,不然會失敗
            System.out.println("File stored at: " + dest.getAbsolutePath());
            file.transferTo(dest);
        } catch (FileStateException e) {
            throw new FileStateException("檔案狀態異常");
        } catch (IOException e) {
            throw new FileUploadIOException("檔案讀取異常");
        }

        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSession(session);

        //回傳路徑,相對路徑  /upload/XXX.png...
        String filePath = "/upload/" + randomDir + "/" + filename;
        return new JsonResult<>(OK, filePath);
    }

}
