package com.cy.store.service;

import com.cy.store.entity.Order;
import com.cy.store.vo.OrderVO;

import java.util.List;

public interface IOrderService {

    //創訂單,要有aid,uid,username,
    //要透過購物車新增,所以還要有cids,多筆所以要[]
   Order create(Integer aid, Integer uid, String username, Integer[] cids);


    List<OrderVO> queryOrderVoByUid(Integer uid);

}
