package com.linyang.service.impl;

import com.linyang.dao.ProductDao;
import com.linyang.domian.Product;
import com.linyang.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductDao productDao;

    @Override
    public List<Product> finall() {
        return productDao.findAll();
    }

    @Override
    public void save(Product product) {
        productDao.save(product);
    }

    @Override
    public Product selectById(Integer id) {
        return productDao.selectById(id);
    }

    @Override
    public void update(Product product) {
        productDao.update(product);
    }

    @Override
    public void delOne(Integer id) {
        productDao.delOne(id);
    }

    @Override
    public void delMany(Integer[] ids) {
        if(ids != null)
        {
            for(Integer id : ids)
            {
               this.delOne(id);
            }
        }
    }
}
