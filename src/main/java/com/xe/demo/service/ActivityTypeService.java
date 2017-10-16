package com.xe.demo.service;

import com.github.pagehelper.page.PageMethod;
import com.xe.demo.common.annotation.ServiceLog;
import com.xe.demo.common.pojo.PageAjax;
import com.xe.demo.common.utils.AppUtil;
import com.xe.demo.mapper.ActivityTypeMapper;
import com.xe.demo.mapper.IFileMapper;
import com.xe.demo.model.Activity;
import com.xe.demo.model.ActivityType;
import com.xe.demo.model.IFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017-10-13.
 */
@Service
public class ActivityTypeService extends AbstratService<ActivityType>{
    @Autowired
    private ActivityTypeMapper activityTypeMapper;

    @ServiceLog("查询活动类型列表")
    public PageAjax<ActivityType> queryFilePage(PageAjax<ActivityType> page, ActivityType activityType) {
        PageMethod.startPage(page.getPageNo(), page.getPageSize());
        List<ActivityType> list = activityTypeMapper.selectAll();
        return AppUtil.returnPage(list);
    }

}
