package com.linyang.dao;

import com.linyang.domian.Role;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RoleDao {

    @Select("SELECT * FROM sys_role")
    List<Role> findAll();

    @Select("SELECT * FROM sys_role where roleName = #{roleName}")
    Role findByName(String roleName);


    @Insert("insert into sys_role values(null,#{roleName},#{roleDesc})")
    void save(Role role);
}
