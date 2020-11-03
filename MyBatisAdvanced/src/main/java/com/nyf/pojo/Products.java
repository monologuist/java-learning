package com.nyf.pojo;

import java.util.Date;

public class Products {
    private Integer pid;
    private String pname;
    private double price;
    private Date pdate;
    private String cid;

    //把属性中的pid设置成包装类型，其初始值为null，动态sql的条件生效了
    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return pname;
    }

    public void setName(String name) {
        this.pname = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getPdate() {
        return pdate;
    }

    public void setPdate(Date pdate) {
        this.pdate = pdate;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    @Override
    public String toString() {
        return "Products{" +
                "pid=" + pid +
                ", name='" + pname + '\'' +
                ", price=" + price +
                ", pdate=" + pdate +
                ", cid='" + cid + '\'' +
                '}';
    }
}
