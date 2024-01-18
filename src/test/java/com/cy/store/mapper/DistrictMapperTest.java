package com.cy.store.mapper;

import com.cy.store.entity.Address;
import com.cy.store.entity.District;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

// @RunWith(SpringRunner.class)注解是一个測試啟動器，
// 可以載入Springboot測試的註解
@RunWith(SpringRunner.class)
@SpringBootTest
public class DistrictMapperTest {
    @Autowired
    private DistrictMapper districtMapper;

    @Test
    public void findByParent() {
        List<District> list = districtMapper.findByParent("8861200");
        for (District d : list) {
            System.out.println(d);
        }
    }


    @Test
    public void findNameByCode() {
        String name = districtMapper.findNameByCode("8861102");
        System.out.println(name);
    }

}

