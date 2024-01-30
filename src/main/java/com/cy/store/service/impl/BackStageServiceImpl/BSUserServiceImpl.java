package com.cy.store.service.impl.BackStageServiceImpl;

import com.cy.store.Dao.Impl.BackStageUser;
import com.cy.store.entity.User;
import com.cy.store.service.BackStageService.BSUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BSUserServiceImpl implements BSUserService {
    @Autowired
    BackStageUser backStageUserDao;

    @Override
    public List<User> getAllUserData() {
        return backStageUserDao.getAllUserData();
    }

    @Override
    public User findUserById(Integer uid) {
        return backStageUserDao.findUserById(uid);
    }

    public void saveUser(User existingUser){
        backStageUserDao.saveUser(existingUser);
    }
}
