package com.xe.demo.api;

import com.fasterxml.jackson.annotation.JsonView;
import com.qiniu.util.StringUtils;
import com.xe.demo.common.pojo.AjaxResult;
import com.xe.demo.common.pojo.SzmData;
import com.xe.demo.common.utils.ChineseCharToEn;
import com.xe.demo.common.utils.DateUtil;
import com.xe.demo.mapper.*;
import com.xe.demo.model.*;
import com.xe.demo.service.*;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    private ActivityMapper activityMapper;
    @Autowired
    private MemberInfoService memberInfoService;
    @Autowired
    private MemberInfoMapper memberInfoMapper;
    @Autowired
    private DynamicTypeMapper dynamicTypeMapper;
    @Autowired
    private DynamicTypeService dynamicTypeService;



    //@Authorization("需token")
     @ApiOperation(value="根据ID获取用户信息", notes="根据ID获取用户信息")
     @RequestMapping(value = "getMember", method = RequestMethod.POST)
     @ApiImplicitParam(paramType="query", name = "memberId", value = "用户ID", required = true, dataType = "String")
    public AjaxResult getMember(@RequestParam(value = "memberId",required = false) String  memberId)
     {
         JsonResult r = new JsonResult();
         Condition condition=new Condition(Activity.class);
         condition.createCriteria().andCondition("id = '"+memberId+"'");
         AjaxResult aa=new AjaxResult();
         aa.setData(memberMapper.selectByExample(condition));
         aa.setRetmsg("succ");
         return aa;
    }

    //@Authorization("需token")
    @ApiOperation(value="修改名片", notes="修改名片")
    @RequestMapping(value = "updateMember", method = RequestMethod.POST)
    @JsonView(Member.class)
    public AjaxResult updateMember(@ApiParam(value = "真实姓名", required = true) @RequestParam("realname") String realname,
                                   @ApiParam(value = "行业", required = true) @RequestParam("trade") String trade,
                                   @ApiParam(value = "公司名称", required = true) @RequestParam("corporatename") String corporatename,
                                   @ApiParam(value = "职位", required = true) @RequestParam("personalinfo") String personalinfo,
                                   @ApiParam(value = "工作地区", required = true) @RequestParam("region") String region,
                                   @ApiParam(value = "微信号", required = true) @RequestParam("wxno") String wxno,
                                   @ApiParam(value = "手机号", required = true) @RequestParam("mobile") String mobile,
                                   @ApiParam(value = "邮箱", required = true) @RequestParam("email") String email,
                                   @ApiParam(value = "ID", required = true) @RequestParam("id") String id)
    {
        Member member=new Member();
        member.setId(id);
        member.setCorporatename(corporatename);
        member.setPersonalinfo(personalinfo);
        member.setWxno(wxno);
        member.setMobile(mobile);
        member.setEmail(email);

        member.setTrade(trade);
        Condition condition=new Condition(DynamicType.class);
        condition.createCriteria().andCondition("id",trade);
        DynamicType industry=dynamicTypeMapper.selectByPrimaryKey(condition);
        member.setTradename(industry.getDynamicname());

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

    //@Authorization("需token")
    @ApiOperation(value="编辑主页", notes="编辑主页")
    @RequestMapping(value = "updateHomePage", method = RequestMethod.POST)
    @JsonView(Member.class)
    public AjaxResult updateHomePage(@ApiParam(value = "个人介绍", required = true) @RequestParam("personalinfo") String personalinfo,
                                   @ApiParam(value = "我的资源", required = true) @RequestParam("resources") String resources,
                                   @ApiParam(value = "ID", required = true) @RequestParam("id") String id)
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

    //@Authorization("需token")
    @ApiOperation(value="获取用户选择的行业", notes="获取用户选择的行业")
    @ResponseBody
    @RequestMapping(value = "getMemberDynamicType", method = RequestMethod.POST)
    @ApiImplicitParam(paramType="query", name = "memberId", value = "用户ID", required = true, dataType = "String")
    public AjaxResult getMemberDynamicType (@RequestParam(value = "memberId",required = false) String  memberId){//int pageNo,int pageSize
        List<DynamicType> list=dynamicTypeService.getMemberDynamicType(memberId);
        list.stream().forEach(i->{
            if(StringUtils.isNullOrEmpty(i.getUserId())){
                i.setChosen(false);
            }else {
                i.setChosen(true);
            }
        });
        AjaxResult ajaxResult=new AjaxResult();
        ajaxResult.setData(list);
        ajaxResult.setRetmsg("success");
        return ajaxResult;
    }

    //@Authorization("需token")
    @ApiOperation(value="根据用户ID获取活动", notes="根据用户ID获取活动")
    @RequestMapping(value = "getActivtiyByMemberId", method = RequestMethod.POST)
    @ApiImplicitParam(paramType="query", name = "memberId", value = "用户ID", required = true, dataType = "String")
    public AjaxResult getActivtiyByMemberId(@RequestParam String  memberId)
    {
        JsonResult r = new JsonResult();
        List<Activity> list = activityMapper.getActivtiyByMemberId(memberId);
        list.stream().forEach(i->{
            if(!StringUtils.isNullOrEmpty(i.getBuynum())){
                i.setFinishimg("/images/my/my_act_icon_finish.png");
            }
        });
        AjaxResult aa=new AjaxResult();
        aa.setData(list);
        aa.setRetmsg("succ");
        return aa;
    }


    @ApiOperation(value="注册用户", notes="注册用户")
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public AjaxResult register(@RequestBody Member member)
    {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        member.setRegtime(df.format(new Date()));
        member.setIsblock("0");
        member.setOpenid(member.getId());
        member.setIshy("0");
        member.setState("0");
        AjaxResult ajaxResult=new AjaxResult();
        ajaxResult.setData(memberService.save(member));
        ajaxResult.setRetmsg("success");
        return ajaxResult;
    }

    @ApiOperation(value="发送验证码", notes="发送验证码")
    @RequestMapping(value = "sendCode", method = RequestMethod.POST)
    public AjaxResult sendCode()
    {
        //模拟验证码发送
        Integer code=(int)((Math.random()*9+1)*1000);
        AjaxResult ajaxResult=new AjaxResult();
        ajaxResult.setData(code);
        ajaxResult.setRetmsg("success");
        return ajaxResult;
    }

    @ApiOperation(value="获取所有用户除了自己", notes="获取所有用户除了自己")
    @RequestMapping(value = "getMemberListNoMy", method = RequestMethod.POST)
    @ApiImplicitParam(paramType="query", name = "memberId", value = "用户ID", required = true, dataType = "String")
    public AjaxResult getMemberListNoMy(@RequestParam(value = "memberId",required = false) String  memberId)
    {
        JsonResult r = new JsonResult();
        Condition condition=new Condition(Activity.class);
        condition.createCriteria().andCondition("id != '"+memberId+"'");
        AjaxResult aa=new AjaxResult();
        List<Member> memberList=memberMapper.selectByExample(condition);
        List<SzmData> szmDataList=new ArrayList<SzmData>();
        SzmData A=new SzmData("A",null);
        SzmData B=new SzmData("B",null);
        SzmData C=new SzmData("C",null);
        SzmData D=new SzmData("D",null);
        SzmData E=new SzmData("E",null);
        SzmData F=new SzmData("F",null);
        SzmData G=new SzmData("G",null);
        SzmData H=new SzmData("H",null);
        SzmData I=new SzmData("I",null);
        SzmData J=new SzmData("J",null);
        SzmData K=new SzmData("K",null);
        SzmData L=new SzmData("L",null);
        SzmData M=new SzmData("M",null);
        SzmData N=new SzmData("N",null);
        SzmData O=new SzmData("O",null);
        SzmData P=new SzmData("P",null);
        SzmData Q=new SzmData("Q",null);
        SzmData R=new SzmData("R",null);
        SzmData S=new SzmData("S",null);
        SzmData T=new SzmData("T",null);
        SzmData U=new SzmData("U",null);
        SzmData V=new SzmData("V",null);
        SzmData W=new SzmData("W",null);
        SzmData X=new SzmData("X",null);
        SzmData Y=new SzmData("Y",null);
        SzmData Z=new SzmData("Z",null);
        szmDataList.add(A);
        szmDataList.add(B);
        szmDataList.add(C);
        szmDataList.add(D);
        szmDataList.add(E);
        szmDataList.add(F);
        szmDataList.add(G);
        szmDataList.add(H);
        szmDataList.add(I);
        szmDataList.add(J);
        szmDataList.add(K);
        szmDataList.add(L);
        szmDataList.add(M);
        szmDataList.add(N);
        szmDataList.add(O);
        szmDataList.add(P);

        szmDataList.add(Q);
        szmDataList.add(R);
        szmDataList.add(S);
        szmDataList.add(T);

        szmDataList.add(U);
        szmDataList.add(V);
        szmDataList.add(W);
        szmDataList.add(X);

        szmDataList.add(Y);
        szmDataList.add(Z);

        for(int j=0;j<szmDataList.size();j++)
        {
            List<Member> members=new ArrayList<Member>();
            for(int i=0;i<memberList.size();i++) {
                ChineseCharToEn cte = new ChineseCharToEn();
                String szm=cte.getAllFirstLetter(memberList.get(i).getName().substring(0,1).toUpperCase());

                if (szmDataList.get(j).getAlphabet().equals(szm)) {
                    members.add(memberList.get(i));
                }


            }
            szmDataList.get(j).setMembers(members);

        }


        aa.setData(szmDataList);
        aa.setRetmsg("succ");
        return aa;
    }


}
