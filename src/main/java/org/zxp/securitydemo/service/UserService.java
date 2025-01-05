package org.zxp.securitydemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.zxp.securitydemo.entity.User;

public interface UserService extends IService<User> {
    void saveUserDetails(User user);
}
