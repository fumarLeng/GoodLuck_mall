package com.cy.store.mapper;

import com.cy.store.entity.Cart;
import com.cy.store.vo.CartVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

// @RunWith(SpringRunner.class)注解是一个測試啟動器，
// 可以載入Springboot測試的註解
@RunWith(SpringRunner.class)
@SpringBootTest
public class CartMapperTest {

    @Autowired
    private CartMapper cartMapper;


    @Test
    public void insert() {
        Cart cart = new Cart();
        cart.setUid(2);
        cart.setPid(10000001);
        cart.setNum(1);
        cart.setPrice(1000L);
        cartMapper.insert(cart);
    }

    @Test
    public void updateNumByCid() {
        cartMapper.updateNumByCid(1, 4, "測試購物車", new Date());
    }

    @Test
    public void fundByUidAndPid() {
        Cart cart = cartMapper.fundByUidAndPid(2, 10000001);
        System.out.println(cart);
    }

    @Test
    public void findVOByUid(){
        List<CartVO> list =  cartMapper.findVOByUid(2);
        System.out.println(list);
    }



    //多選購物車商品
    @Test
    public void findVOByCid(){
        Integer[] cids = {1,2,3,7};
        List<CartVO> list =  cartMapper.findVOByCid(cids);
        System.out.println(list);
    }


    @Test
    public void findByCid(){
        Integer cid = 4;
        Cart result = cartMapper.findByCid(cid);
        System.out.println(result);
    }



}

