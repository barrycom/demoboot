package com.xe.demo.controller;

import com.qiniu.util.StringUtils;
import com.xe.demo.common.annotation.Authority;
import com.xe.demo.common.annotation.ControllerLog;
import com.xe.demo.common.pojo.AjaxResult;
import com.xe.demo.common.pojo.PageAjax;
import com.xe.demo.common.utils.AppUtil;
import com.xe.demo.common.utils.DateUtil;
import com.xe.demo.mapper.*;
import com.xe.demo.model.DynamicType;
import com.xe.demo.model.Member;
import com.xe.demo.model.MemberInfo;
import com.xe.demo.model.Regions;
import com.xe.demo.service.ChatService;
import com.xe.demo.service.MemberInfoService;
import com.xe.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tk.mybatis.mapper.entity.Condition;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017-10-9.
 */

@Controller
@RequestMapping("/admin/chat/")
public class ChatController extends BaseController {
    @Autowired
    private ChatService chatService;


    @Authority(opCode = "06", opName = "用户列表")
    @RequestMapping("mainPage")
    public String mainPage(Map<String, Object> map) {
        return "chat/main";
    }

    @ControllerLog("用户列表数据")
    @RequestMapping("queryPage")
    @ResponseBody
    @Authority(opCode = "06", opName = "交换名片用户列表数据")
    public PageAjax<HashMap> queryPage(PageAjax<HashMap> page, Member member) {
        PageAjax<HashMap> pl=chatService.queryList(page,member);
        return pl;
    }

}

