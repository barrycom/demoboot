package com.xe.demo.service;

import com.xe.demo.common.annotation.ServiceLog;
import com.xe.demo.mapper.AdvertMapper;
import com.xe.demo.mapper.DaysignMapper;
import com.xe.demo.model.Advert;
import com.xe.demo.model.Daysign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017-10-18.
 */
@Service
public class DaysignService extends AbstratService<Daysign> {
    @Autowired
    private DaysignMapper daysignMapper;

    @ServiceLog("列表")
    public List<Daysign> queryadList(Map map){
       return daysignMapper.queryadList(map);
    }
}
