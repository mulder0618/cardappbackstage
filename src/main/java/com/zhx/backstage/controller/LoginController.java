package com.zhx.backstage.controller;

import com.zhx.backstage.service.LoginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by mulder on 2015/9/28.
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;


    /**
     * 显示登录页
     * @param modelAndView
     * @return
     */
    @RequestMapping("/showlogin")
         public ModelAndView showtest(
            ModelAndView modelAndView
    ){
        modelAndView.setViewName("login/login");
        return modelAndView;
    }

    /**
     * 登录
     * @param username
     * @param password
     * @param modelAndView
     * @return
     */
    @RequestMapping("/login")
    public ModelAndView login(
            String username,
            String password,
            ModelAndView modelAndView
    ){
        try{
            SecurityUtils.getSubject().login(new UsernamePasswordToken(username,password));
            modelAndView.setViewName("index/index");
        }
        catch (AuthenticationException e) {
            modelAndView.setViewName("login/login");
        }
        return modelAndView;
    }


}
