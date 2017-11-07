package com.xe.demo.controller;

import com.xe.demo.common.annotation.Authority;
import com.xe.demo.common.annotation.ControllerLog;
import com.xe.demo.common.pojo.PageAjax;
import com.xe.demo.mapper.MemberMapper;
import com.xe.demo.model.Member;
import com.xe.demo.model.UserCollecTindustry;
import com.xe.demo.service.ChatService;
import com.xe.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tk.mybatis.mapper.entity.Condition;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017-10-9.
 */

@Controller
@RequestMapping("/admin/corporate/")
public class CorporateController extends BaseController {
    @Autowired
    private ChatService chatService;
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private MemberService memberService;


    @Authority(opCode = "06", opName = "公司列表")
    @RequestMapping("mainPage")
    public String mainPage(Map<String, Object> map) {
        return "corporate/main";
    }

    @ControllerLog("公司列表数据")
    @RequestMapping("queryPage")
    @ResponseBody
    @Authority(opCode = "06", opName = "交换名片用户列表数据")
    public PageAjax<HashMap> queryPage(PageAjax<HashMap> page, Member member) {
        PageAjax<HashMap> pl=chatService.queryCorporateList(page,member);
        return pl;
    }

    @Authority(opCode = "06", opName = "公司成员页")
    @RequestMapping("mainMemberPage")
    public String mainMemberPage(Map<String, Object> map,String name) {
        Condition condition=new Condition(Member.class);
        condition.createCriteria().andCondition("corporatename= '"+name+"'");
        map.put("member",memberMapper.selectByExample(condition).get(0));
        return "corporate/mainInfo";
    }

    @ControllerLog("公司成员数据")
    @RequestMapping("queryMemberPage")
    @ResponseBody
    @Authority(opCode = "06", opName = "交换名片用户列表数据")
    public PageAjax<Member> queryMemberPage(PageAjax<Member> page, Member member) {
        PageAjax<Member> pl=memberService.queryPage(page,member);
        return pl;
    }

}

