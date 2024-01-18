package com.cy.store.mapper;

import com.cy.store.entity.Product;

import java.util.List;


//商品
public  interface ProductMapper {
    List<Product> findHotList();


//----------------------------------------------//

    ///根據商品id查詢商品
    Product findById(Integer id);
}
