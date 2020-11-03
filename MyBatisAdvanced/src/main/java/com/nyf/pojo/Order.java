package com.nyf.pojo;

import java.util.Date;

public class Order {
    private int id,user_id;
    private String number;
    private Date createtime;
    private String note;
    //存储关联的用户信息
    private User user;
    //get/set....

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", number='" + number + '\'' +
                ", createtime=" + createtime +
                ", note='" + note + '\'' +
                ", user=" + user +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}