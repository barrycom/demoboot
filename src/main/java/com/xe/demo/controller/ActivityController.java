package com.xe.demo.controller;

import com.github.pagehelper.page.PageMethod;
import com.xe.demo.common.annotation.Authority;
import com.xe.demo.common.annotation.ControllerLog;
import com.xe.demo.common.pojo.AjaxResult;
import com.xe.demo.common.pojo.PageAjax;
import com.xe.demo.model.*;
import com.xe.demo.service.ActivityService;
import com.xe.demo.service.ActivityTypeService;
import com.xe.demo.service.AdvertService;
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
    @Autowired
    private ActivityTypeService activityTypeService;
    @Autowired
    private AdvertService advertService;


    @Authority(opCode = "06", opName = "广告位列表")
    @RequestMapping("mainadPage")
    public String mainadPage(Map<String, Object> map) {
        return "advert/main";
    }

    @Authority(opCode = "06", opName = "广告位列表")
    @ResponseBody
    @RequestMapping("adList")
    public PageAjax<Advert> adList(Map<String, Object> map,Advert advert,PageAjax<Advert> page) {
       map.put("adtype","0");
        PageMethod.startPage(page.getPageNo(), page.getPageSize());
        List<Advert> list = advertService.queryadList(map);
        return new PageAjax<Advert>(list);
    }

    @Authority(opCode = "06", opName = "添加广告位")
    @RequestMapping("addadPage")
    public String addadPage(Map<String, Object> map) {
        Advert advert=new Advert();
        List<Advert> activityTypeList=advertService.queryList(advert);
        map.put("list", activityTypeList);
        return "advert/add";
    }

    @ControllerLog("添加广告位")
    @RequestMapping("addadvert")
    @ResponseBody
    @Authority(opCode = "06", opName = "添加广告位")
    public AjaxResult addadvert(Advert advert) {
      return advertService.save(advert);
    }

    @Authority(opCode = "06", opName = "修改广告位")
    @RequestMapping("updatead")
    public String updatead(Map<String, Object> map,Integer id) {
        Advert advert= advertService.queryByID(id);
        map.put("advert", advert);
        return "advert/edit";
    }


    @ControllerLog("修改广告位")
    @RequestMapping("editad")
    @ResponseBody
    @Authority(opCode = "06", opName = "修改广告位")
    public AjaxResult editad(Advert advert) {
        return advertService.update(advert);
    }

    @ControllerLog("删除")
    @RequestMapping("removead")
    @ResponseBody
    @Authority(opCode = "06", opName = "删除广告位")
    public AjaxResult removead(int id) {
        AjaxResult ajaxResult=new AjaxResult();
        if(advertService.delById(id)<=0){
            ajaxResult.setRetcode(-1);
            ajaxResult.setRetmsg("操作失败");
        }
        return ajaxResult;
    }


    @Authority(opCode = "06", opName = "查询列表")
    @RequestMapping("mainPage")
    public String mainPage(Map<String, Object> map) {
        Activity activity=new Activity();
        List<Activity> list = activityService.queryList(activity);
        map.put("list", list);
        return "activity/main";
    }


    @Authority(opCode = "06", opName = "活动待上架列表")
    @RequestMapping("mainPagedsj")
    public String mainPagedsj(Map<String, Object> map,PageAjax<Activity> page) {
        Activity activity=new Activity();
        PageAjax<Activity> list = activityService.queryPage(page,activity);
        map.put("list", list);
        return "activity/maindsj";
    }

    @Authority(opCode = "06", opName = "活动已上架列表")
    @RequestMapping("mainPageysj")
    public String mainPageysj(Map<String, Object> map,PageAjax<Activity> page) {
        Activity activity=new Activity();
        PageAjax<Activity> list = activityService.queryPage(page,activity);
        map.put("list", list);
        return "activity/mainysj";
    }



    @ControllerLog("查询待上架列表")
    @RequestMapping("queryPagedsj")
    @ResponseBody
    @Authority(opCode = "06", opName = "查询待上架列表")
    public PageAjax<Activity> queryPagedsj(PageAjax<Activity> page, Activity activity,String daytime) {
        activity.setState("0");
        PageAjax<Activity> activityList=activityService.queryPage(page, activity);
        if(daytime.equals("one"))
        {
            activityList=   activityService.queryListOneMonth(page, activity);
        }
        if(daytime.equals("two"))
        {
            activityList= activityService.queryListTwoMonth(page, activity);
        }

        if(daytime.equals("more"))
        {
            activityList=activityService.queryPage(page, activity);
        }

            return activityList;
    }


    @ControllerLog("查询已上架列表")
    @RequestMapping("queryPageysj")
    @ResponseBody
    @Authority(opCode = "06", opName = "查询已上架列表")
    public PageAjax<Activity> queryPageysj(PageAjax<Activity> page, Activity activity,String daytime) {
        activity.setState("1");
        PageAjax<Activity> activityList=activityService.queryPage(page, activity);
        if(daytime.equals("one"))
        {
            activityList=   activityService.queryListOneMonth(page, activity);
        }
        if(daytime.equals("two"))
        {
            activityList= activityService.queryListTwoMonth(page, activity);
        }

        if(daytime.equals("more"))
        {
            activityList=activityService.queryPage(page, activity);
        }
        return activityList;
    }





    @ControllerLog("查询列表")
    @RequestMapping("queryPage")
    @ResponseBody
    @Authority(opCode = "06", opName = "查询列表")
    public PageAjax<Activity> queryPage(PageAjax<Activity> page, Activity activity,String status) {


        return activityService.queryPage(page, activity,status);
    }

    @Authority(opCode = "06", opName = "新增活动")
    @RequestMapping("addPage")
    public String addPage(Map<String, Object> map) {

        List<ActivityType> activityTypeList=activityTypeService.queryAll();

        map.put("list", activityTypeList);
        return "activity/add";
    }


    @Authority(opCode = "06", opName = "查看详情")
    @RequestMapping("view")
    public String view(Map<String, Object> map,String id) {
        Activity activity = activityService.getActivityByid(id);
        map.put("activity", activity);
        return "activity/pview";
    }




    @Authority(opCode = "06", opName = "修改活动")
    @RequestMapping("updateActivitPage")
    public String updateActivitPage(String id,String cz, Map<String, Object> map) {
        Activity activity = activityService.getActivityByid(id);
        List<ActivityType> activityTypeList=activityTypeService.queryAll();
        map.put("list", activityTypeList);
        map.put("cz", cz);
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
        activity.setActivityedate(activity.getActivitysdate());
        return activityService.save(activity);
    }


    @ControllerLog("修改活动")
    @RequestMapping("edit")
    @ResponseBody
    @Authority(opCode = "06", opName = "修改活动")
    public AjaxResult edit(Activity activity) {
        activity.setActivityedate(activity.getActivitysdate());
        return activityService.update(activity);
    }


    @ControllerLog("上架/下架活动")
    @RequestMapping("sxstatus")
    @ResponseBody
    @Authority(opCode = "06", opName = "上架活动")
    public AjaxResult upActivity(String id) {
        Activity zx=activityService.getActivityByid(id);
        if(zx.getState().equals("0"))
        {
            zx.setState("1");
        }
        else {
            zx.setState("0");
        }
        return activityService.update(zx);
    }


}

