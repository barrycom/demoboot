package com.xe.demo.controller;

import com.xe.demo.common.annotation.Authority;
import com.xe.demo.common.annotation.ControllerLog;
import com.xe.demo.common.pojo.AjaxResult;
import com.xe.demo.common.pojo.PageAjax;
import com.xe.demo.model.Activity;
import com.xe.demo.model.ActivityType;
import com.xe.demo.model.IFile;
import com.xe.demo.service.ActivityService;
import com.xe.demo.service.ActivityTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Administrator on 2017-10-24.
 *
 *
 *
 */

@Controller
@RequestMapping("/admin/activityType/")
public class ActivityTypeController {
    @Autowired
    private ActivityTypeService activityTypeService;

    @Authority(opCode = "06", opName = "查询列表")
    @RequestMapping("mainPage")
    public String mainPage(Map<String, Object> map) {
        return "activityType/main";
    }


    @ControllerLog("查询列表")
    @RequestMapping("queryPage")
    @ResponseBody
    @Authority(opCode = "06", opName = "查询列表")
    public PageAjax<ActivityType> queryPage(PageAjax<ActivityType> page, ActivityType activityType, String status) {
        return activityTypeService.queryPage(page, activityType);
    }

    @Authority(opCode = "06", opName = "添加页面")
    @RequestMapping("addPage")
    public String addFilePage(Map<String, Object> map) {
        return "activityType/add";
    }


    @ControllerLog("添加文件")
    @RequestMapping("addActivityType")
    @ResponseBody
    @Authority(opCode = "06", opName = "添加文件")
    public AjaxResult addActivityType(ActivityType activityType) {
        activityType.setId(UUID.randomUUID().toString());
        return activityTypeService.save(activityType);
    }

    @Authority(opCode = "06", opName = "修改页面")
    @RequestMapping("editPage")
    public String editPage(Map<String, Object> map,String id) {
        ActivityType activityType = activityTypeService.queryByID(id);
        map.put("activityType", activityType);
        return "activityType/edit";
    }


    @ControllerLog("修改")
    @RequestMapping("updateActivityType")
    @ResponseBody
    @Authority(opCode = "06", opName = "修改")
    public AjaxResult updateActivityType(ActivityType activityType) {
        return activityTypeService.update(activityType);
    }


}
