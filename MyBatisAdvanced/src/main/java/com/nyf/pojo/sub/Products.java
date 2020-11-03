package com.nyf.pojo.sub;

import org.apache.ibatis.type.Alias;

import java.util.Date;

@Alias("sub-products")
//该注解 仅在冲突时有效 不能代替配置文件
public class Products {
    private long pid;
    private String pname;
    private  double price;
    private Date pdate;
    private String cid;

    @Override
    public String toString() {
        return "Product{" +
                "pid=" + pid +
                ", name='" + pname + '\'' +
                ", price=" + price +
                ", pdate=" + pdate +
                ", cid='" + cid + '\'' +
                '}';
    }

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
}
