package com.cy.store.config;


import com.cy.store.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

// 註冊攔截器
@Configuration //加載當前的攔截器並進行註冊
public class LoginInterceptorConfigurer implements WebMvcConfigurer {

    //設定自定義的攔截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //new 出自定義的攔截器
        HandlerInterceptor interceptor = new LoginInterceptor();
        //把白名單要放的網址放在List集合
        List<String> patterns = new ArrayList<>();
        patterns.add("/bootstrap3/**");
        patterns.add("/css/**");
        patterns.add("/images/**");
        patterns.add("/js/**");
        patterns.add("/web/register.html");
        patterns.add("/web/login.html");
        patterns.add("/web/index.html");
        patterns.add("/web/product.html");
        patterns.add("/users/reg");
        patterns.add("/users/login");
        patterns.add("/districts/**");
        patterns.add("/products/**");

        registry.addInterceptor(interceptor) //註冊
            .addPathPatterns("/**") //黑名單
            .excludePathPatterns(patterns); //他要的參數是一個list集合
    }
}
