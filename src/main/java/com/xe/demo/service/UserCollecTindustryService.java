package com.xe.demo.service;

import com.xe.demo.common.annotation.ServiceLog;
import com.xe.demo.mapper.UserCollecTindustryMapper;
import com.xe.demo.model.UserCollecTindustry;
import com.xe.demo.model.UserCollecTiondy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017-10-9.
 */
@Service
public class UserCollecTindustryService extends AbstratService<UserCollecTindustry> {
    @Autowired
    private UserCollecTindustryMapper  userCollecTindustryMapper;

    @ServiceLog("查询文件列表")
    public List<Map<String,String>>  querydyn(UserCollecTindustry  userCollecTindustry){

        List<Map<String,String>> li=userCollecTindustryMapper.querydyn(userCollecTindustry);

        return li;
    };
}
