package com.cy.store.controller;

import com.cy.store.entity.Order;
import com.cy.store.entity.Product;
import com.cy.store.entity.User;
import com.cy.store.service.impl.BackStageUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("BackStage")
public class BackStageController {

    @Autowired
    private BackStageUserService UserService;

    //頁面返回
    @GetMapping("")
    public String getAllUserData(HttpServletRequest request) {
        List<User> userList = UserService.getAllUserData();
        request.setAttribute("userList", userList);
        return "forward:/web/BackStage/index.html";
    }

    //頁面資料
    @GetMapping("/user")
    public ResponseEntity<List<User>> getAllUserData() {
        List<User> userList = UserService.getAllUserData();
//        System.out.println("Avatar: " + userList.get(1).getAvatar());
        return ResponseEntity.ok(userList);
    }

    //單筆查詢user
    @GetMapping("/user/{uid}")
    public ResponseEntity<?> GetOneUser(@PathVariable Integer uid) {
        List<User> userList = UserService.getAllUserData();
        User updatedUser = null;
        for (User user : userList) {
            if (uid.equals(user.getUid())) {
                updatedUser = user;
                //找到
//                System.out.println("ok");
                break;
            }
        }

        if (updatedUser != null) {

            return ResponseEntity.ok(updatedUser);
        } else {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with id: " + uid);
        }
    }

