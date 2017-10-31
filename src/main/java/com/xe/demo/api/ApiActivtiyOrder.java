package com.xe.demo.api;

import com.xe.demo.common.pojo.AjaxResult;
import com.xe.demo.model.ActivityOrder;
import com.xe.demo.model.ActivityType;
import com.xe.demo.service.ActivityOrderService;
import com.xe.demo.service.ActivityService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.bouncycastle.asn1.x500.style.RFC4519Style.member;

/**
 * Created by Administrator on 2017-10-24.
 */
@RestController
@RequestMapping("/api")
public class ApiActivtiyOrder {

    @Autowired
    private ActivityOrderService activityOrderService;
    @Authorization("需token")
    @ApiOperation(value="新增活动订单", notes="新增活动订单")
    @RequestMapping(value = "addActivtiyOrder", method = RequestMethod.POST)
    public AjaxResult getActivtiyallType (@RequestBody ActivityOrder activityOrder){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        /*activityOrder.setBuytime(df.format(new Date()));*/
        String orderNo="wx"+activityOrder.getUserid()+"_"+System.currentTimeMillis();
        activityOrder.setId(orderNo);
       // activityOrder.setPaymemo("微信支付");
        activityOrder.setIszs("0");
     //应该是未支付目前假装支付了   activityOrder.setStatus("0");
        activityOrder.setStatus("1");

        activityOrder.setPaymoney(activityOrder.getOrdermoney());//此处真实不写
        activityOrder.setBuytime(df.format(new Date()));//此处真实不写
        activityOrder.setCreatetime(df.format(new Date()));
        activityOrderService.save(activityOrder);
        AjaxResult ajaxResult=new AjaxResult();
        ajaxResult.setData(orderNo);
        ajaxResult.setRetmsg("success");
        //后面调用支付的。（暂时没有）
        return ajaxResult;

    }



}
