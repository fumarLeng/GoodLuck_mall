package com.cy.store.Dao.Impl;

import com.cy.store.entity.Order;
import com.cy.store.entity.User;
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

//        List<User> OrderList = namedParameterJdbcTemplate.query(sql, map, new OrderListRowMapper());

//        return OrderList;
        return null;
    }
}
