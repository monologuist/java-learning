package com.nyf.dao;

import com.nyf.pojo.Products;

import java.util.List;

public interface ProductDao {
    //根据id查询产品
    public Products selectByID(int id);

    //根据名称或id来进行查询  多个参数直接传一个对象
    public List<Products> selectByIDorName(Products product);

    //根据id查询一批数据
    public List<Products> selectProductByIDS(List<Integer> xxx);

    //更新数据-先根据id查找，再更新这个id下的内容
    public int updateProductTest(Products product);
}
