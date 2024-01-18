package com.cy.store.mapper;

import com.cy.store.entity.Address;
import org.apache.ibatis.annotations.Param;
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
public class AddressMapperTest {
    @Autowired
    private AddressMapper addressMapper;

    @Test
    public void insert() {
        Address address = new Address();
        address.setUid(1);
        address.setPhone("0912345678");
        address.setName("女朋友");

        addressMapper.insert(address);
    }

    @Test
    public void countByUid() {
        Integer count = addressMapper.countByUid(1);
        System.out.println("目前有幾筆資料 :　" + count );
    }

    @Test
    public void findByUid(){
        List<Address> list =  addressMapper.findByUid(1);
        System.out.println(list);
    }


    @Test
    public void findByAid(){
        System.out.println( addressMapper.findByAid(5));

    };
    @Test
    public void  updateNonDefault(){
        addressMapper.updateNonDefault(2);

    };
    @Test
    public void  updateDefaultByAid(){
        addressMapper.updateDefaultByAid(11,"測試預設修改",new Date());

    };

    @Test
    public void deleteByAid(){
        addressMapper.deleteByAid(8);
    }

    @Test
    public void findLastModified(){
        System.out.println(addressMapper.findLastModified(2));

    }


}

