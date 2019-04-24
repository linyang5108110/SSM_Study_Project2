package com.linyang.dao;

import com.linyang.domian.SysUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserDao {
    @Select("select * from sys_user where username = #{username} and status = 1")
    SysUser findByUserName(String username);

    @Select("select * from sys_user")
    List<SysUser> finAll();

    @Select("select * from sys_user where username = #{username}")
    SysUser verificationUsername(String username);

    @Insert("INSERT into sys_user VALUES(null,#{username},#{email},#{password},#{phoneNum},#{status})")
    void save(SysUser sysUser);

    @Select("select * from sys_user where id = #{id}")
    SysUser findById(Integer id);
}
