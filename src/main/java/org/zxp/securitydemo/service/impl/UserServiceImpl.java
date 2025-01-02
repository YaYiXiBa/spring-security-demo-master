package org.zxp.securitydemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.zxp.securitydemo.entity.User;
import org.zxp.securitydemo.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.zxp.securitydemo.service.UserService;
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{
}
