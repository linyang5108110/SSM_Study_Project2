package com.linyang.controller;


import com.github.pagehelper.PageInfo;
import com.linyang.dao.UserDao;
import com.linyang.domian.PageBean;
import com.linyang.domian.Product;
import com.linyang.domian.Role;
import com.linyang.domian.SysUser;
import com.linyang.service.RoleService;
import com.linyang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
@Secured({"ROLE_ADMIN"})//安全框架 只有带有role_admin权限的用户才能访问
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

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
        return "redirect:/user/pageFindAll";
    }

    @RequestMapping("/details")
    public ModelAndView showUser(Integer id)
    {

        //创建ModelAndView对象
        ModelAndView modelAndView = new ModelAndView();
        //查询数据
        SysUser user = userService.findById(id);
        //添加数据
        modelAndView.addObject("user", user);
        //指定页面
        modelAndView.setViewName("user-show");
        //返回ModelAndView对象
        return modelAndView;
    }

    /**
     * 用户角色添加数据回显
     * @param userID
     * @return
     */
    @RequestMapping("/saveUserRoleUI")
    public ModelAndView saveUserRoleUI(Integer userID)
    {
        //查询所有的角色
        List<Role> roleList = roleService.finall();

        //查询当前用户/用户中包括了角色信息
        SysUser user = userService.findById(userID);

        //取出用户的角色信息
        List<Role> userRoles = user.getRoleList();
        //将用户用户里面的角色id取出来放到一个字符串中方便对比
        StringBuffer userRolesStr = new StringBuffer();
        for (Role userRole : userRoles) {
           //字符串的保存的信息为,1,2,3,4,
            userRolesStr.append(",");
            userRolesStr.append(userRole.getId());
            userRolesStr.append(",");

        }

        ModelAndView modelAndView = new ModelAndView();
         modelAndView.addObject("roleList",roleList);
         modelAndView.addObject("userID",user.getId());
         modelAndView.addObject("userRolesStr",userRolesStr.toString());
         modelAndView.setViewName("user-role-add");

        return modelAndView;
    }

    /**
     * 实际用户角色保存
     * @return
     */
    @RequestMapping("/saveUserRole")
    public  String saveUserRole(Integer userId,Integer[] roleIds)
    {
        //保存
        userService.saveUserRole(userId,roleIds);

        return "redirect:/user/pageFindAll";
    }

}
