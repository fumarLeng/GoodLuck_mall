package com.cy.store.entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public class OrderItem extends BaseEntity implements Serializable {
    private Integer id;
    private Integer oid;
    private Integer pid;
    private String title;
    private byte[] image;
    private Long price;
    private Integer num;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderItem)) return false;
        OrderItem orderItem = (OrderItem) o;
        return Objects.equals(getId(), orderItem.getId()) && Objects.equals(getOid(), orderItem.getOid()) && Objects.equals(getPid(), orderItem.getPid()) && Objects.equals(getTitle(), orderItem.getTitle()) && Arrays.equals(getImage(), orderItem.getImage()) && Objects.equals(getPrice(), orderItem.getPrice()) && Objects.equals(getNum(), orderItem.getNum());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getId(), getOid(), getPid(), getTitle(), getPrice(), getNum());
        result = 31 * result + Arrays.hashCode(getImage());
        return result;
    }
}

