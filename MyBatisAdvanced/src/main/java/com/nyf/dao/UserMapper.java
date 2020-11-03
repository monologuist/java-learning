package com.nyf.dao;

import com.nyf.pojo.Order;
import com.nyf.pojo.User;

public interface UserMapper {
    //根据id查询用户
    public User selectByID(Integer id);
}
