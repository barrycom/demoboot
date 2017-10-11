package com.xe.demo.controller;

import com.xe.demo.common.annotation.Authority;
import com.xe.demo.common.annotation.ControllerLog;
import com.xe.demo.common.pojo.AjaxResult;
import com.xe.demo.common.pojo.PageAjax;
import com.xe.demo.model.Activity;
import com.xe.demo.model.AuthRole;
import com.xe.demo.model.AuthUser;
import com.xe.demo.service.ActivityService;
import com.xe.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Administrator on 2017-10-9.
 */

@Controller
@RequestMapping("/admin/activity/")
public class ActivityController extends BaseController {
    @Autowired
    private ActivityService activityService;


    @Authority(opCode = "06", opName = "查询列表")
    @RequestMapping("mainPage")
    public String mainPage(Map<String, Object> map) {
        Activity activity=new Activity();
        List<Activity> list = activityService.queryList(activity);
        map.put("list", list);
        return "activity/main";
    }


    @ControllerLog("查询列表")
    @RequestMapping("queryPage")
    @ResponseBody
    @Authority(opCode = "06", opName = "查询列表")
    public PageAjax<Activity> queryPage(PageAjax<Activity> page, Activity activity) {
        return activityService.queryPage(page, activity);
    }

    @Authority(opCode = "06", opName = "新增活动")
    @RequestMapping("addPage")
    public String addPage(Map<String, Object> map) {

        return "activity/add";
    }

    @Authority(opCode = "06", opName = "修改活动")
    @RequestMapping("updateActivitPage")
    public String updateActivitPage(String id, Map<String, Object> map) {
        Activity activity = activityService.getActivityByid(id);
        map.put("activity", activity);
        return "activity/edit";
    }

    @Authority(opCode = "06", opName = "选择地图")
    @RequestMapping("selectMap")
    public String selectMap(Map<String, Object> map) {

        return "activity/selectmap";
    }

    @ControllerLog("添加活动")
    @RequestMapping("add")
    @ResponseBody
    @Authority(opCode = "06", opName = "添加活动")
    public AjaxResult add(Activity activity) {
        activity.setId(UUID.randomUUID().toString());
        activity.setActivityid(activity.getId());
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式
        String daynow = dateFormat.format(now);
        activity.setCreatetime(daynow);
        activity.setState("0");
        return activityService.save(activity);
    }
}

