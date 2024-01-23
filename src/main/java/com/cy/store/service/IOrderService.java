package com.cy.store.service;

import com.cy.store.entity.Order;
import com.cy.store.vo.OrderVO;

import java.util.List;

public interface IOrderService {

    /**
     * @param aid 會員地址
     * @param uid 會員id
     * @param username 會員名稱
     * @param cids  要透過購物車新增,所以還要有cids,多筆所以要[]
     * @return
     */
   Order create(Integer aid, Integer uid, String username, Integer[] cids);


    /**
     * 透過uid查詢訂單
     * @param uid 會員id
     * @return
     */
    List<OrderVO> queryOrderVoByUid(Integer uid);


    List<OrderVO> queryOrderVoStatusByUid(Integer uid,Integer status);
}
