package com.cy.store.service;


import com.cy.store.entity.Product;
import java.util.List;
// 商品熱銷排行功能業務邏輯層介面

public interface IProductService {
    List<Product> findHostList();

    Product findById(Integer id);
}
