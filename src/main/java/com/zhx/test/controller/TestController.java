package com.zhx.test.controller;

import com.zhx.base.model.BaseParam;
import com.zhx.test.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by mulder on 2015/6/16.
 */
@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;


    /**
     * 测试页面
     * @param modelAndView
     * @return
     */
    @RequestMapping("/showtest")
         public ModelAndView showtest(
            ModelAndView modelAndView
    ){
        modelAndView.setViewName("test/testindex");
        return modelAndView;
    }

    /**
     * 测试mysql链接
     * @param modelAndView
     * @return
     */
    @RequestMapping("/testmysqlcontract")
    public ModelAndView testmysqlcontract(
            ModelAndView modelAndView
    ){
        int count = testService.testCount();
        modelAndView.addObject("count", count);
        modelAndView.setViewName("test/testmysqlcontract");
        return modelAndView;
    }

    /**
     * 插入测试
     * @param modelAndView
     * @return
     */
    @Transactional
    @RequestMapping("/testinsert")
    public ModelAndView testinsert(
            ModelAndView modelAndView
    ){
        testService.testInsert("测试");
        testService.testInsert("测1231222222sdfdsfsdfdsfffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffsdfs试");
        modelAndView.setViewName("test/testindex");
        return modelAndView;
    }



    /**
     * 测试参数
     * @param modelAndView
     * @param param1
     * @param param2
     * @param param3
     * @return
     */
    @RequestMapping("/testparam" )
    public ModelAndView testparam(
            ModelAndView modelAndView,
            String param1,
            String param2,
            String param3,
            BaseParam baseParam
    ){
        modelAndView.setViewName("test/testindex");
        return modelAndView;
    }


}
