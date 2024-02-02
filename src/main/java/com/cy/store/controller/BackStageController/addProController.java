package com.cy.store.controller.BackStageController;

import com.cy.store.controller.BassController;
import com.cy.store.util.FileUploadUtil;
import com.cy.store.util.JsonResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("product")
public class addProController extends BassController {
    @RequestMapping("/add")
    public JsonResult<String> changeProImage(
            @RequestParam("file") MultipartFile file) {

        String filePath = FileUploadUtil.uploadFile(file);

        return new JsonResult<>(OK, "成功新增產品圖片,路徑為: " + filePath);
    }

}
