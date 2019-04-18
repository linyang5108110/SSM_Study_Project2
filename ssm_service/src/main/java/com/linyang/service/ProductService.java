package com.linyang.service;

import com.linyang.domian.Product;

import java.util.List;

public interface ProductService {

    List<Product> finall();

    void save(Product product);

    Product selectById(Integer id);

    void update(Product product);

    void delOne(Integer id);

    void delMany(Integer[] ids);
}
