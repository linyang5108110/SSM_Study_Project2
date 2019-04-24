package com.linyang.service.impl;

import com.linyang.dao.PermissionDao;
import com.linyang.domian.Permission;
import com.linyang.domian.Role;
import com.linyang.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
   PermissionDao permissionDao;

    @Override
    public List<Permission> findAll() {
        List<Permission> permissionList = permissionDao.findAll();
        return permissionList;
    }

    @Override
    public List<Permission> findPermissionParentList() {
        List<Permission> permissionParentList = permissionDao.findPermissionParentList();
        return permissionParentList;
    }

    @Override
    public void save(Permission permission) {
        permissionDao.save(permission);
    }

}
