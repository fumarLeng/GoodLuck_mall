package com.cy.store.service;

import com.cy.store.entity.Address;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

// @RunWith(SpringRunner.class)表示啟動這個單元測試(正常單元測試是不能用的), 需要傳一個SpringRunner型別的參數
@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressServiceTests {
    @Autowired
    private  IAddressService addressService;

    @Test
    public void addNewAddress(){
        Integer uid = 2;
        String username = "網站管理者";
        Address address = new Address();
        address.setName("小王");
        address.setPhone("0980808080");
        address.setAddress("小王村1號");
        addressService.addNewAddress(uid, username, address);
        System.out.println("OK.");
    }

    @Test
    public void setDefault(){
        addressService.setDefault(9,2,"網站管理員");
    }


    @Test
    public void delete(){
        addressService.delete(9,2,"測試刪除");
    }


}

