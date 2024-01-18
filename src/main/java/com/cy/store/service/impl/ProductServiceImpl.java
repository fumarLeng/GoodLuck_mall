package com.cy.store.service.impl;

import com.cy.store.entity.Product;
import com.cy.store.mapper.ProductMapper;
import com.cy.store.service.IProductService;
import com.cy.store.service.ex.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// 商品熱銷排行功能業務邏輯層介面實作
@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductMapper productMapper;


    @Override
    public List<Product> findHostList() {
        List<Product> list = productMapper.findHotList();

        return list;
    }

    @Override
    public Product findById(Integer id) {
        Product product = productMapper.findById(id);

        if (product == null) {
            throw new ProductNotFoundException("商品不存在");
        }

        //降低傳輸的資料大小,不寫也可以
        product.setPriority(null);
        product.setCreatedUser(null);
        product.setCreatedTime(null);
        product.setModifiedUser(null);
        product.setModifiedTime(null);

        return product;
    }
}
