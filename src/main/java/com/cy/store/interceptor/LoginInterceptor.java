package com.cy.store.interceptor;


import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 攔截器
 */
public class LoginInterceptor implements HandlerInterceptor {


    //在前端傳req到後端之前先執行攔截器的方法

    /**
     * 檢查session裡面有沒有uid,有就通過,沒有就重導到登錄
     *
     * @param request 前端的請求
     * @param response 後端的回應
     * @param handler 處理特定請求的業務邏輯
     * @return 如果返回 true，請求通過；返回 false，攔截請求
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        //可以透過 HttpServletRequest 來得到session
        Object obj = request.getSession().getAttribute("uid");
        //obj等於null表示會員沒有登錄過,就重導回去登錄頁面
        if (obj == null) {
            response.sendRedirect("/web/login.html");
            return false; //有return false 才會結束這個方法
        }
        return true;
    }

}

//實作完攔截器方法要去註冊--> LoginInterceptorConfigurer
