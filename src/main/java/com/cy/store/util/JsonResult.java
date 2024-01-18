package com.cy.store.util;


//Json格式的數據進行回傳

import java.io.Serializable;

public class JsonResult<E> implements Serializable {
    //對應的狀態碼
    private  Integer state;
    //錯誤訊息
    private String message;
    //數據
    private E data;

    public JsonResult() {
    }

    public JsonResult(Integer state) {
        this.state = state;
    }

    public JsonResult(Throwable e) {
        this.message = e.getMessage();
    }

    public JsonResult(Integer state, E data) {
        this.state = state;
        this.data = data;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }
}
