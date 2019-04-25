package com.linyang.service;

import com.github.pagehelper.PageInfo;
import com.linyang.domian.PageBean;
import com.linyang.domian.SysUser;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {

    List<SysUser> finall();

    PageInfo<SysUser> findByPage(Integer pageNum, Integer pageSize);

    String verificationUsername(String username);

    void save(SysUser sysUser);

    SysUser findById(Integer id);

    /**
     * 保存用户角色添加
     * @param userId
     * @param roleIds
     */
    void saveUserRole(Integer userId, Integer[] roleIds);
}
