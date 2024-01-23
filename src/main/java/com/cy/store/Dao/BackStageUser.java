package com.cy.store.Dao;

import com.cy.store.entity.Order;
import com.cy.store.entity.Product;
import com.cy.store.entity.User;

import java.util.List;

public interface BackStageUser {

    List<User> getAllUserData();

    List<Order> getAllOrderData();

    List<Product> getAllProductData();
}
