package com.cy.store.mapper;

import com.cy.store.entity.User;
import com.cy.store.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

// @RunWith(SpringRunner.class)注解是一个測試啟動器，
// 可以載入Springboot測試的註解
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTests {
    @Autowired
    private UserMapper userMapper;


    //----------------------------------------------//

    @Test
    public void insert() {
        User user = new User();
        user.setUsername("test03");
        user.setPassword("123");
        Integer rows = userMapper.insert(user);
        System.out.println("rows=" + rows);
    }


    //----------------------------------------------//

    @Test
    public void findByUsername() {
        String username = "user06";
        User user = userMapper.findByUsername(username);
        System.out.println(user);
    }

    //----------------------------------------------------------------//

    @Test
    public void updatePasswordByUid() {

        userMapper.updatePasswordByUid(7, "123", "系統管理者", new Date());


    }

    //----------------------------------------------//

    @Test
    public void findByUid() {
        Integer uid = 7;
        System.out.println(userMapper.findByUid(uid));

    }

    //----------------------------------------------//

    @Test
    public void updateInfoByUid() {
        User user = new User();
        user.setUid(7);
        user.setPhone("0123456789");
        user.setEmail("TomTest@gmail.com");
        user.setGender(1);
        user.setModifiedUser("網站管理員");
        user.setModifiedTime(new Date());
        Integer rows = userMapper.updateInfoByUid(user);
        System.out.println("rows=" + rows);

    }

    //----------------------------------------------//

//    @Test
//    public void updateAvatarByUid(){
//        userMapper.updateAvatarByUid(
//                7,
//                "/image/avatar/icons8-1.png",
//                "管理員",
//                new Date());
//    }

}

