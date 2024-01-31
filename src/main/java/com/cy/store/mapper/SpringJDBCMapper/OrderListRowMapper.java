package com.cy.store.mapper.SpringJDBCMapper;

import com.cy.store.entity.Order;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderListRowMapper implements RowMapper<Order> {
    @Override
    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
        Order order = new Order();

        order.setOid(rs.getInt("oid"));
        order.setUid(rs.getInt("uid"));
        order.setRecvName(rs.getString("recv_name"));
        order.setRecvPhone(rs.getString("recv_phone"));
        order.setRecvProvince(rs.getString("recv_province"));
        order.setRecvCity(rs.getString("recv_city"));
        order.setRecvArea(rs.getString("recv_area"));
        order.setRecvAddress(rs.getString("recv_address"));
        order.setTotalPrice(rs.getLong("total_price"));
        order.setStatus(rs.getInt("status"));
        order.setOrderTime(rs.getDate("order_time"));
        order.setPayTime(rs.getDate("pay_time"));

        order.setCreatedUser(rs.getString("created_user"));
        order.setCreatedTime(rs.getDate("created_time"));
        order.setModifiedUser(rs.getString("modified_user"));
        order.setModifiedTime(rs.getDate("modified_time"));

        return order;
    }
}
