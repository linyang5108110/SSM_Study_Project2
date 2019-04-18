package com.linyang.dao;

import com.linyang.domian.SysUser;
import org.apache.ibatis.annotations.Select;

public interface UserDao {
    @Select("select * from sys_user where username = #{username}")
    SysUser findByUserName(String username);
}
