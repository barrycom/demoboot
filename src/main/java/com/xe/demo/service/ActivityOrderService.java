package com.xe.demo.service;

import com.github.pagehelper.page.PageMethod;
import com.xe.demo.common.annotation.ServiceLog;
import com.xe.demo.common.pojo.PageAjax;
import com.xe.demo.common.utils.AppUtil;
import com.xe.demo.mapper.ActivityOrderMapper;
import com.xe.demo.mapper.AuthUserMapper;
import com.xe.demo.model.Activity;
import com.xe.demo.model.ActivityOrder;
import com.xe.demo.model.AuthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017-10-18.
 */
@Service
public class ActivityOrderService extends AbstratService<ActivityOrder> {
    @Autowired
    private ActivityOrderMapper activityOrderMapper;
    @ServiceLog("查询列表活动订单")
    public PageAjax<ActivityOrder> queryPage(PageAjax<ActivityOrder> page, ActivityOrder activityOrder) {
        PageMethod.startPage(page.getPageNo(), page.getPageSize());
        List<ActivityOrder> list = activityOrderMapper.queryList(activityOrder);
        return AppUtil.returnPage(list);
    }

    @ServiceLog("根据订单ID获取订单详情")
    public ActivityOrder selectOneById( String  id) {
       ActivityOrder activityOrder = activityOrderMapper.selectOneById(id);
        return activityOrder;
    }
}
