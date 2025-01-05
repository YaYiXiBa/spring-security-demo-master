package org.zxp.securitydemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.zxp.securitydemo.config.DBUserDetailsManager;
import org.zxp.securitydemo.entity.User;
import org.zxp.securitydemo.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.zxp.securitydemo.service.UserService;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{
    @Autowired
    private DBUserDetailsManager dbUserDetailsManager;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public void saveUserDetails(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        // 保存到数据库
//        this.save(user);
//        Collection<GrantedAuthority> authorities = new ArrayList<>();
        // 创建 UserDetails 对象并保存
        UserDetails userDetails = org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())  // 使用加密后的密码
                .build();
        dbUserDetailsManager.createUser(userDetails);
    }
}
