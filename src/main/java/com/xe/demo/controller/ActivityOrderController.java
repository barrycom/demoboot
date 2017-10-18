package com.xe.demo.controller;

import com.xe.demo.common.annotation.Authority;
import com.xe.demo.model.Activity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017-10-18.
 */
@Controller
@RequestMapping("/admin/activityOrder/")
public class ActivityOrderController {

    @Authority(opCode = "06", opName = "订单查询列表")
    @RequestMapping("mainPage")
    public String mainPage(Map<String, Object> map) {
        return "activityOrder/main";
    }
}
