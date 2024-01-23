package com.cy.store.mapper;

import com.cy.store.entity.Order;
import com.cy.store.entity.OrderItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

// @RunWith(SpringRunner.class)注解是一个測試啟動器，
// 可以載入Springboot測試的註解
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMapperTest {

    @Autowired
    private OrderMapper orderMapper;


    @Test
    public void insert() {
        Order order = new Order();
        order.setUid(2);
        order.setRecvName("測試訂單");
        order.setRecvPhone("0912345678");
        orderMapper.insertOrder(order);
    }

    @Test
    public void insertOrderItem() {
        OrderItem orderItem = new OrderItem();
        orderItem.setOid(1);
        orderItem.setPid(10000004);
        orderItem.setTitle("(deli）1548A商務辦公計算機,太陽能雙電源");
        orderMapper.insertOrderItem(orderItem);
    }


    //透過uid查詢order oid 再透過oid查詢 order_item裡面的商品
    @Test
    public void queryOrderVoByUid() {
        System.out.println(orderMapper.queryOrderVoByUid(2));
    }


    @Test
    public void queryOrderVoStatusByUid() {

        System.out.println(orderMapper.queryOrderVoStatusByUid(2,1));
    }

}

