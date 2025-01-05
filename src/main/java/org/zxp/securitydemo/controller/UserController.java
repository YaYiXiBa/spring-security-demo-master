package org.zxp.securitydemo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.web.bind.annotation.*;
import org.zxp.securitydemo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.zxp.securitydemo.service.UserService;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @GetMapping("/details")
    public Map getCurUserDetails(){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String name = authentication.getName();
        HashMap<String, Object> map = new HashMap<>();
        map.put("username", name);
        map.put("authorities", authorities);
        return map;
    }

}
