package com.cy.store.controller;

import com.cy.store.entity.User;
import com.cy.store.service.BackStageUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


@RequestMapping("backStage")
@Controller
public class BackStageController {

    @GetMapping("/index")
    public void getAllUserData(HttpServletResponse response) throws IOException {

        response.setContentType("text/html");

        String htmlContent = "<html><body><h1>Hello, World!</h1></body></html>";
        response.getWriter().write(htmlContent);
    }
}
