package com.xe.demo.controller;

import com.xe.demo.common.annotation.Authority;
import com.xe.demo.common.annotation.ControllerLog;
import com.xe.demo.common.pojo.PageAjax;
import com.xe.demo.model.Activity;
import com.xe.demo.model.AuthRole;
import com.xe.demo.model.AuthUser;
import com.xe.demo.service.ActivityService;
import com.xe.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017-10-9.
 */

@Controller
@RequestMapping("/admin/activity/")
public class ActivityController extends BaseController {
    @Autowired
    private ActivityService activityService;


    @Authority(opCode = "0101", opName = "无需验证")
    @RequestMapping("mainPage")
    public String mainPage(Map<String, Object> map) {
        Activity activity=new Activity();
        List<Activity> list = activityService.queryList(activity);
        map.put("list", list);
        return "activity/main";
    }


    @ControllerLog("查询用户列表")
    @RequestMapping("queryPage")
    @ResponseBody
    @Authority(opCode = "0101", opName = "无需验证")
    public PageAjax<Activity> queryPage(PageAjax<Activity> page, Activity activity) {
        return activityService.queryPage(page, activity);
    }

    @Authority(opCode = "010101", opName = "无需验证")
    @RequestMapping("addPage")
    public String addPage(Map<String, Object> map) {

        return "activity/add";
    }

    @Authority(opCode = "010101", opName = "无需验证")
    @RequestMapping("selectMap")
    public String selectMap(Map<String, Object> map) {

        return "activity/selectmap";
    }
}

