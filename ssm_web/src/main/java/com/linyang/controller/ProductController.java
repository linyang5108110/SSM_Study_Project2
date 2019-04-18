package com.linyang.controller;

import com.linyang.domian.PageBean;
import com.linyang.domian.Product;
import com.linyang.service.ProductService;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;
import java.util.List;

/**
 *
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    /**
     * 查询全部
     *
     * @return
     */

    @RequestMapping("/findAll")
    public ModelAndView finAll() {
        //创建ModelAndView对象
        ModelAndView modelAndView = new ModelAndView();
        //查询数据
        List<Product> productList = productService.finall();
        //添加数据
        modelAndView.addObject("productList", productList);
        //指定页面
        modelAndView.setViewName("product-list");
        //返回ModelAndView对象
        return modelAndView;
    }

    /**
     * 信息保存
     */
    @RequestMapping("save")
    public String save(Product product) {

        System.out.println(product);
        //保存操作
        productService.save(product);
        //指定页面:请求查询全部G
        //保存完后重定向到findAll
        return "redirect:/product/findAll";
    }

    /**
     * 更新页面回显
     *
     * @param id
     * @return
     */
    @RequestMapping("/updateUI")
    public ModelAndView updateUI(Integer id) {
        //创建ModelAndView对象
        ModelAndView modelAndView = new ModelAndView();
        //查询数据
        Product product = productService.selectById(id);
        //添加数据
        modelAndView.addObject("product", product);
        //指定页面
        modelAndView.setViewName("product-update");
        //返回ModelAndView对象
        return modelAndView;
    }

    /**
     * 修改数据
     *
     * @param product
     * @return
     */
    @RequestMapping("/update")
    public String updte(Product product) {
        productService.update(product);

        return "redirect:/product/findAll";
    }

    /**
     * 删除一条记录
     *
     * @param id
     * @return
     */
    @RequestMapping("/delOne")
    public String delOne(Integer id) {

        productService.delOne(id);
        return "redirect:/product/findAll";
    }

    /**
     * 删除多条
     *
     * @param ids
     * @return
     */
    @RequestMapping("/delMany")
    public String delMany(Integer[] ids) {
        for (Integer id : ids) {
            System.out.println(id);
        }
        productService.delMany(ids);
        return "redirect:/product/findAll";
    }


    /**
     * 分页查询
     *
     * @return pageNum, 第几页
     * pageSize,每页多少条
     */
    @RequestMapping("/findByPage")
    public ModelAndView findByPage(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        ModelAndView modelAndView = new ModelAndView();
        //查询所有商品
        PageBean<Product> productPageBean = productService.findByPage(pageNum, pageSize);
        modelAndView.addObject("productPageBean", productPageBean);
        modelAndView.setViewName("product-list");
        return modelAndView;

    }

}
