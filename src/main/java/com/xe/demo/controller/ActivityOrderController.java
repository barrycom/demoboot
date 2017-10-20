package com.xe.demo.controller;

import com.xe.demo.common.annotation.Authority;
import com.xe.demo.common.annotation.ControllerLog;
import com.xe.demo.common.pojo.AjaxResult;
import com.xe.demo.common.pojo.PageAjax;
import com.xe.demo.model.Activity;
import com.xe.demo.model.ActivityOrder;
import com.xe.demo.service.ActivityOrderService;
import com.xe.demo.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017-10-18.
 */
@Controller
@RequestMapping("/admin/activityOrder/")
public class ActivityOrderController extends BaseController {
    @Autowired
    private ActivityOrderService activityOrderService;
    @Autowired
    private ActivityService activityService;


    @Authority(opCode = "06", opName = "订单查询列表")
    @RequestMapping("mainPage")
    public String mainPage(Map<String, Object> map) {
        return "activityOrder/main";
    }


    @ControllerLog("查询列表")
    @RequestMapping("queryPage")
    @ResponseBody
    @Authority(opCode = "06", opName = "查询列表")
    public PageAjax<ActivityOrder> queryPage(PageAjax<ActivityOrder> page, ActivityOrder activityOrder) {
        return activityOrderService.queryPage(page, activityOrder);
    }



    @Authority(opCode = "06", opName = "修改订单")
    @RequestMapping("updateActivityOrderPage")
    public String updateActivityOrderPage(String id, Map<String, Object> map) {
        ActivityOrder activitOrder = activityOrderService.selectOneById(id);
        map.put("activityOrder", activitOrder);
        return "activityOrder/edit";
    }

    @ControllerLog("修改订单信息")
    @RequestMapping("edit")
    @ResponseBody
    @Authority(opCode = "06", opName = "修改活动")
    public AjaxResult edit(ActivityOrder activityOrder,String iszs) {
        if(iszs!="")
        {
            activityOrder.setIszs("1");
            activityOrder.setStatus("1");
            activityOrder.setPaymemo(iszs);
        }
        return activityOrderService.update(activityOrder);
    }

}
