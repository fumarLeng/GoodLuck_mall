package com.cy.store.service;

import com.cy.store.entity.District;
import com.cy.store.vo.CartVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

// @RunWith(SpringRunner.class)表示啟動這個單元測試(正常單元測試是不能用的), 需要傳一個SpringRunner型別的參數
@RunWith(SpringRunner.class)
@SpringBootTest
public class CartServiceTests {
    @Autowired
    private ICartService cartService;

    @Test
    public void addToCart() {
        cartService.addToCart(2,
                10000001,
                5,
                "測試總數");
    }

    @Test
    public void getVOByUid() {
        List<CartVO> list = cartService.getVOByUid(2);
        //看筆數
        System.out.println("count=" + list.size());
        for (CartVO c : list) {
            System.out.println(c);
        }

    }

    @Test
    public void addNum(){

        Integer cid = 2;
        Integer uid = 2;
        String username = "購物車數量";
        Integer num = cartService.addNum(cid,uid,username);
        System.out.println(num);
    }


}



