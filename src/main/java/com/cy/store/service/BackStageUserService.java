package com.cy.store.service;

import com.cy.store.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface BackStageUserService {
    List<User> getAllUserData();
}