package com.zhx.backstage.service;

import com.zhx.backstage.mapper.LoginMapper;
import com.zhx.base.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mulder on 15/6/16.
 */
@Service("loginService")
public class LoginService extends BaseService {

    @Autowired
    LoginMapper loginMapper;


    public int testCount(){
        return  loginMapper.testCount();
    }




}
