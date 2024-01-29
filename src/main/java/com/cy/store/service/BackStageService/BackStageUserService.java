package com.cy.store.service.BackStageService;

import com.cy.store.entity.Order;
import com.cy.store.entity.Product;
import com.cy.store.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface BackStageUserService {


    public List<Order> getAllOrderData();
    public List<Product> getAllProductData();

//  修改

    public Order findOrderById(Integer id );

    public Product findProductById(Integer id);


    public void saveOrder(Order existingOrder);
    public void saveProdcut(Product existingUser);
}
