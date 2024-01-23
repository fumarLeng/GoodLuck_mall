package com.cy.store.mapper;

import com.cy.store.entity.Order;
import com.cy.store.entity.OrderItem;
import com.cy.store.vo.OrderVO;

import java.util.Date;
import java.util.List;

public interface OrderMapper {


    /**
     * 新增訂單
     * @param order 訂單類別的資料
     * @return 幾筆
     */
    Integer insertOrder(Order order);


    /**
     * 新增訂單明細
     * @param orderItem 訂單明細資料
     * @return 幾筆
     */
    Integer insertOrderItem(OrderItem orderItem);


    /**
     * 從uid查到所有訂單
     * @param uid 會員uid
     * @return 所有訂單資料
     */
    List<OrderVO> queryOrderVoByUid(Integer uid);


    /**
     * 從oid找到訂單
     * @param oid
     * @return
     */
    Order queryOrderVoByOid(Integer oid);

    /**
     * 透過uid找到各個狀態的訂單
     * @param uid  會員uid
     * @param status 訂單狀態
     * @return
     */
    List<OrderVO> queryOrderVoStatusByUid(Integer uid, Integer status);


    /**
     * 根據oid修改訂單狀態,付款之後更改時間
     * @param oid 訂單oid
     * @param status 要更改的狀態
     * @param payTime 付款時間
     * @return
     */
    int updateStatusByOid(Integer oid, Integer status, Date payTime);

}
