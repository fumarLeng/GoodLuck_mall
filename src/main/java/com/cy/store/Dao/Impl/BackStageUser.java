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
        String sql = "UPDATE `t_user` SET phone = :phone , email = :email , gender = :gender , is_delete= :is_delete WHERE uid = :uid";

        Map<String, Object> map = new HashMap<>();
        map.put("uid", existingUser.getUid());

//        map.put("username", existingUser.getUsername());
        map.put("phone", existingUser.getPhone());
        map.put("email", existingUser.getEmail());
        map.put("gender", existingUser.getGender());
        map.put("is_delete", existingUser.getIsDelete());

        namedParameterJdbcTemplate.update(sql, map);
    }

    @Override
    public void saveProdcut(Product existingProdcut){

//        String sql = "UPDATE `t_product` SET category_id = :category_id , title = :title , sell_point = :sell_point , price= :price , num= :num +" +
//                "WHERE id = :id";
//        String sql = "UPDATE `t_product` SET category_id = :getCategoryId WHERE id = :id";
            String sql = "UPDATE `t_product` SET category_id = :getCategoryId WHERE id = :id";
        Map<String, Object> map = new HashMap<>();
        map.put("id", existingProdcut.getId());
        map.put("getCategoryId", existingProdcut.getCategoryId());

//        System.out.println("getCategoryId " + existingProdcut.getCategoryId());
//
////        map.put("username", existingUser.getUsername());
//        map.put("phone", existingUser.getPhone());
//        map.put("email", existingUser.getEmail());
//        map.put("gender", existingUser.getGender());
//        map.put("is_delete", existingUser.getIsDelete());

        namedParameterJdbcTemplate.update(sql, map);
    }

    @Override
    public Product findProductById(Integer id) {
        String sql = "SELECT * FROM `t_product` WHERE id =:id";
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        System.out.println("id: " + id);
        List<Product> ProductList = namedParameterJdbcTemplate.query(sql, map, new ProductListRowMapper());
        System.out.println("ProductList.get(0): " + ProductList.get(0));
        if (ProductList.size() > 0) {
            return ProductList.get(0);
        } else {
            return null;
        }
    }

}
