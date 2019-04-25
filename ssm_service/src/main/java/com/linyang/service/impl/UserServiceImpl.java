package com.linyang.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.linyang.dao.UserDao;
import com.linyang.domian.PageBean;
import com.linyang.domian.Role;
import com.linyang.domian.SysUser;
import com.linyang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import sun.jvm.hotspot.asm.DummySymbolFinder;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    UserDao userDao;

    /**
     * loadUserByUsername 根据用户名载入用户对象
     *
     * @return 用户详情对象（安全框架可以识别）
     * @throws UsernameNotFoundException
     * @params username 登陆时会自动传入
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        /**根据用户名查询用户对象
         * 参数一就是从数据库中查询到的用户名
         * 参数二数据库中查询到的密码
         * 参数三角色的集合对象
         */
        //根据页面输入的用户名到数据库中查询用户数据
        SysUser sysUser = userDao.findByUserName(username);
        //添加真正的角色从数据库中拿出来的
        List<GrantedAuthority> list = new ArrayList<>();
        //创建
        List<Role> roleList = sysUser.getRoleList();

        for (Role role : roleList) {
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("ROLE_" + role.getRoleName());
            list.add(simpleGrantedAuthority);
        }
        //"{noop}"密码不加密
        //把用户和密码还有权限都给UserDetails让框架自动处理，我们处理不了
        UserDetails userDetails = new User(sysUser.getUsername(), sysUser.getPassword(), list);
        return userDetails;
    }

    @Override
    public List<SysUser> finall() {

        List<SysUser> sysUsersList = userDao.finAll();
        return sysUsersList;
    }

    @Override
    public PageInfo<SysUser> findByPage(Integer pageNum, Integer pageSize) {
        //将第几页 每页几条数据放到pageHelper中
        PageHelper.startPage(pageNum, pageSize);
        //查询处所有数据
        List<SysUser> sysUsers = userDao.finAll();
        //创建pageInfo
        PageInfo<SysUser> pageInfo = new PageInfo<>(sysUsers);
        return pageInfo;
    }

    @Override
    public String verificationUsername(String username) {
        SysUser sysUser = userDao.verificationUsername(username);
        //如果sysuser是null就证明用户名不存在
        if (sysUser == null) {
            return "1";
        } else {
            return "0";
        }
    }


    /**
     * 加密类
     */
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(SysUser sysUser) {
        //将密码加密
        String encodePassword = bCryptPasswordEncoder.encode(sysUser.getPassword());
        sysUser.setPassword(encodePassword);

        userDao.save(sysUser);
    }

    @Override
    public SysUser findById(Integer id) {
        SysUser sysUser = userDao.findById(id);
        return sysUser;
    }

    /**
     * 保存用户角色添加
     *
     * @param userId
     * @param roleIds
     */
    @Override
    public void saveUserRole(Integer userId, Integer[] roleIds) {
        //首先清空用户中的所有角色
        userDao.delUserRoles(userId);

        //判断roleIds是否为null
        if (roleIds != null) {   //循环roleIds为用户添加角色信息
            for (Integer roleId : roleIds) {
                userDao.saveUserRole(userId, roleId);
            }
        }
    }
}
