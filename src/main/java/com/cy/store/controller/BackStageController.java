package com.cy.store.controller;

import com.cy.store.entity.Order;
import com.cy.store.entity.Product;
import com.cy.store.entity.User;
import com.cy.store.service.impl.BackStageUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("BackStage")
public class BackStageController {

    @Autowired
    private BackStageUserService UserService;

    @GetMapping("")
    public String getAllUserData(HttpServletRequest request) {
        List<User> userList = UserService.getAllUserData();
        request.setAttribute("userList", userList);
        return "forward:/web/BackStage/index.html";
    }

    @GetMapping("/user")
    public ResponseEntity<List<User>> getAllUserData() {
        List<User> userList = UserService.getAllUserData();
//        System.out.println("Avatar: " + userList.get(1).getAvatar());
        return ResponseEntity.ok(userList);
    }

    //    修改
    @PutMapping("/user/{uid}")
    public ResponseEntity<?> updateUser(@PathVariable Integer uid) {
        List<User> userList = UserService.getAllUserData();
        User updatedUser = null;
        for (User user : userList) {
            if (uid.equals(user.getUid())) {
                updatedUser = user;
                //找到
                System.out.println("ok");
                break;
            }
        }

        if (updatedUser != null) {

            return ResponseEntity.ok(updatedUser);
        } else {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with id: " + uid);
        }
    }


    @GetMapping("/order")
    public ResponseEntity<List<Order>> getAllOrderData() {
        List<Order> orderList = UserService.getAllOrderData();
        System.out.println("getOid: " + orderList.get(0).getRecvPhone());
        return ResponseEntity.ok(orderList);
    }

    @GetMapping("/product")
    public ResponseEntity<List<Product>> getAllProductData() {
        List<Product> productList = UserService.getAllProductData();
        System.out.println("getOid: " + productList.get(0).getId());
        return ResponseEntity.ok(productList);
    }

}
