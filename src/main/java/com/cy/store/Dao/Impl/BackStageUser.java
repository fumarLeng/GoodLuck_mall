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

import java.io.IOException;
import java.util.Date;
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

    @Override
    public Order findOrderById(Integer id) {
        String sql = "SELECT oid, uid, recv_name, recv_phone, recv_province, recv_city, recv_area, recv_address, total_price, status, order_time, pay_time, created_user, created_time, modified_user, modified_time FROM t_order WHERE oid = :oid";
        Map<String, Object> map = new HashMap<>();
        map.put("oid" , id);

        List<Order> OrderList = namedParameterJdbcTemplate.query(sql, map, new OrderListRowMapper());

        System.out.println("0: "+ OrderList.get(0));
        System.out.println((OrderList.size() > 0));

        if (OrderList.size() > 0) {
            return OrderList.get(0);
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

//=============新增產品======================================

    public void addProduct(Product product) {
        String sql = "INSERT INTO t_product (" +
                "category_id, item_type, title, sell_point, price, " +
                "num, image, status, priority) " +
                "VALUES (:categoryId, :itemType, :title, :sellPoint, :price, " +
                ":num, :image, :status, :priority)";


        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("categoryId", product.getCategoryId());
        paramMap.put("itemType", product.getItemType());
        paramMap.put("title", product.getTitle());
        paramMap.put("sellPoint", product.getSellPoint());
        paramMap.put("price", product.getPrice());
        paramMap.put("num", product.getNum());
        paramMap.put("image", product.getImage());
        paramMap.put("status", product.getStatus());
        paramMap.put("priority", product.getPriority());

        namedParameterJdbcTemplate.update(sql,paramMap);


    }
//=============新增產品======================================
    public void saveOrder(Order existingOrder){
//        String sql = "UPDATE `t_order` SET recv_name = :recv_name , recv_phone = :recv_phone , recv_province = :recv_province , recv_city= :recv_city , recv_area = :recv_area , recv_address = :recv_address WHERE oid = :oid";
        String sql = "UPDATE `t_order` SET status = :status WHERE oid = :oid";

        System.out.println("sql: " + sql);
        Map<String, Object> map = new HashMap<>();
        map.put("oid", existingOrder.getOid());
        map.put("status" , existingOrder.getStatus());
//        map.put("recv_name", existingOrder.getRecvName());
//        map.put("recv_phone", existingOrder.getRecvPhone());
//        map.put("recv_province", existingOrder.getRecvProvince());
//        map.put("recv_city", existingOrder.getRecvCity());
//        map.put("recv_area", existingOrder.getRecvArea());
//        map.put("recv_address", existingOrder.getRecvAddress());
//        System.out.println("sqlRecvName: " + existingOrder.getRecvName());
        System.out.println("Status: " + existingOrder.getStatus());

        namedParameterJdbcTemplate.update(sql, map);
    }




    @Override
    public void saveProdcut(Product existingProdcut){

//        String sql = "UPDATE `t_product` SET category_id = :getCategoryId WHERE id = :id";
        String sql = "UPDATE `t_product` SET category_id = :category_id , item_type = :item_type , title = :title , " +
                "sell_point = :sell_point ,price= :price , num = :num , " +
                "image = :image ,status = :status , priority = :priority , " +
                "modified_time = :modified_time " +
                " WHERE id = :id";

        Map<String, Object> map = new HashMap<>();
        map.put("id", existingProdcut.getId());
        map.put("category_id", existingProdcut.getCategoryId());
        map.put("item_type", existingProdcut.getItemType());
        map.put("title", existingProdcut.getTitle());
        map.put("sell_point", existingProdcut.getSellPoint());
        map.put("price", existingProdcut.getPrice());
        map.put("num", existingProdcut.getNum());
        map.put("image", existingProdcut.getImage());
        map.put("status", existingProdcut.getStatus());
        map.put("priority", existingProdcut.getPriority());

        Date now  = new Date();
        System.out.println(now);
        map.put("modified_time" , now);

        namedParameterJdbcTemplate.update(sql, map);
    }

    @Override
    public Product findProductById(Integer id) {
        String sql = "SELECT * FROM `t_product` WHERE id =:id";
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);

        List<Product> ProductList = namedParameterJdbcTemplate.query(sql, map, new ProductListRowMapper());
        System.out.println("DaoProductList.get(0): " + ProductList.get(0));

        if (ProductList.size() > 0) {
            return ProductList.get(0);
        } else {
            return null;
        }
    }

    //分頁Product
    @Override
    public List<Product> getProductsByPage(int page, int pageSize) {

//        String sql = "SELECT * FROM t_product LIMIT :offset, :pageSize";
        String sql = "SELECT * FROM t_product LIMIT :offset, :pageSize";

        int offset = (page - 1) * pageSize;

        System.out.println("offset: " + offset + " pageSize: " + pageSize);

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("offset", offset);
        paramMap.put("pageSize", pageSize);

        List<Product> productList = namedParameterJdbcTemplate.query(sql, paramMap, new ProductListRowMapper());

        return productList;
    }

    @Override
    public Integer getProductCount(){
        String sql = "SELECT COUNT(*) FROM t_product";
        Map<String, Object> map = new HashMap<>();
        Integer productCount = namedParameterJdbcTemplate.queryForObject(sql,map,Integer.class);

        return productCount;
    }
}
