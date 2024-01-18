package com.cy.store.controller;


import com.cy.store.controller.ex.*;
import com.cy.store.service.ex.*;
import com.cy.store.util.JsonResult;
import org.springframework.cglib.transform.impl.AddInitTransformer;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

//控制層的父類, 用來統一做異常處理
public class BassController {
    //操作成功的狀態碼
    public static final int OK = 200;
    //---------------------設定session-------------------------//

    /**
     * 拿到session裡面的uid
     *
     * @param session session變數
     * @return 當前登錄的會員uid的值
     */
    public final Integer getuidFromSession(HttpSession session) {
        return Integer.valueOf(session.getAttribute("uid").toString());
    }

    /**
     * 拿到當前登錄會員的username
     *
     * @param session session變數
     * @return 當前登錄會員的username
     */
    public final String getUsernameFromSession(HttpSession session) {
        return session.getAttribute("username").toString();
    }

    //----------------------處理統一的異常-----------------------//

    // 當專案有異常, 會被統一攔截到這個請求處理方法, 方法的回傳值會直接傳給前端
    // 自動將異常傳遞給這個方法的參數上
    @ExceptionHandler({ServiceException.class, FileUploadException.class}) //用來統一處理丟出的異常
    public JsonResult<Void> handleException(Throwable e) {
        JsonResult<Void> result = new JsonResult<>(e);
        if (e instanceof UsernameDuplicateException) {
            result.setState(4000);
            result.setMessage("名稱已被使用");
        } else if (e instanceof UserNotFoundException) {
            result.setState(4001);
            result.setMessage("會員不存在");
        } else if (e instanceof PasswordNotMatchException) {
            result.setState(4002);
            result.setMessage("密碼錯誤");
        } else if (e instanceof AddressCountLimitException) {
            result.setState(4003);
            result.setMessage("收貨地址數量超過上限");
        } else if (e instanceof AddressNotFoundException) {
            result.setState(4004);
            result.setMessage("收貨地址不存在");
        } else if (e instanceof AccessDeniedException) {
            result.setState(4005);
            result.setMessage("收貨地址更新錯誤,使用者沒有權限");
        } else if (e instanceof ProductNotFoundException) {
            result.setState(4006);
            result.setMessage("商品資料不存在");
        } else if (e instanceof CartNotFoundException) {
            result.setState(4007);
            result.setMessage("購物車不存在");
        }else if (e instanceof InsertException) {
            result.setState(5000);
            result.setMessage("註冊時產生未知的異常");
        } else if (e instanceof UpdateException) {
            result.setState(5001);
            result.setMessage("更新時產生未知的異常");
        } else if (e instanceof DeleteException) {
            result.setState(5002);
            result.setMessage("刪除時產生未知的異常");
        } else if (e instanceof FileEmptyException) {
            result.setState(6000);
            result.setMessage("檔案是空的");
        } else if (e instanceof FileSizeException) {
            result.setState(6001);
            result.setMessage("檔案大小有問題");
        } else if (e instanceof FileTypeException) {
            result.setState(6002);
            result.setMessage("檔案類型有問題");
        } else if (e instanceof FileStateException) {
            result.setState(6003);
            result.setMessage("檔案狀態有問題");
        } else if (e instanceof FileUploadIOException) {
            result.setState(6004);
            result.setMessage("檔案上傳產生未知的異常");
        }


        return result;
    }


}
