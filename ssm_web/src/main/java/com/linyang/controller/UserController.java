package com.linyang.controller;


import com.github.pagehelper.PageInfo;
import com.linyang.dao.UserDao;
import com.linyang.domian.PageBean;
import com.linyang.domian.Product;
import com.linyang.domian.SysUser;
import com.linyang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/findAll")
    public ModelAndView findAll() {

        //创建ModelAndView对象
        ModelAndView modelAndView = new ModelAndView();
        //查询数据
        List<SysUser> sysUsers = userService.finall();
        //添加数据
        modelAndView.addObject("sysUsers", sysUsers);
        //指定页面
        modelAndView.setViewName("user-list");
        //返回ModelAndView对象

        return modelAndView;
    }

    /**
     * 分页查询过所有
     *
     * @return
     */
    @RequestMapping("/pageFindAll")
    public ModelAndView pageFindAll(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                    @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {

        ModelAndView modelAndView = new ModelAndView();
        //查询所有商品
        PageInfo<SysUser> sysUserPageBean = userService.findByPage(pageNum, pageSize);
        System.out.println(sysUserPageBean);
        modelAndView.addObject("sysUserPageBean", sysUserPageBean);
        modelAndView.setViewName("user-list");
        return modelAndView;
    }

    /**
     * 查询用户名字是否重复
     *
     * @param username
     * @return
     */
    @RequestMapping("/saveUI")
    @ResponseBody
    public String saveUI(String username) {
        //调用service方式验证用户名是否存在
        String result = userService.verificationUsername(username);
        return result;
    }

    /**
     * 保存用户
     *
     * @param sysUser
     * @return
     */
    @RequestMapping("/save")
    public String save(SysUser sysUser) {
         userService.save(sysUser);
        return "redirect:/user/findAll";
    }

    @RequestMapping("/details")
    public ModelAndView showUser(Integer id)
    {
        System.out.println(id);
        return null;
    }

}
