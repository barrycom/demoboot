package com.xe.demo.controller;

import com.qiniu.util.StringUtils;
import com.xe.demo.common.annotation.Authority;
import com.xe.demo.common.annotation.ControllerLog;
import com.xe.demo.common.pojo.AjaxResult;
import com.xe.demo.common.pojo.PageAjax;
import com.xe.demo.common.utils.AppUtil;
import com.xe.demo.mapper.MemberInfoMapper;
import com.xe.demo.mapper.MemberMapper;
import com.xe.demo.model.Activity;
import com.xe.demo.model.Member;
import com.xe.demo.model.MemberInfo;
import com.xe.demo.service.MemberInfoService;
import com.xe.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tk.mybatis.mapper.entity.Condition;

import java.util.Map;

/**
 * Created by Administrator on 2017-10-9.
 */

@Controller
@RequestMapping("/admin/member/")
public class MemberController extends BaseController {
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberInfoService memberInfoService;
    @Autowired
    private MemberInfoMapper memberInfoMapper;


    @Authority(opCode = "06", opName = "用户列表")
    @RequestMapping("mainPage")
    public String mainPage(Map<String, Object> map,Member member) {
      /*  List<Member> list = memberService.queryList(member);
        map.put("list", list);*/
        return "member/main";
    }

    @ControllerLog("用户列表数据")
    @RequestMapping("queryPage")
    @ResponseBody
    @Authority(opCode = "06", opName = "用户列表数据")
    public PageAjax<Member> queryPage(PageAjax<Member> page, Member member) {
        PageAjax<Member> memberList= null;
        if(!StringUtils.isNullOrEmpty(member.getName()) || !StringUtils.isNullOrEmpty(member.getMobile())){
            memberList=memberService.queryPage(page, member);
        }else{
            memberList=memberService.querySearchPage(page, member);
        }
        return memberList;
    }

    @ControllerLog("屏蔽用户")
    @RequestMapping("blockMember/{id}")
    @ResponseBody
    @Authority(opCode = "06", opName = "屏蔽用户")
    public AjaxResult blockMember(@PathVariable("id") String id) {
        Member member=new Member();
        member.setId(id);
        member=memberService.queryOne(member);
        if(member.getIsblock() .equals("1")){
            member.setIsblock("0");
        }else{
            member.setIsblock("1");
        }
        return memberService.update(member);
    }

    @Authority(opCode = "06", opName = "审核列表")
    @RequestMapping("mainMemberInfoPage")
    public String memberInfo(Map<String, Object> map,MemberInfo memberInfo) {
        return "member/verifymember";
    }

    @ControllerLog("审核列表数据")
    @RequestMapping("queryMemberInfoPage")
    @ResponseBody
    @Authority(opCode = "06", opName = "审核列表数据")
    public PageAjax<MemberInfo> memberInfoQueryPage(PageAjax<MemberInfo> page, MemberInfo memberInfo) {

        Condition condition=new Condition(MemberInfo.class);
        if(!StringUtils.isNullOrEmpty(memberInfo.getRealname())){
            memberInfo.setRealname("%"+memberInfo.getRealname()+"%");
            condition.createCriteria().andLike("realname",memberInfo.getRealname());
        }
        if(!StringUtils.isNullOrEmpty(memberInfo.getIspass())){
            memberInfo.setIspass("ispass="+memberInfo.getIspass()+"");
            condition.createCriteria().andCondition(memberInfo.getIspass());
        }
        condition.setOrderByClause("createtime");
        return AppUtil.returnPage(memberInfoMapper.selectByExample(condition));
    }


}

