package com.controller;

import com.service.ShiroService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("shiro")
public class ShiroController {
    @Autowired
    private ShiroService shiroService;

    @RequestMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password) {
        // get the currently executing user:
        Subject currentUser = SecurityUtils.getSubject();
        if (!currentUser.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            token.setRememberMe(true);
            try {
                System.out.println("------------------------->token2:" + token.hashCode());
                currentUser.login(token);
            } catch (AuthenticationException ae) {
                System.out.println("------------------>"+ae.getMessage());

            }
        }
        //为什么return就会直接跳转，只打印错误消息？？？
    return  "redirect:/index.jsp";
    }

    @RequestMapping("/testShiroAnnotation")
    public String testMethod(){
        this.shiroService.testMethod();
    return "redirect:/index.jsp";
    }
}