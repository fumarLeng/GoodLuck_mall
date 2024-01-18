package com.cy.store.service;


//地址列表功能業務邏輯層介面

import com.cy.store.entity.District;
import java.util.List;


public interface IDistrictService {

    /**
     * 根據父代號查詢國家,市,區資料
     * @param parent 父代號
     * @return 多個國家,市,區資料
     */
    List<District> getByParent(String parent);


   String getNameByCode(String code);
}