    //修改
    @PutMapping("/user/modify/{uid}")
    public ResponseEntity<?> updateUser(@PathVariable Integer uid,
                                        @RequestBody User updatedUser) {
        // 根據uid查找用戶，然後更新用戶資訊
        User existingUser = UserService.findUserById(uid);
        System.out.println("existingUser: " + existingUser);
        if (existingUser != null) {
            // 更新用戶資訊
            existingUser.setUsername(updatedUser.getUsername());
            existingUser.setPhone(updatedUser.getPhone());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setGender(updatedUser.getGender());
            existingUser.setIsDelete(updatedUser.getIsDelete());

//            System.out.println("uid: " + updatedUser.getUid());
//            System.out.println("Gender: " + updatedUser.getPhone());
//            System.out.println("IsDelete: " + updatedUser.getEmail());
            // 保存更新後的用戶資訊
            UserService.saveUser(existingUser);

//            return new ResponseEntity<>("用戶更新成功", HttpStatus.OK);
            return ResponseEntity.ok(HttpStatus.OK);
        } else {
            return new ResponseEntity<>("找不到該用戶", HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/order")
    public ResponseEntity<List<Order>> getAllOrderData() {
        List<Order> orderList = UserService.getAllOrderData();
//        System.out.println("getOid: " + orderList.get(0).getRecvPhone());
        return ResponseEntity.ok(orderList);
    }

    //頁面資料product
    @GetMapping("/product")
    public ResponseEntity<List<Product>> getAllProductData() {
        List<Product> productList = UserService.getAllProductData();
        System.out.println("getOid: " + productList.get(0).getId());
        return ResponseEntity.ok(productList);
    }

    //單筆查詢product
    @GetMapping("/product/{id}")
    public ResponseEntity<?> GetOneProduct(@PathVariable Integer id) {
        List<Product> userList = UserService.getAllProductData();
        Product updatedProduct = null;
        for (Product product : userList) {
            if (id.equals(product.getId())) {
                updatedProduct = product;
                //找到
                System.out.println("ok");
                break;
            }
        }

        if (updatedProduct != null) {

            return ResponseEntity.ok(updatedProduct);
        } else {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with id: " + id);
        }
    }


    //修改
    @PutMapping("/product/modify/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Integer id,
                                           @RequestBody Product updatedProduct) {
        // 根據uid查找用戶，然後更新用戶資訊
        System.out.println("findProductById: " + id);
        Product existingProduct = UserService.findProductById(id);
        System.out.println("existingProduct: " + existingProduct);
        if (existingProduct != null) {
            // 更新用戶資訊
            System.out.println("updatedProduct.getItemType(): " + updatedProduct.getItemType());
            existingProduct.setCategoryId(updatedProduct.getCategoryId());
            existingProduct.setItemType(updatedProduct.getItemType());
            existingProduct.setTitle(updatedProduct.getTitle());
            existingProduct.setSellPoint(updatedProduct.getSellPoint());
            existingProduct.setPrice(updatedProduct.getPrice());
            existingProduct.setNum(updatedProduct.getNum());
            existingProduct.setImage(updatedProduct.getImage());
            existingProduct.setStatus(updatedProduct.getStatus());
//            existingProduct.setUsername(updatedProduct.getUsername());

//            existingProduct.setPhone(updatedProduct.getPhone());
//            existingProduct.setEmail(updatedProduct.getEmail());
//            existingProduct.setGender(updatedProduct.getGender());
//            existingProduct.setIsDelete(updatedProduct.getIsDelete());

            System.out.println("getId: " + updatedProduct.getId());
            System.out.println("getItemType: " + updatedProduct.getItemType());
//            System.out.println("Gender: " + updatedProduct.getPhone());
//            System.out.println("IsDelete: " + updatedProduct.getEmail());
            // 保存更新後的用戶資訊
            UserService.saveProdcut(updatedProduct);
            return new ResponseEntity<>("用戶更新成功", HttpStatus.OK);
        }
//        } else {
//            return new ResponseEntity<>("找不到該用戶", HttpStatus.NOT_FOUND);
//        }
        return ResponseEntity.ok(HttpStatus.OK);
    }


    //單筆查詢order
    @GetMapping("/order/{id}/{index}")
    public ResponseEntity<?> GetOneOrder(@PathVariable Integer id,
                                         @PathVariable Integer index) {
//        List<Order> orderList = UserService.findOrderById(id);
        Order orderList = UserService.findOrderById(id , index);
        Order updatedOrder = null;
        System.out.println("id: " + id);
//        for (Order order : orderList) {
//            if (id.equals(orderList.getUid())) {
                updatedOrder = orderList;
                //找到
                System.out.println("order: " + orderList );

//            }
//        }
        if (updatedOrder != null) {

            return ResponseEntity.ok(updatedOrder);
        } else {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with id: " + id);
        }
    }


    //修改
    @PutMapping("/order/modify/{id}")
    public ResponseEntity<?> updateOrder(@PathVariable Integer id,
                                           @RequestBody Product updatedProduct) {
        // 根據uid查找用戶，然後更新用戶資訊
        System.out.println("findProductById: " + id);
        Product existingProduct = UserService.findProductById(id);
        System.out.println("existingProduct: " + existingProduct);
        if (existingProduct != null) {
            // 更新用戶資訊
            System.out.println("updatedProduct.getItemType(): " + updatedProduct.getItemType());
            existingProduct.setCategoryId(updatedProduct.getCategoryId());
            existingProduct.setItemType(updatedProduct.getItemType());
            existingProduct.setTitle(updatedProduct.getTitle());
            existingProduct.setSellPoint(updatedProduct.getSellPoint());
            existingProduct.setPrice(updatedProduct.getPrice());
            existingProduct.setNum(updatedProduct.getNum());
            existingProduct.setImage(updatedProduct.getImage());
            existingProduct.setStatus(updatedProduct.getStatus());
//            existingProduct.setUsername(updatedProduct.getUsername());

//            existingProduct.setPhone(updatedProduct.getPhone());
//            existingProduct.setEmail(updatedProduct.getEmail());
//            existingProduct.setGender(updatedProduct.getGender());
//            existingProduct.setIsDelete(updatedProduct.getIsDelete());

            System.out.println("getId: " + updatedProduct.getId());
            System.out.println("getItemType: " + updatedProduct.getItemType());
//            System.out.println("Gender: " + updatedProduct.getPhone());
//            System.out.println("IsDelete: " + updatedProduct.getEmail());
            // 保存更新後的用戶資訊
            UserService.saveProdcut(updatedProduct);
            return new ResponseEntity<>("用戶更新成功", HttpStatus.OK);
        }
//        } else {
//            return new ResponseEntity<>("找不到該用戶", HttpStatus.NOT_FOUND);
//        }
        return ResponseEntity.ok(HttpStatus.OK);
    }

}



