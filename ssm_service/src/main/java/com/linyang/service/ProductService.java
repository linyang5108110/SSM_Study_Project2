package com.linyang.service;

import com.linyang.domian.PageBean;
import com.linyang.domian.Product;

import java.util.List;

public interface ProductService {

    List<Product> finall();

    void save(Product product);

    Product selectById(Integer id);

    void update(Product product);

    void delOne(Integer id);

    void delMany(Integer[] ids);

    PageBean<Product> findByPage(Integer pageNum, Integer pageSize);

    void testPagezInfo(Integer pageNum,Integer pageSize);
}
