package com.cy.store.service.BackStageService;

import com.cy.store.entity.User;

import java.util.List;

public interface BSUserService {
    List<User> getAllUserData();

    public User findUserById(Integer uid);

    public void saveUser(User existingUser);
}
