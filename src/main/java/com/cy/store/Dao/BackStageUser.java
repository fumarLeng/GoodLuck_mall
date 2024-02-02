package com.cy.store.Dao;

import com.cy.store.entity.Order;
import com.cy.store.entity.Product;
import com.cy.store.entity.User;

import java.util.List;

public interface BackStageUser {

    List<User> getAllUserData();

    List<Order> getAllOrderData();

    List<Product> getAllProductData();

    public User findUserById(Integer uid);

    public Order findOrderById(Integer id);
//  修改
    public void saveUser(User existingUser);

//    =============新增產品========================
    public void addProduct(Product product);

//    =============新增產品========================

    public void saveProdcut(Product existingProdcut);

    public void saveOrder(Order existingOrder);

    public Product findProductById(Integer id);

    public List<Product> getProductsByPage(int page, int pageSize);

    public Integer getProductCount();


}
