package com.linyang.dao;

import com.linyang.domian.SysUser;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserDao {
    @Select("select * from sys_user where username = #{username} and status = 1")
    @Results({
            @Result(property = "id",column = "id",id = true),
            @Result(property = "roleList",column = "id",
                    many = @Many(select = "com.linyang.dao.RoleDao.findRoleByUserId")
            )
    })
    SysUser findByUserName(String username);

    @Select("select * from sys_user")
    List<SysUser> finAll();

    @Select("select * from sys_user where username = #{username}")
    SysUser verificationUsername(String username);

    @Insert("INSERT into sys_user VALUES(null,#{username},#{email},#{password},#{phoneNum},#{status})")
    void save(SysUser sysUser);


    /**
     * 关联查询
     * @param id
     * @return
     */
    @Select("select * from sys_user where id = #{id}")
    @Results({
            @Result(property = "id",column = "id",id = true),
            //映射rolelist属性
            /**
             * property 映射实体类类中的属性
             *
             * column 数据库中的字段或者列名
             * many；映射多个对象
             * selelct namespace(接口全类名) + id(方法名)
             */
            @Result(property = "roleList",column = "id",
                    many = @Many(select = "com.linyang.dao.RoleDao.findRoleByUserId")
            )
    })
    SysUser findById(Integer id);

    @Delete("DELETE FROM sys_user_role WHERE userId = #{userId}")
    void delUserRoles(Integer userId);

    /**
     * 给用户添加角色信息
     * @param userId
     * @param roleId
     */
    @Insert("INSERT INTO sys_user_role VALUES(#{param1},#{param2})")
    void saveUserRole(Integer userId, Integer roleId);
}
