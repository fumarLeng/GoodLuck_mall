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


//    根据訂單oid查訂單
//    List<OrderVO> queryOrderVoByOid(Integer oid);

    /**
     * 透過uid跟訂單狀態找到對應的訂單
     * @param uid 會員id
     * @param status 訂單狀態
     * @return
     */
    List<OrderVO> queryOrderVoStatusByUid(Integer uid,Integer status);


    /**
     * 透過oid修改oid訂單狀態
     * @param oid 訂單id
     * @param uid 會員id
     * @param status 修改的狀態
     * @return
     */
    int updateStatusByOid(Integer oid,Integer uid,Integer status);
}
