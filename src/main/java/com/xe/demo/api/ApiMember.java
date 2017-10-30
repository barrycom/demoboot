package com.xe.demo.api;

import com.fasterxml.jackson.annotation.JsonView;
import com.qiniu.util.StringUtils;
import com.xe.demo.common.pojo.AjaxResult;
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
    private DynamicTypeService dynamicTypeService;
    @Autowired
    private DynamicTypeMapper dynamicTypeMapper;




    //@Authorization("需token")
     @ApiOperation(value="根据ID获取用户信息", notes="根据ID获取用户信息")
     @RequestMapping(value = "getMember", method = RequestMethod.POST)
     @ApiImplicitParam(paramType="query", name = "memberId", value = "用户ID", required = true, dataType = "String")
    public AjaxResult getMember(@RequestParam(value = "memberId",required = false) String  memberId)
     {
         JsonResult r = new JsonResult();
         Condition condition=new Condition(Activity.class);
         condition.createCriteria().andCondition("id = "+memberId+"");
         AjaxResult aa=new AjaxResult();
         aa.setData(memberMapper.selectByExample(condition));
         aa.setRetmsg("succ");
         return aa;
    }

    //@Authorization("需token")
    @ApiOperation(value="修改名片", notes="修改名片")
    @RequestMapping(value = "updateMember", method = RequestMethod.POST)
    public AjaxResult updateMember(@ApiParam(value = "真实姓名", required = true) @RequestParam("name") String name,
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
        member.setName(name);
        member.setCorporatename(corporatename);
        member.setPersonalinfo(personalinfo);
        member.setWxno(wxno);
        member.setMobile(mobile);
        member.setEmail(email);

        member.setTrade(trade);
        Condition condition=new Condition(DynamicType.class);
        DynamicType industry=dynamicTypeService.queryOne(Integer.parseInt(trade));
        member.setTradename(industry.getDynamicname());

        member.setRegion(region);
        /*Condition condition2=new Condition(Regions.class);
        condition2.createCriteria().andCondition("id",region);
        Regions regions= regionsMapper.selectByPrimaryKey(condition2);*/
        String[] regionName=region.split(",");
        member.setRegionname(regionName[0]+regionName[1]);

        /*MemberInfo memberInfo=new MemberInfo();
        memberInfo.setMemberid(id);
        memberInfo = memberInfoMapper.selectOne(memberInfo);
        if(memberInfo == null){
            memberInfo.setRealname(realname);
            memberInfoService.insert(memberInfo);
        }else{
            memberInfo.setRealname(realname);
            memberInfoService.update(memberInfo);
        }
*/
        memberService.update(member);
        AjaxResult aa=new AjaxResult();
        aa.setData(memberService.queryOne(member));
        aa.setRetmsg("succ");
        return aa;
    }

    //@Authorization("需token")
    @ApiOperation(value="编辑主页", notes="编辑主页")
    @RequestMapping(value = "updateHomePage", method = RequestMethod.POST)
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
        aa.setData(memberService.queryOne(member));
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
        AjaxResult ajaxResult=new AjaxResult();
        ajaxResult.setData(memberService.save(member));
        ajaxResult.setRetmsg("success");
        return ajaxResult;
    }

    @ApiOperation(value="发送验证码", notes="发送验证码")
    @RequestMapping(value = "sendCode", method = RequestMethod.POST)
    public AjaxResult sendCode(@RequestBody Member member)
    {
        //模拟验证码发送
        Integer code=(int)((Math.random()*9+1)*1000);
        AjaxResult ajaxResult=new AjaxResult();
        ajaxResult.setData(code);
        ajaxResult.setRetmsg("success");
        return ajaxResult;
    }
}
