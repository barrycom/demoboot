package com.xe.demo.controller;

import com.qiniu.util.StringUtils;
import com.xe.demo.common.annotation.Authority;
import com.xe.demo.common.annotation.ControllerLog;
import com.xe.demo.common.pojo.AjaxResult;
import com.xe.demo.common.pojo.PageAjax;
import com.xe.demo.common.utils.AppUtil;
import com.xe.demo.common.utils.DateUtil;
import com.xe.demo.mapper.*;
import com.xe.demo.model.*;
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
@RequestMapping("/admin/member/")
public class MemberController extends BaseController {
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberInfoService memberInfoService;
    @Autowired
    private MemberInfoMapper memberInfoMapper;
    @Autowired
    private RegionsMapper regionsMapper;
    @Autowired
    private IndustryMapper industryMapper;
    @Autowired
    private DynamicTypeMapper dynamicTypeMapper;


    @Authority(opCode = "06", opName = "用户列表")
    @RequestMapping("mainPage")
    public String mainPage(Map<String, Object> map,Member member) {
        return "member/main";
    }

    @Authority(opCode = "06", opName = "会员列表")
    @RequestMapping("vipMainPage")
    public String vipMainPage(Map<String, Object> map,Member member) {
        return "member/vipmember";
    }

    @Authority(opCode = "06", opName = "冻结列表")
    @RequestMapping("blockMainPage")
    public String blockMainPage(Map<String, Object> map,Member member) {
        return "member/blockmember";
    }

    @ControllerLog("用户列表数据")
    @RequestMapping("queryPage/{type}")
    @ResponseBody
    @Authority(opCode = "06", opName = "用户列表数据")
    public PageAjax<Member> queryPage(PageAjax<Member> page, Member member,@PathVariable("type") String type) {
        if("2".equals(type)){
            member.setIshy("1");
        }else if("3".equals(type)){
            member.setIsblock("1");
        }
        PageAjax<Member> memberList= null;
        if(StringUtils.isNullOrEmpty(member.getName()) && StringUtils.isNullOrEmpty(member.getMobile())){
            memberList=memberService.queryPage(page, member);
        }else{
            memberList=memberService.querySearchPage(page, member);
        }
        return memberList;
    }

    @Authority(opCode = "06", opName = "查看/升级/修改用户")
    @RequestMapping("updateMemberPage")
    public String updateMemberPage(String memberid,String type, Map<String, Object> map) {
        Member member = memberService.queryByID(memberid);
        member.setType(type);
        List<DynamicType> industry = dynamicTypeMapper.selectAll();
        map.put("trade",industry);
        map.put("member", member);
        return "member/edit";
    }

    @Authority(opCode = "06", opName = "选择行业")
    @RequestMapping("selectIndustryNamePage")
    public String selectIndustryNamePage(Map<String, Object> map) {
        List<DynamicType> industry = dynamicTypeMapper.selectAll();
        map.put("list",industry);
        return "member/selectIndustryNamePage";
    }

    @Authority(opCode = "06", opName = "选择地区")
    @ResponseBody
    @RequestMapping("getCity")
    public AjaxResult getCity(Map<String, Object> map,String name) {
        AjaxResult ajaxResult=new AjaxResult();
        Regions region = new Regions();
        Condition condition=new Condition(Regions.class);
        condition.createCriteria().andCondition("regiontype !=1");
        regionsMapper.selectByExample(condition);
        regionsMapper.select(region).stream().forEach(i->{
            if(name.contains(i.getRegionname())) {
                ajaxResult.setData(i);   ;
            }
        });
        return ajaxResult;
    }
   /* public static void main(String [] args){
        String s = "安微芜湖";
        String v = "芜湖";
        s.contains(v);
    }*/


    @ControllerLog("屏蔽/解除屏蔽 用户操作")
    @RequestMapping("blockMember/{id}")
    @ResponseBody
    @Authority(opCode = "06", opName = "屏蔽/解除屏蔽 用户操作")
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

    @ControllerLog("修改用户")
    @RequestMapping("edit")
    @ResponseBody
    @Authority(opCode = "06", opName = "修改用户")
    public AjaxResult edit(Member member) {
        Member m=memberService.queryByID(member.getId());
        if("2".equals(member.getType())){
            m.setIshy("1");
            m.setViptimestart(member.getViptimestart());
            m.setViptimeend(member.getViptimeend());
        }else {
            m.setName(member.getName());
            m.setTrade(member.getTrade());
            m.setTradename(member.getTradename());
            m.setCorporatename(member.getCorporatename());
            m.setProfession(member.getProfession());
            m.setRegion(member.getRegion());
            m.setRegionname(member.getRegionname());
            m.setWxno(member.getWxno());
            m.setMobile(member.getMobile());
            m.setEmail(member.getEmail());
        }
        return memberService.update(m);
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

    @ControllerLog("审核通过")
    @RequestMapping("memberInfoPass/{id}")
    @ResponseBody
    @Authority(opCode = "06", opName = "审核通过")
    public AjaxResult memberInfoPass(@PathVariable("id") String id) {
       MemberInfo memberInfo=memberInfoService.queryByID(id);
       Member member = new Member();
       member.setState("1");
       member.setId(memberInfo.getMemberid());
       memberService.update(member);
       memberInfo.setId(id);
       memberInfo.setIspass("1");
       memberInfo.setRegtime(DateUtil.getCurDate());
        return memberInfoService.update(memberInfo);
    }

    @ControllerLog("拒绝审核页面")
    @RequestMapping("memberInfoRefusePage/{id}")
    @Authority(opCode = "06", opName = "屏蔽用户")
    public String memberInfoRefusePage(Map<String, Object> map,@PathVariable("id") String id){
        map.put("id",id);
        return "member/refusePage";
    }

    @ControllerLog("拒绝通过")
    @RequestMapping("memberInfoRefuse")
    @ResponseBody
    @Authority(opCode = "06", opName = "屏蔽用户")
    public AjaxResult memberInfoPass(MemberInfo memberInfo) {
        memberInfo.setIspass("2");
        memberInfo.setRegtime(DateUtil.getCurDate());
        return memberInfoService.update(memberInfo);
    }
}

