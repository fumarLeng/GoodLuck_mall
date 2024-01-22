package com.cy.store.mapper;

import com.cy.store.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setUid(rs.getInt("Uid"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setSalt(rs.getString("salt"));
        user.setPhone(rs.getString("phone"));
        user.setEmail(rs.getString("email"));
        user.setGender(rs.getInt("gender"));
        user.setAvatar(rs.getBytes("avatar"));
//        user.setIsDelete(rs.getInt("isDelete"));

        return user;
    }
}
