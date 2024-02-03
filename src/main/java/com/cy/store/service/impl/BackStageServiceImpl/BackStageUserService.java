package com.cy.store.service.impl.BackStageServiceImpl;

import com.cy.store.Dao.Impl.BackStageUser;
import com.cy.store.entity.Order;
import com.cy.store.entity.Product;
import com.cy.store.entity.User;
//import com.ibm.dtfj.corereaders.PageCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BackStageUserService implements com.cy.store.service.BackStageService.BackStageUserService {

    @Autowired
    BackStageUser backStageUserDao;


    @Override
    public List<Order> getAllOrderData() {
        return backStageUserDao.getAllOrderData();
    }

    @Override
    public List<Product> getAllProductData() {
        return backStageUserDao.getAllProductData();
    }

    @Override
    public Order findOrderById(Integer id ) {
        return backStageUserDao.findOrderById(id);
    }

    @Override
    public void saveOrder(Order existingOrder) {
        backStageUserDao.saveOrder(existingOrder);
    }

    @Override
    public void saveProdcut(Product existingUser) {
        backStageUserDao.saveProdcut(existingUser);
    }


    @Override
    public Product findProductById(Integer id) {
        return backStageUserDao.findProductById(id);
    }

    @Override
    public List<Product> getProductsByPage(int page, int pageSize) {
        return backStageUserDao.getProductsByPage(page , pageSize);
    }

    @Override
    public Integer getProductCount() {
        return backStageUserDao.getProductCount();
    }
}
