package com.cy.store.service;

import com.cy.store.vo.CartVO;

import java.util.List;

public interface ICartService {

    /**
     * 把商品加到購物車
     *
     * @param uid      會員id
     * @param pid      商品id
     * @param amount   新增的數量幾筆
     * @param username 修改的會員名稱
     */
    void addToCart(Integer uid,
                   Integer pid,
                   Integer amount,
                   String username);

    List<CartVO> getVOByUid(Integer uid);


    //購物車商品數量的"+"
    Integer addNum(Integer cid, Integer uid, String username);
    //購物車商品數量的"-"
    Integer reduceNum(Integer cid, Integer uid, String username);


    //uid加進來比對cid
    List<CartVO> getVOByCid(Integer uid, Integer[] cids);

    //根據cid刪除商品
    int deleteCartByCid(Integer cid);

}
