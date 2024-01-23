package com.cy.store.service;

import com.cy.store.entity.Order;
import com.cy.store.entity.Product;
import com.cy.store.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface BackStageUserService {
    List<User> getAllUserData();

    public List<Order> getAllOrderData();

    public List<Product> getAllProductData();
}
