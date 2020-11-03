package com.nyf.pojo;

import java.util.Date;

public class Products {
    private long pid;
    private String pname;
    private double price;
    private Date pdate;
    private String cid;

    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
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
