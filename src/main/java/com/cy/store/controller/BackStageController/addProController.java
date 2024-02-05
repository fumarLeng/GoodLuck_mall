package com.cy.store.controller.BackStageController;

import com.cy.store.controller.BassController;
import com.cy.store.entity.Product;
import com.cy.store.service.BackStageService.BSaddProductService;
import com.cy.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequestMapping("product")
public class addProController extends BassController {

    @Autowired
    private BSaddProductService BSaddProductService;
    @RequestMapping("/add")
    public JsonResult<String> addProduct(
            @RequestParam("categoryId") Integer categoryId,
            @RequestParam("itemType") String itemType,
            @RequestParam("title") String title,
            @RequestParam("sellPoint") String sellPoint,
            @RequestParam("price") Long price,
            @RequestParam("num") Integer num,
            @RequestParam("file") MultipartFile file,
            @RequestParam("status") Integer status,
            @RequestParam("priority") Integer priority) {

        // 接到的資料封裝到VO裡面
        Product product = new Product();
        product.setCategoryId(categoryId);
        product.setItemType(itemType);
        product.setTitle(title);
        product.setSellPoint(sellPoint);
        product.setPrice(price);
        product.setNum(num);

//        String imagePath = FileUploadUtil.uploadFile(image);
//        System.out.println("拿到圖片路徑 " + imagePath);

        try {
            byte[] image = file.getBytes();
            product.setImage(image);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        product.setStatus(status);
        product.setPriority(priority);

        BSaddProductService.addProduct(product);

        return new JsonResult<>(OK, "成功新增產品");
    }

}
