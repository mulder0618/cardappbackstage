package com.zhx.backstage.controller;

import com.zhx.backstage.service.IndexService;
import com.zhx.backstage.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by mulder on 2015/9/28.
 */
@Controller
@RequestMapping("/index")
public class IndexController {

    @Autowired
    private IndexService indexService;


    /**
     * 显示首页
     * @param modelAndView
     * @return
     */
    @RequestMapping("/showindex")
         public ModelAndView showtest(
            ModelAndView modelAndView
    ){
        modelAndView.setViewName("index/index");
        return modelAndView;
    }



}
