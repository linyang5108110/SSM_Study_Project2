package com.test;

import com.linyang.dao.OrderDao;
import com.linyang.domian.Order;
import com.linyang.domian.Product;
import com.linyang.service.OrderService;
import com.linyang.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext-service.xml","classpath*:applicationContext-dao.xml"})
public class PageInfoTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @Test
    public void PageInfoTest()
    {
        productService.testPagezInfo(1,5);

        //添加order表数据
//        Order order = new Order();
//        Product product = new Product();
//        product.setId(12L);
//        for(int i = 11; i < 30 ; i++)
//        {
//            order.setOrderNum("0000SOS"+i);
//            order.setPayType(0);
//            order.setProduct(product);
//            order.setOrderDesc("没意思");
//            order.setOrderStatus(1);
//            order.setOrderTime(new Date());
//            order.setPeopleCount(22);
//
//            orderService.save(order);
//        }

    }

}
