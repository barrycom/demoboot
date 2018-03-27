package com.xe.demo.service;

import com.github.pagehelper.page.PageMethod;
import com.xe.demo.common.annotation.ServiceLog;
import com.xe.demo.common.pojo.PageAjax;
import com.xe.demo.common.utils.AppUtil;
import com.xe.demo.mapper.ActivityOrderMapper;
import com.xe.demo.mapper.AdvertMapper;
import com.xe.demo.model.ActivityOrder;
import com.xe.demo.model.ActivityType;
import com.xe.demo.model.Advert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017-10-18.
 */
@Service
public class AdvertService extends AbstratService<Advert> {
    @Autowired
    private AdvertMapper advertMapper;

    @ServiceLog("查询活动类型列表")
    public List<Advert> queryadList(Map map){
       return advertMapper.queryadList(map);
    }
}
