package com.test;

import com.linyang.dao.OrderDao;
import com.linyang.domian.Order;
import com.linyang.domian.Product;
import com.linyang.domian.SysUser;
import com.linyang.service.OrderService;
import com.linyang.service.ProductService;
import com.linyang.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import sun.tools.java.ClassPath;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext*.xml","classpath*:applicationContext*.xml"})
public class PageInfoTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;


    @Test
    public void PageInfoTest()
    {
//        productService.testPagezInfo(1,5);

        //添加order表数据
        Order order = new Order();
        Product product = new Product();
        product.setId(12L);
        for(int i = 11; i < 30 ; i++)
        {
            order.setOrderNum("0000SOS2"+i);
            order.setPayType(0);
            order.setProduct(product);
            order.setOrderDesc("没意思");
            order.setOrderStatus(1);
            order.setOrderTime(new Date());
            order.setPeopleCount(22);

            orderService.save(order);
        }

//        SysUser sysUser = new SysUser();
//        for(int i = 0; i < 500; i++)
//        {
//            sysUser.setUsername("linYangwww"+i);
//            sysUser.setPassword("12323");
//            sysUser.setEmail("1310853847@qq.com");
//            sysUser.setPhoneNum("15885054146");
//            sysUser.setStatus(1);
//
//            userService.save(sysUser);
//        }
    }

}
