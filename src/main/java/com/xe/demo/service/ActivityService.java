package com.xe.demo.service;

import com.github.pagehelper.page.PageMethod;
import com.xe.demo.common.annotation.ServiceLog;
import com.xe.demo.common.pojo.PageAjax;
import com.xe.demo.common.utils.AppUtil;
import com.xe.demo.mapper.ActivityMapper;
import com.xe.demo.mapper.ActivityTypeMapper;
import com.xe.demo.mapper.AuthUserMapper;
import com.xe.demo.model.Activity;
import com.xe.demo.model.ActivityType;
import com.xe.demo.model.AuthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.example.SelectByExampleMapper;
import tk.mybatis.mapper.entity.Condition;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017-10-9.
 */
@Service
public class ActivityService extends AbstratService<Activity> {
    @Autowired
    private ActivityMapper activityMapper;
    @Autowired
    private ActivityTypeMapper activityTypeMapper;

    @ServiceLog("查询活动列表")
    public PageAjax<Activity> queryPage(PageAjax<Activity> page, Activity activity,String status) {
        PageMethod.startPage(page.getPageNo(), page.getPageSize());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
      String day=df.format(new Date());// new Date()为获取当前系统时间
        List<Activity> list=activityMapper.queryList(activity);
       /* if(status.equals("all")) {
             list = activityMapper.queryList(activity);
        }*/
        if(status.equals("starting")) {
            Condition condition = new Condition(Activity.class);
            condition.createCriteria().andCondition("activitysdate <= '" +  day + "'").andCondition("activityedate >= '" + day + "'");
            condition.setOrderByClause("createtime desc");
            list = activityMapper.selectByExample(condition);
        }
        if(status.equals("ending")) {
            Condition condition = new Condition(Activity.class);
            condition.createCriteria().andCondition("activityedate <= '" + day + "'");
            condition.setOrderByClause("createtime desc");
            list = activityMapper.selectByExample(condition);
        }
    /*    Example example=new Example(User.class);
        List<Object> values=new Arraylist<Object>();
        values.add(1L);
        values.add(2L);
        values.add(3L);
        example.creatCriteria().addIn("id",values);*/
        for(int i=0;i<list.size();i++)
        {
            String typeName="未知";
            ActivityType at=activityTypeMapper.selectByPrimaryKey(list.get(i).getActivitytype());
            if(at!=null) {
                 typeName = at.getTypename();
            }
            list.get(i).setActivitytypename(typeName);
        }
        return AppUtil.returnPage(list);
    }


    @ServiceLog("查询活动列表2月前")
    public PageAjax<Activity> queryListTwoMonth(PageAjax<Activity> page, Activity activity) {
        PageMethod.startPage(page.getPageNo(), page.getPageSize());
        List<Activity> list = activityMapper.queryList(activity);
        for(int i=0;i<list.size();i++)
        {
            String typeName="未知";
            ActivityType at=activityTypeMapper.selectByPrimaryKey(list.get(i).getActivitytype());
            if(at!=null) {
                typeName = at.getTypename();
            }
            list.get(i).setActivitytypename(typeName);
        }
        return AppUtil.returnPage(list);
    }


    @ServiceLog("查询活动列表1月前")
    public PageAjax<Activity> queryListOneMonth(PageAjax<Activity> page, Activity activity) {
        PageMethod.startPage(page.getPageNo(), page.getPageSize());
        List<Activity> list = activityMapper.queryList(activity);
        for(int i=0;i<list.size();i++)
        {
            String typeName="未知";
            ActivityType at=activityTypeMapper.selectByPrimaryKey(list.get(i).getActivitytype());
            if(at!=null) {
                typeName = at.getTypename();
            }
            list.get(i).setActivitytypename(typeName);
        }
        return AppUtil.returnPage(list);
    }

    @ServiceLog("查询活动")
    public Activity getActivityByid(String id) {
        Activity activitytmp=new Activity();
        activitytmp.setId(id);
        Activity activity = activityMapper.selectOne(activitytmp);
        return activity;
    }
}
