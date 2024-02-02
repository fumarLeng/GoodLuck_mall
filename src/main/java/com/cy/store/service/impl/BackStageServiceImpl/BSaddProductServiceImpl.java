package com.cy.store.service.impl.BackStageServiceImpl;

import com.cy.store.Dao.Impl.BackStageUser;
import com.cy.store.entity.Product;
import com.cy.store.service.BackStageService.BSaddProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BSaddProductServiceImpl implements BSaddProductService {

    @Autowired
    BackStageUser backStageUserDao;

    @Override
    public void addProduct(Product product) {
        backStageUserDao.addProduct(product);
    }

}
