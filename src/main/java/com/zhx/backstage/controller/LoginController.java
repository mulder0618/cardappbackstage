package com.zhx.backstage.controller;

import com.zhx.backstage.service.LoginService;
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



}
