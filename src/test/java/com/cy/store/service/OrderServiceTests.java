package com.cy.store.service;

import com.cy.store.entity.Order;
import com.cy.store.vo.CartVO;
import com.cy.store.vo.OrderVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

// @RunWith(SpringRunner.class)表示啟動這個單元測試(正常單元測試是不能用的), 需要傳一個SpringRunner型別的參數
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTests {

    @Autowired
    private IOrderService orderService;

    @Test
    public void create() {
        //Integer aid, Integer uid, String username, Integer[] cids
        Integer[] cids = {3, 5};
        Order order =  orderService.create(6, 2, "test02", cids);
        System.out.println(order);
    }

    @Test
    public void queryOrderVoByUid() {
        List<OrderVO> orderVO =  orderService.queryOrderVoByUid(2);
        System.out.println(orderVO);
    }

}



