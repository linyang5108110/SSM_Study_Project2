package com.linyang.controller;


import com.linyang.domian.Order;
import com.linyang.domian.Product;
import com.linyang.service.OrderService;
import com.linyang.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    ProductService productService;

    /**
     * 查询所有
     * @return
     */
    @RequestMapping("/findAll")
     public ModelAndView findAll()
     {
         //创建ModelAndView对象
         ModelAndView modelAndView = new ModelAndView();
         //查询数据
         List<Order> orderList = orderService.finall();
         //添加数据
         modelAndView.addObject("orderList", orderList);
         //指定页面
         modelAndView.setViewName("order-list");
         //返回ModelAndView对象
         return  modelAndView;
     }

    /**
     *添加信息
     */
    @RequestMapping("/saveUI")
    public ModelAndView saveUI()
    {
        //创建ModelAndView对象
        ModelAndView modelAndView = new ModelAndView();
        //查询数据
        List<Product> productList = productService.finall();
        //添加数据
        modelAndView.addObject("productList", productList);
        //指定页面
        modelAndView.setViewName("order-add");
        //返回ModelAndView对象
        return  modelAndView;
    }

    /**
     * 数据保存
     * @param order
     * @return
     */
    @RequestMapping("/save")
    public String save(Order order)
    {
        orderService.save(order);

         return "redirect:/order/findAll";
    }

    /**
     * 删除单个
     * @param id
     * @return
     */
    @RequestMapping("/delOne")
    public String delOne(Integer id)
    {
        orderService.delOne(id);
        return "redirect:/order/findAll";
    }

    /**
     * 删除多个
     */
    @RequestMapping("/delMany")
    public String delMany(Integer[] ids)
    {
        for (Integer id : ids) {
            System.out.println(id);
        }
        orderService.delMany(ids);
        return "redirect:/order/findAll";
    }

}
