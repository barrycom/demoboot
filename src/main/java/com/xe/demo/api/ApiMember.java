package com.xe.demo.api;

import com.fasterxml.jackson.annotation.JsonView;
import com.qiniu.util.StringUtils;
import com.xe.demo.common.pojo.AjaxResult;
import com.xe.demo.mapper.*;
import com.xe.demo.model.*;
import com.xe.demo.service.ActivityService;
import com.xe.demo.service.ActivityTypeService;
import com.xe.demo.service.MemberInfoService;
import com.xe.demo.service.MemberService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Condition;

import java.util.List;

/**
 * Created by Administrator on 2017-10-13.
 */
@RestController
@RequestMapping("/api")
public class ApiMember {

    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private RegionsMapper regionsMapper;
    @Autowired
    private IndustryMapper industryMapper;
    @Autowired
    private MemberInfoService memberInfoService;
    @Autowired
    private MemberInfoMapper memberInfoMapper;


     @Authorization("需token")
     @ApiOperation(value="根据ID获取用户信息", notes="根据ID获取用户信息")
     @RequestMapping(value = "getMember", method = RequestMethod.POST)
     @ApiImplicitParam(paramType="query", name = "memberId", value = "用户ID", required = true, dataType = "String")
    public AjaxResult getMember(@RequestParam String  memberId)
     {
         JsonResult r = new JsonResult();
         Condition condition=new Condition(Activity.class);
         condition.createCriteria().andCondition("id = "+memberId+"");
         AjaxResult aa=new AjaxResult();
         aa.setData(memberMapper.selectByExample(condition));
         aa.setRetmsg("succ");
         return aa;
    }

    @Authorization("需token")
    @ApiOperation(value="修改名片", notes="修改名片")
    @RequestMapping(value = "updateMember", method = RequestMethod.POST)
    @JsonView(Member.class)
    public AjaxResult updateMember(@ApiParam(value = "真实姓名", required = true) @RequestParam String realname,
                                   @ApiParam(value = "行业", required = true) @RequestParam String trade,
                                   @ApiParam(value = "公司名称", required = true) @RequestParam String corporatename,
                                   @ApiParam(value = "职位", required = true) @RequestParam String personalinfo,
                                   @ApiParam(value = "工作地区", required = true) @RequestParam String region,
                                   @ApiParam(value = "微信号", required = true) @RequestParam String wxno,
                                   @ApiParam(value = "手机号", required = true) @RequestParam String mobile,
                                   @ApiParam(value = "邮箱", required = true) @RequestParam String email,
                                   @ApiParam(value = "ID", required = true) @RequestParam String id)
    {
        Member member=new Member();
        member.setId(id);
        member.setCorporatename(corporatename);
        member.setPersonalinfo(personalinfo);
        member.setWxno(wxno);
        member.setMobile(mobile);
        member.setEmail(email);

        member.setTrade(trade);
        Condition condition=new Condition(Industry.class);
        condition.createCriteria().andCondition("id",trade);
        Industry industry=industryMapper.selectByPrimaryKey(condition);
        member.setTradename(industry.getIndustryname());

        member.setRegion(region);
        Condition condition2=new Condition(Regions.class);
        condition2.createCriteria().andCondition("id",region);
        Regions regions= regionsMapper.selectByPrimaryKey(condition2);
        member.setRegionname(regions.getRegionname());

        MemberInfo memberInfo=new MemberInfo();
        memberInfo.setMemberid(id);
        memberInfo = memberInfoMapper.selectOne(memberInfo);
        if(memberInfo == null){
            memberInfo.setRealname(realname);
            memberInfoService.insert(memberInfo);
        }else{
            memberInfo.setRealname(realname);
            memberInfoService.update(memberInfo);
        }

        memberService.update(member);
        AjaxResult aa=new AjaxResult();
        aa.setRetmsg("succ");
        return aa;
    }

    @Authorization("需token")
    @ApiOperation(value="编辑主页", notes="编辑主页")
    @RequestMapping(value = "updateHomePage", method = RequestMethod.POST)
    @JsonView(Member.class)
    public AjaxResult updateHomePage(@ApiParam(value = "个人介绍", required = true) @RequestParam String personalinfo,
                                   @ApiParam(value = "我的资源", required = true) @RequestParam String resources,
                                   @ApiParam(value = "ID", required = true) @RequestParam String id)
    {
        Member member=new Member();
        member.setId(id);
        member.setPersonalinfo(personalinfo);
        member.setResources(resources);

        memberService.update(member);
        AjaxResult aa=new AjaxResult();
        aa.setRetmsg("succ");
        return aa;
    }
}
