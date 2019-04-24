package com.linyang.controller;

import com.linyang.domian.Permission;
import com.linyang.domian.Role;
import com.linyang.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    PermissionService permissionService;

    @RequestMapping("/findAll")
    public ModelAndView findAll()
    {
        //创建ModelAndView对象
        ModelAndView modelAndView = new ModelAndView();
        //查询数据
        List<Permission> permissionList = permissionService.findAll();
        //添加数据
        modelAndView.addObject("permissionList", permissionList);
        //指定页面
        modelAndView.setViewName("permission-list");
        //返回ModelAndView对象
        return modelAndView;
    }

    @RequestMapping("/saveUI")
    public ModelAndView saveIU()
    {
        //创建ModelAndView对象
        ModelAndView modelAndView = new ModelAndView();
        //查询父权限
        List<Permission> PermissionParentList = permissionService.findPermissionParentList();
        //添加数据
        modelAndView.addObject("PermissionParentList", PermissionParentList);
        //指定页面
        modelAndView.setViewName("permission-add");
        //返回ModelAndView对象
        return modelAndView;
    }

    @RequestMapping("/save")
    public String save(Permission permission)
    {
       permissionService.save(permission);
       return "redirect:/permission/findAll";
    }
}
