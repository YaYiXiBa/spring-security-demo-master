package org.zxp.securitydemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    
    @GetMapping("/login-page")
    public String login() {
        return "login";
    }
    
    @GetMapping("/index")
    public String index() {
        return "index";
    }
    
    @GetMapping("/")
    public String root() {
        return "redirect:/index";  // 根路径重定向到 index
    }
}
