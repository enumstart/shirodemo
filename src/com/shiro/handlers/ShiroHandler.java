package com.shiro.handlers;

import com.shiro.service.ShiroService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by enum on 2018/1/24.
 */
@Controller
@RequestMapping(value = "/shiro")
public class ShiroHandler {

    @Autowired
    private ShiroService shiroService;

    @RequestMapping(value = "/login")
    public String login(@RequestParam("username")String username, @RequestParam("password")String password){
        System.out.println("------begin-----");
        Subject currentSubject = SecurityUtils.getSubject();
        if (!currentSubject.isAuthenticated()){
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            token.setRememberMe(true);
            try {
                currentSubject.login(token);
            }catch (AuthenticationException e){
                System.out.println("---登入失败-- " + e.getMessage());
            }
        }
        return "redirect:/list.jsp";
    }


}
