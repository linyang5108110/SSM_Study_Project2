package com.linyang.dao;

import com.linyang.domian.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface RoleDao {

    @Select("SELECT * FROM sys_role")
    List<Role> findAll();

    @Select("SELECT * FROM sys_role where roleName = #{roleName}")
    Role findByName(String roleName);


    @Insert("insert into sys_role values(null,#{roleName},#{roleDesc})")
    void save(Role role);

    /**
     * 根据用户id查询角色列表
     * @param userId
     * @return
     */
    @Select("SELECT r.* FROM sys_user_role ur,sys_role r  WHERE ur.roleId = r.id AND ur.userId = #{userId}")
    @Results({
            @Result(property = "id",column = "id",id = true),
            @Result(property = "permissionList",column = "id",
            many = @Many(select = "com.linyang.dao.PermissionDao.findPermissionListByRoleId"))

    })
    List<Role> findRoleByUserId(Integer userId);




    @Select("SELECT * FROM sys_role where id = #{roleId}")
    @Results({
            @Result(property = "id",column = "id",id = true),
            @Result(property = "permissionList",column = "id",
            many = @Many(select = "com.linyang.dao.PermissionDao.findPermissionListByRoleId"))
    })
    Role findById(Integer roleId);

    @Delete("DELETE from sys_role_permission WHERE roleId = #{roleId}")
    void delRolePermissions(Integer roleId);

    @Insert("INSERT INTO sys_role_permission VALUES(#{param2},#{param1})")
    void saveRolePermissions(Integer roleId, Integer permissionId);
}
