package com.cy.store.service;

import com.cy.store.entity.District;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

// @RunWith(SpringRunner.class)表示啟動這個單元測試(正常單元測試是不能用的), 需要傳一個SpringRunner型別的參數
@RunWith(SpringRunner.class)
@SpringBootTest
public class DistrictServiceTests {
    @Autowired
    private IDistrictService districtService;

    @Test
    public void getByParent() {
        String parent = "886";
        List<District> list = districtService.getByParent(parent);
        for (District d : list) {
            System.out.println(d);
        }
    }

}

