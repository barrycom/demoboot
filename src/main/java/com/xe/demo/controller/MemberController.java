package com.xe.demo.controller;

import com.xe.demo.common.annotation.Authority;
import com.xe.demo.common.annotation.ControllerLog;
import com.xe.demo.common.pojo.PageAjax;
import com.xe.demo.model.Activity;
import com.xe.demo.model.Member;
import com.xe.demo.service.ActivityService;
import com.xe.demo.service.MemberService;
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
@RequestMapping("/admin/member/")
public class MemberController extends BaseController {
    @Autowired
    private MemberService memberService;


    @Authority(opCode = "0401", opName = "无需验证")
    @RequestMapping("mainPage")
    public String mainPage(Map<String, Object> map,Member member) {
      /*  List<Member> list = memberService.queryList(member);
        map.put("list", list);*/
        return "member/main";
    }

    @ControllerLog("查询用户列表")
    @RequestMapping("queryPage")
    @ResponseBody
    @Authority(opCode = "0401", opName = "无需验证")
    public PageAjax<Member> queryPage(PageAjax<Member> page, Member member) {
        return memberService.queryPage(page, member);
    }


}

