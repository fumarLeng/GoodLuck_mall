package com.cy.store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("BackStage")
public class BackStageController {

    @GetMapping("")
    public String getAllUserData() {

        return "forward:/web/BackStage/index.html";
    }
}
