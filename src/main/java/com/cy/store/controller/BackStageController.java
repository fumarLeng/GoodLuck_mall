package com.cy.store.controller;

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
        return ResponseEntity.ok(userList);
    }

}
