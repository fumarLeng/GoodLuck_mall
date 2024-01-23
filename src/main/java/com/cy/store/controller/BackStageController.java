package com.cy.store.controller;

import com.cy.store.entity.Order;
import com.cy.store.entity.Product;
import com.cy.store.entity.User;
import com.cy.store.service.impl.BackStageUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("BackStage")
public class BackStageController {

    @Autowired
    private BackStageUserService productService;

    @GetMapping("")
    public String getAllUserData(HttpServletRequest request) {
        List<User> userList = productService.getAllUserData();
        request.setAttribute("userList",userList);
        return "forward:/web/BackStage/index.html";
    }
    @GetMapping("/user")
    public ResponseEntity<List<User>> getAllUserData() {
        List<User> userList = productService.getAllUserData();
//        System.out.println("Avatar: " + userList.get(1).getAvatar());
        return ResponseEntity.ok(userList);
    }
    @GetMapping("/order")
    public ResponseEntity<List<Order>> getAllOrderData() {
        List<Order> orderList = productService.getAllOrderData();
        System.out.println("getOid: " + orderList.get(0).getRecvPhone());
        return ResponseEntity.ok(orderList);
    }
    @GetMapping("/product")
    public ResponseEntity<List<Product>> getAllProductData() {
        List<Product> productList = productService.getAllProductData();
        System.out.println("getOid: " + productList.get(0).getId());
        return ResponseEntity.ok(productList);
    }

}
