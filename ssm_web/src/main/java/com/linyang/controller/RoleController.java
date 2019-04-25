package com.linyang.controller;

import com.linyang.domian.Permission;
import com.linyang.domian.Role;
import com.linyang.domian.SysUser;
import com.linyang.service.PermissionService;
import com.linyang.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import java.util.List;

@Controller
@RequestMapping("/role")
@Secured({"ROLE_ADMIN"})
public class RoleController {

    @Autowired
    RoleService roleService;

    @Autowired
    PermissionService permissionService;


    @RequestMapping("/findAll")
    public ModelAndView findAll() {

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
    public String findByName(String roleName) {
        return roleService.findByName(roleName);
    }

    @RequestMapping("/save")
    public String save(Role role) {
        roleService.save(role);
        return "redirect:/role/findAll";
    }

    /**
     * 保存角色权限数据回显
     *
     * @return
     */
    @RequestMapping("/saveRolePermissionsUI")
    public ModelAndView saveRolePermissionsUI(Integer roleId) {


        //查询处所有的权限
        List<Permission> permissionList = permissionService.findAll();

        //查询处当前角色信息/信息中包含它已经具有的权限信息
        Role role = roleService.findById(roleId);
        //获取role中已经拥有的所有权限
        List<Permission> rolePermissionList = role.getPermissionList();

        //循环roole拥有权限信息将id放入字符串中，在再页面端对比是否被选中
        StringBuffer rolePermissionStr = new StringBuffer();
        for (Permission permission : rolePermissionList) {
            rolePermissionStr.append(",");
            rolePermissionStr.append(permission.getId());
            rolePermissionStr.append(",");
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("permissionList", permissionList);
        modelAndView.addObject("roleId",role.getId());
        modelAndView.addObject("rolePermissionStr", rolePermissionStr.toString());
        modelAndView.setViewName("role-permission-add");
        return modelAndView;
    }

    /**
     * 角色添加权限
     * @return
     */
    @RequestMapping("/saveRolePermissions")
    public  String saveRolePermissions(Integer roleId,Integer[] permissionIds)
    {
         roleService.saveRolePermissions(roleId,permissionIds);

        return "redirect:/role/findAll";
    }
}

