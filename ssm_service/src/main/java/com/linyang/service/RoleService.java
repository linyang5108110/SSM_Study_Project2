package com.linyang.service;

import com.linyang.domian.Role;

import java.util.List;

public interface RoleService {
    List<Role> finall();

    String findByName(String roleName);

    void save(Role role);

    Role findById(Integer roleId);

    void saveRolePermissions(Integer roleId, Integer[] permissionIds);
}
