package com.cy.store.Dao.Impl;

import com.cy.store.entity.Order;
import com.cy.store.entity.Product;
import com.cy.store.entity.User;
import com.cy.store.mapper.SpringJDBCMapper.OrderListRowMapper;
import com.cy.store.mapper.SpringJDBCMapper.ProductListRowMapper;
import com.cy.store.mapper.SpringJDBCMapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class BackStageUser implements com.cy.store.Dao.BackStageUser {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<User> getAllUserData() {
        String sql = "SELECT * FROM t_user";

        Map<String, Object> map = new HashMap<>();

        List<User> UserList = namedParameterJdbcTemplate.query(sql, map, new UserRowMapper());

        return UserList;
    }

    @Override
    public List<Order> getAllOrderData() {
        String sql = "SELECT * FROM t_order";
        Map<String, Object> map = new HashMap<>();

        List<Order> OrderList = namedParameterJdbcTemplate.query(sql, map, new OrderListRowMapper());

        return OrderList;
    }
    @Override
    public List<Product> getAllProductData() {
        String sql = "SELECT * FROM t_product";
        Map<String, Object> map = new HashMap<>();

        List<Product> ProductList = namedParameterJdbcTemplate.query(sql, map, new ProductListRowMapper());

        return ProductList;
    }

    @Override
    public User findUserById(Integer uid) {
        String sql = "SELECT * FROM t_user WHERE uid =:uid";

        Map<String, Object> map = new HashMap<>();
        map.put("uid", uid);

        List<User> UserList = namedParameterJdbcTemplate.query(sql, map, new UserRowMapper());

        if (UserList.size() > 0) {
            return UserList.get(0);
        } else {
            return null;
        }
    }

    public void saveUser(User existingUser){
        String sql = "UPDATE `t_user` SET username= :username WHERE uid = :uid";

        Map<String, Object> map = new HashMap<>();
        map.put("uid", existingUser.getUid());
        map.put("username", existingUser.getUsername());

        namedParameterJdbcTemplate.update(sql, map);
    }
}
