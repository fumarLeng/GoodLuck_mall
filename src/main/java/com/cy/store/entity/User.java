package com.cy.store.entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

/**
 * 會員VO
 */

public class User extends BaseEntity implements Serializable {
    private Integer uid;
    private String username;
    private String password;
    private String salt;
    private String phone;
    private String email;
    private Integer gender;
    private byte[] avatar;
    private Integer isDelete;
//--------------------圖片---------------------------------------//
//    private String avatar64;
//--------------------圖片---------------------------------------//

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

//--------------------圖片---------------------------------------//
//
//    public String getAvatar64() {
//        return avatar64;
//    }
//
//    public void setAvatar64(String avatar64) {
//        this.avatar64 = avatar64;
//    }
//--------------------圖片---------------------------------------//

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(uid, user.uid) && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(salt, user.salt) && Objects.equals(phone, user.phone) && Objects.equals(email, user.email) && Objects.equals(gender, user.gender) && Arrays.equals(avatar, user.avatar) && Objects.equals(isDelete, user.isDelete);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(uid, username, password, salt, phone, email, gender, isDelete);
        result = 31 * result + Arrays.hashCode(avatar);
        return result;
    }
}
