package com.cy.store.vo;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public class CartVO implements Serializable {
    /**
     * 購物車資料跟商品資料,要放到訂單裡
     */
    private Integer cid;
    private Integer uid;
    private Integer pid;
    private Long price;
    private Integer num;
    private String title;
    private Long realPrice;
    private byte[] image;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(Long realPrice) {
        this.realPrice = realPrice;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartVO)) return false;
        CartVO cartVO = (CartVO) o;
        return Objects.equals(getCid(), cartVO.getCid()) && Objects.equals(getUid(), cartVO.getUid()) && Objects.equals(getPid(), cartVO.getPid()) && Objects.equals(getPrice(), cartVO.getPrice()) && Objects.equals(getNum(), cartVO.getNum()) && Objects.equals(getTitle(), cartVO.getTitle()) && Objects.equals(getRealPrice(), cartVO.getRealPrice()) && Arrays.equals(getImage(), cartVO.getImage());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getCid(), getUid(), getPid(), getPrice(), getNum(), getTitle(), getRealPrice());
        result = 31 * result + Arrays.hashCode(getImage());
        return result;
    }
}



