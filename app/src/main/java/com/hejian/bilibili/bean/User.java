package com.hejian.bilibili.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by 何健 on 2017/3/28.
 */
@Entity
public class User {
    @Id
    private Long id;
    private int num;
    private String imageUrl;
    private String name;
    private double price;
    private double tId;
    @Transient
    private int tempUsageCount; // not persisted
    public double getTId() {
        return this.tId;
    }
    public void setTId(double tId) {
        this.tId = tId;
    }
    public double getPrice() {
        return this.price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getImageUrl() {
        return this.imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public int getNum() {
        return this.num;
    }
    public void setNum(int num) {
        this.num = num;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 1634344890)
    public User(Long id, int num, String imageUrl, String name, double price,
            double tId) {
        this.id = id;
        this.num = num;
        this.imageUrl = imageUrl;
        this.name = name;
        this.price = price;
        this.tId = tId;
    }
    @Generated(hash = 586692638)
    public User() {
    }
}
