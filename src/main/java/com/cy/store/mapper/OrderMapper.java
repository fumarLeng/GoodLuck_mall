package com.cy.store.mapper;

import com.cy.store.entity.Order;
import com.cy.store.entity.OrderItem;
import com.cy.store.vo.OrderVO;

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



}
