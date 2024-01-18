package com.cy.store.mapper;

import com.cy.store.entity.District;

import java.util.List;


//地址
public interface DistrictMapper {

    /**
     * 根據父代號查詢區域
     * @param parent 父代號
     * @return 某個父代號底下所有區域
     */
    List<District> findByParent(String parent);


    String findNameByCode(String code);
}
