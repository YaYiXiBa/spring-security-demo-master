package org.zxp.securitydemo.controller;

import org.springframework.web.bind.annotation.*;
import org.zxp.securitydemo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.zxp.securitydemo.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    public UserService userService;

    @GetMapping("/list")
    public List<User> getList(){
        return userService.list();
    }

    @PostMapping("/add")
    public void add(@RequestBody User user){
        userService.saveUserDetails(user);
    }

}
