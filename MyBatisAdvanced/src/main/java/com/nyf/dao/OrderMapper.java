package com.nyf.dao;

import com.nyf.pojo.Order;

public interface OrderMapper {

    //根据id查询订单
    public Order selectOrderByID(Integer id);

    //根据id查询订单  嵌套查询方式
    public Order selectOrderByID2(Integer id);

}
