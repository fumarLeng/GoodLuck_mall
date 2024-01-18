package com.cy.store.service;


// 收貨地址功能業務邏輯層介面

import com.cy.store.entity.Address;

import java.util.List;

public interface IAddressService {
    void addNewAddress(Integer uid, String username, Address address);


    List<Address> getByUid(Integer uid);


    /**
     * 修改傳進來的會員id的某一條收貨地址aid為預設收貨地址
     * @param aid 收貨地址id
     * @param uid 會員id
     * @param username 修改人名稱
     */
    void setDefault(Integer aid,Integer uid,String username);


    /**
     * 刪除會員點選的收貨地址資料
     * @param aid 收貨地址編號
     * @param uid 會員id
     * @param username 會員名稱
     */
    void delete(Integer aid,Integer uid,String username);


    /**
     * 訂單需要透過這個方法拿到商品資料
     * @param aid 商品id
     * @param uid uid用來確認資料
     * @return
     */
    Address getByAid(Integer aid,Integer uid);
}
