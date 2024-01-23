package com.cy.store.service.impl;

import com.cy.store.Dao.Impl.BackStageUser;
import com.cy.store.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BackStageUserService implements com.cy.store.service.BackStageUserService {

    @Autowired
    BackStageUser backStageUserDao;


    @Override
    public List<User> getAllUserData() {
        return backStageUserDao.getAllUserData();
    }
}
