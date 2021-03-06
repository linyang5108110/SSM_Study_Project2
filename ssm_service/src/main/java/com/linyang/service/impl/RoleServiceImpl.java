package com.linyang.service.impl;

import com.linyang.dao.RoleDao;
import com.linyang.domian.Role;
import com.linyang.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {


    @Autowired
    RoleDao roleDao;

    @Override
    public List<Role> finall() {

       List<Role> roles =  roleDao.findAll();
        return roles;

    }

    @Override
    public String findByName(String roleName) {
       Role role  =  roleDao.findByName(roleName);
       //返回一代表角色名字不存在
       if(role == null)
       {
           return "1";
       }
       else {
           return "0";
       }
    }

    @Override
    public void save(Role role) {
        roleDao.save(role);
    }

    @Override
    public Role findById(Integer roleId) {

        return roleDao.findById(roleId);
    }

    @Override
    public void saveRolePermissions(Integer roleId, Integer[] permissionIds) {
        //首先清空所有角色所带的权限
        roleDao.delRolePermissions(roleId);
        //判断permissionIds是否为null
        if(permissionIds != null)
        {
            for (Integer permissionId : permissionIds) {
                  roleDao.saveRolePermissions(roleId,permissionId);
            }
        }
    }
}
