package com.xe.demo.service;

import com.github.pagehelper.page.PageMethod;
import com.xe.demo.common.annotation.ServiceLog;
import com.xe.demo.common.pojo.PageAjax;
import com.xe.demo.common.utils.AppUtil;
import com.xe.demo.mapper.*;
import com.xe.demo.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.example.SelectByExampleMapper;
import tk.mybatis.mapper.entity.Condition;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

    @Autowired
    private UserJoinActivityMapper userJoinActivityMapper;


    @Autowired
    private MemberViewActivityMapper memberViewActivityMapper;

    @Autowired
    private MemberMapper memberMapper;

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
        for(int i=0;i<list.size();i++)
        {
            Condition condition = new Condition(UserJoinActivity.class);
            condition.createCriteria().andCondition("activityid = '" + list.get(i).getId() + "'");
            List<UserJoinActivity> userJoinActivityList=userJoinActivityMapper.selectByExample(condition);

            List<Member> mlisst=new ArrayList<>();
            for(int j=0;j<userJoinActivityList.size();j++)
            {
                Member mb=new Member();
                mb.setId(userJoinActivityList.get(j).getUserid());
                mb=memberMapper.selectOne(mb);
                mlisst.add(mb);
            }
            list.get(i).setMembersList(mlisst);

            Condition conditionview = new Condition(UserJoinActivity.class);
            conditionview.createCriteria().andCondition("activityid = '" + list.get(i).getId() + "'");
            List<MemberViewActivity> memberViewActivityList=memberViewActivityMapper.selectByExample(conditionview);
            List<Member> vlisst=new ArrayList<>();
            for(int k=0;k<memberViewActivityList.size();k++)
            {
                Member mb=new Member();
                mb.setId(memberViewActivityList.get(k).getUserid());
                mb=memberMapper.selectOne(mb);
                vlisst.add(mb);
            }
            list.get(i).setViewmembersList(vlisst);
          //  list.get(i).se list.get(i).setMembersList(mlisst);t
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

        Condition condition = new Condition(UserJoinActivity.class);
        condition.createCriteria().andCondition("activityid = '" + activity.getId() + "'");
        List<UserJoinActivity> userJoinActivityList=userJoinActivityMapper.selectByExample(condition);

        List<Member> mlisst=new ArrayList<>();
        for(int j=0;j<userJoinActivityList.size();j++)
        {
            Member mb=new Member();
            mb.setId(userJoinActivityList.get(j).getUserid());
            mb=memberMapper.selectOne(mb);
            mlisst.add(mb);
        }
        activity.setMembersList(mlisst);

        Condition conditionview = new Condition(UserJoinActivity.class);
        conditionview.createCriteria().andCondition("activityid = '" +activity.getId() + "'");
        List<MemberViewActivity> memberViewActivityList=memberViewActivityMapper.selectByExample(conditionview);
        List<Member> vlisst=new ArrayList<>();
        for(int k=0;k<memberViewActivityList.size();k++)
        {
            Member mb=new Member();
            mb.setId(memberViewActivityList.get(k).getUserid());
            mb=memberMapper.selectOne(mb);
            vlisst.add(mb);
        }
        activity.setViewmembersList(vlisst);



        return activity;
    }
}
