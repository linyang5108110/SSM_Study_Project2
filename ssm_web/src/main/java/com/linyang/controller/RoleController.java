package com.linyang.controller;

import com.linyang.domian.Role;
import com.linyang.domian.SysUser;
import com.linyang.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    RoleService roleService;


    @RequestMapping("/findAll")
    public ModelAndView findAll()
    {

       //创建ModelAndView对象
       ModelAndView modelAndView = new ModelAndView();
       //查询数据
       List<Role> roles = roleService.finall();
       //添加数据
       modelAndView.addObject("roles", roles);
       //指定页面
       modelAndView.setViewName("role-list");
       //返回ModelAndView对象
       return modelAndView;
    }

    @RequestMapping("/findByName")
    @ResponseBody
    public String findByName(String roleName)
    {
         return roleService.findByName(roleName);
    }

    @RequestMapping("/save")
    public String save(Role role)
    {
        roleService.save(role);
        return "redirect:/role/findAll";
    }

}

