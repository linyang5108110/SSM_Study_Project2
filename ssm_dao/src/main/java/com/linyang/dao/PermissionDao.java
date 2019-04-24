package com.linyang.dao;

import com.linyang.domian.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PermissionDao {
    @Select("SELECT * FROM sys_permission")
    List<Permission> findAll();

    @Select("SELECT * FROM sys_permission where pid = 0")
    List<Permission>  findPermissionParentList();

    @Insert("insert into sys_permission values(null,#{permissionName},#{url},#{pid})")
    void save(Permission permission);
}
