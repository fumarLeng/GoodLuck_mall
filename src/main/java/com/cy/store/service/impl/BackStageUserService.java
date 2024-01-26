package com.cy.store.service.impl;

import com.cy.store.Dao.Impl.BackStageUser;
import com.cy.store.entity.Order;
import com.cy.store.entity.Product;
import com.cy.store.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BackStageUserService implements com.cy.store.service.BackStageUserService {

    @Autowired
    BackStageUser backStageUserDao;


    @Override
    public List<User> getAllUserData() {
        return backStageUserDao.getAllUserData();
    }

    @Override
    public List<Order> getAllOrderData() {
        return backStageUserDao.getAllOrderData();
    }

    @Override
    public List<Product> getAllProductData() {
        return backStageUserDao.getAllProductData();
    }

    @Override
    public User findUserById(Integer uid) {
        return backStageUserDao.findUserById(uid);
    }

    public void saveUser(User existingUser){
        backStageUserDao.saveUser(existingUser);
    }

    @Override
    public void saveProdcut(Product existingUser) {
        backStageUserDao.saveProdcut(existingUser);
    }

    ;




    @Override
    public Product findProductById(Integer id) {
        return null;
    }
}
