package com.cy.store.mapper.SpringJDBCMapper;

import com.cy.store.entity.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductListRowMapper implements RowMapper<Product> {
    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        Product product = new Product();

        product.setId(rs.getInt("id"));
        product.setCategoryId(rs.getInt("category_id"));
        product.setItemType(rs.getString("item_type"));
        product.setTitle(rs.getString("title"));
        product.setSellPoint(rs.getString("sell_point"));
        product.setPrice(rs.getLong("price"));
        product.setNum(rs.getInt("num"));
        product.setImage(rs.getString("image")); //路徑
        product.setStatus(rs.getInt("status"));
        product.setPriority(rs.getInt("priority"));
        product.setCreatedTime(rs.getDate("created_time"));
        product.setModifiedTime(rs.getDate("modified_time"));
        product.setCreatedUser(rs.getString("created_user"));
        product.setModifiedUser(rs.getString("modified_user"));


        return product;
    }
}
