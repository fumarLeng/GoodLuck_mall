package com.cy.store.controller.BackStageController;

import com.cy.store.entity.User;
import com.cy.store.service.impl.BackStageServiceImpl.BSUserServiceImpl;
import com.cy.store.service.impl.BackStageServiceImpl.BackStageUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping("BackStage")
public class BSUserController {

    //頁面資料

    @Autowired
    private BSUserServiceImpl UserServiceImpl;

    @GetMapping("/user")
    public ResponseEntity<List<User>> getAllUserData() {
        List<User> userList = UserServiceImpl.getAllUserData();
        return ResponseEntity.ok(userList);
    }

    //單筆查詢user
    @GetMapping("/user/{uid}")
    public ResponseEntity<?> GetOneUser(@PathVariable Integer uid) {
        List<User> userList = UserServiceImpl.getAllUserData();
        User updatedUser = null;
        for (User user : userList) {
            if (uid.equals(user.getUid())) {
                updatedUser = user;
                break;
            }
        }

        if (updatedUser != null) {

            return ResponseEntity.ok(updatedUser);
        } else {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with id: " + uid);
        }
    }

    //修改
    @PutMapping("/user/modify/{uid}")
    public ResponseEntity<?> updateUser(@PathVariable Integer uid,
                                        @RequestBody User updatedUser) {
        // 根據uid查找用戶，然後更新用戶資訊
        User existingUser = UserServiceImpl.findUserById(uid);
        System.out.println("existingUser: " + existingUser);
        if (existingUser != null) {
            // 更新用戶資訊
            existingUser.setUsername(updatedUser.getUsername());
            existingUser.setPhone(updatedUser.getPhone());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setGender(updatedUser.getGender());
            existingUser.setIsDelete(updatedUser.getIsDelete());

            // 保存更新後的用戶資訊
            UserServiceImpl.saveUser(existingUser);

            return ResponseEntity.ok(HttpStatus.OK);
        } else {
            return new ResponseEntity<>("找不到該用戶", HttpStatus.NOT_FOUND);
        }
    }


}
