package com.linyang.service;

import com.linyang.domian.Permission;
import com.linyang.domian.Role;

import java.util.List;

public interface PermissionService {
    List<Permission> findAll();

    List<Permission> findPermissionParentList();

    void save(Permission permission);
}
