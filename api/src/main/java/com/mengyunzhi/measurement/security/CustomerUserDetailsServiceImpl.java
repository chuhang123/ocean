package com.mengyunzhi.measurement.security;

import com.mengyunzhi.measurement.repository.User;
import com.mengyunzhi.measurement.repository.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by panjie on 17/5/18.
 */
@Api(tags = "CustomerUserDetailsServiceImpl 实现UserDetailsService接口",
description = "用于在spring security的初始化配置中，重写了spring security的默认判断用户是否合法的函数")
@Service
public class CustomerUserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @ApiOperation(value = "@Override loadUserByUsername 通过用户名查找相关用户。同时，为找到的相关用户，添加其拥有的角色")
    @Override
    public UserDetails loadUserByUsername(@ApiParam("用户名") String username) throws UsernameNotFoundException {
        // 如果未找到相关用户，则抛出UsernameNotFoundException异常供spring security获取
        User user = userRepository.findOneByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        } else if (user.getStatus() != 0) {
            throw new UsernameNotFoundException("用户状态不正常");
        }

        // 用户拥有的认证（角色）列表。 SimpleGrantedAuthority简单授权
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

        // 向简单授权中添加角色
        authorityList.add(new SimpleGrantedAuthority("Admin"));

        // 将带有用户名、密码、角色列表的 org.springframework.security.core.userdetails.User 对象返回
        return new  org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), authorityList);
    }
}
