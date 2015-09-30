package com.zhx.backstage.service;

import com.zhx.backstage.mapper.LoginMapper;
import com.zhx.base.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by mulder on 15/6/16.
 */
@Service("indexService")
public class IndexService extends BaseService {

    @Autowired
    LoginMapper loginMapper;


    public int testCount(){
        return  loginMapper.testCount();
    }




}
