package com.linyang.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.linyang.dao.OrderDao;
import com.linyang.domian.Order;
import com.linyang.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderDao orderDao;

    @Override
    public List<Order> finall() {
        return orderDao.findAll();
    }

    @Override
    public void save(Order order) {
        orderDao.save(order);
    }

    @Override
    public void delOne(Integer id) {
        orderDao.delOne(id);
    }

    @Override
    public void delMany(Integer[] ids) {
        if(ids != null)
        {
            for (Integer id : ids) {
                 this.orderDao.delOne(id);
            }
        }
    }

    @Override
    public PageInfo<Order> findPageInfo(Integer pageNum, Integer pageSize) {
        //三行代码解决分页查询
        PageHelper.startPage(pageNum,pageSize);
        List<Order> all = orderDao.findAll();
        PageInfo<Order> pageInfo = new PageInfo<>(all);
        return pageInfo;
    }
}
