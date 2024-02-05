package com.cy.store.vo;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

public class OrderVO implements Serializable {
    private Integer oid;
    private Integer aid;
    private Long totalPrice;
    private Integer status;
    private Date orderTime;
    private Date payTime;
    private byte[] image;
    private String title;
    private Long price;
    private Integer num;

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
        if (!(o instanceof OrderVO)) return false;
        OrderVO orderVO = (OrderVO) o;
        return Objects.equals(getOid(), orderVO.getOid()) && Objects.equals(getAid(), orderVO.getAid()) && Objects.equals(getTotalPrice(), orderVO.getTotalPrice()) && Objects.equals(getStatus(), orderVO.getStatus()) && Objects.equals(getOrderTime(), orderVO.getOrderTime()) && Objects.equals(getPayTime(), orderVO.getPayTime()) && Arrays.equals(getImage(), orderVO.getImage()) && Objects.equals(getTitle(), orderVO.getTitle()) && Objects.equals(getPrice(), orderVO.getPrice()) && Objects.equals(getNum(), orderVO.getNum());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getOid(), getAid(), getTotalPrice(), getStatus(), getOrderTime(), getPayTime(), getTitle(), getPrice(), getNum());
        result = 31 * result + Arrays.hashCode(getImage());
        return result;
    }
}
