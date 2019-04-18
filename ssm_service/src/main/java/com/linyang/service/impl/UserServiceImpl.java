package com.linyang.service.impl;

import com.linyang.dao.UserDao;
import com.linyang.domian.SysUser;
import com.linyang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sun.jvm.hotspot.asm.DummySymbolFinder;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService,UserDetailsService {
    @Autowired
    UserDao userDao;

    /**
     *
     * @params
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        /**根据用户名查询用户对象
         * 参数一就是从数据库中查询到的用户名
         * 参数二数据库中查询到的密码
         * 参数三角色的集合对象
         */
        SysUser sysUser =  userDao.findByUserName(username);
        List<GrantedAuthority> list = new ArrayList<>();
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("ROLE_USER");
        list.add(simpleGrantedAuthority);
        UserDetails userDetails = new User(sysUser.getUsername(),"{noop}"+sysUser.getPassword(),list);
        return userDetails;
    }
}
