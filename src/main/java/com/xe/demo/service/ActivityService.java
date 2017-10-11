package com.xe.demo.service;

import com.github.pagehelper.page.PageMethod;
import com.xe.demo.common.annotation.ServiceLog;
import com.xe.demo.common.pojo.PageAjax;
import com.xe.demo.common.utils.AppUtil;
import com.xe.demo.mapper.ActivityMapper;
import com.xe.demo.mapper.AuthUserMapper;
import com.xe.demo.model.Activity;
import com.xe.demo.model.AuthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017-10-9.
 */
@Service
public class ActivityService extends AbstratService<Activity> {
    @Autowired
    private ActivityMapper activityMapper;

    @ServiceLog("查询活动列表")
    public PageAjax<Activity> queryPage(PageAjax<Activity> page, Activity activity) {
        PageMethod.startPage(page.getPageNo(), page.getPageSize());
        List<Activity> list = activityMapper.queryList(activity);
        return AppUtil.returnPage(list);
    }

    @ServiceLog("查询活动列表")
    public Activity getActivityByid(String id) {
        Activity activity = activityMapper.selectByPrimaryKey(id);
        return activity;
    }
}
