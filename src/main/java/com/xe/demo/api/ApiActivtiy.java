package com.xe.demo.api;

import com.github.pagehelper.page.PageMethod;
import com.xe.demo.common.pojo.AjaxResult;
import com.xe.demo.common.pojo.PageAjax;
import com.xe.demo.common.utils.OpenIdUtil;
import com.xe.demo.mapper.ActivityMapper;
import com.xe.demo.mapper.MemberViewActivityMapper;
import com.xe.demo.model.*;
import com.xe.demo.service.ActivityService;
import com.xe.demo.service.ActivityTypeService;
import com.xe.demo.service.MemberService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import net.sf.json.JSONObject;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2017-10-13.
 */
@RestController
@RequestMapping("/api")
public class ApiActivtiy {

    @Autowired
    private ActivityTypeService activityTypeService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private ActivityMapper activityMapper;

    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberViewActivityMapper memberViewActivityMapper;
    /*@Authorization("需token")*/
    @ApiOperation(value="获取所有的活动类型", notes="获取所有的活动类型")
    @RequestMapping(value = "getActivtiyallType", method = RequestMethod.POST)
    public AjaxResult getActivtiyallType (){
        JsonResult r = new JsonResult();
        List<ActivityType> list=activityTypeService.queryAll();
        AjaxResult aa=new AjaxResult();
        aa.setData(list);
        aa.setRetmsg("succ");
        return aa;
    }

/*     @Authorization("需token")*/
     @ApiOperation(value="根据活动类型获取对应的活动", notes="根据活动类型获取对应的活动")
     @RequestMapping(value = "getActivtiyByType", method = RequestMethod.POST)
     @ApiImplicitParam(paramType="query", name = "typeid",  value = "活动类型编号", required = true, dataType = "String")
    public AjaxResult getActivtiyByType(@RequestParam String  typeid)
     {


       //  RowBounds rowBounds = new RowBounds(pageNo, pageSize);
         /*}
         else {
             RowBounds rowBounds = new RowBounds();
         }*/
        JsonResult r = new JsonResult();
        Condition condition=new Condition(Activity.class);
        condition.createCriteria().andCondition("activitytype = '"+typeid+"'").andCondition("state = '1'");
        condition.setOrderByClause("createtime desc");

      //   PageMethod.startPage(1, 99999);
        List<Activity> list = activityMapper.selectByExample(condition);
        AjaxResult aa=new AjaxResult();
        aa.setData(list);
        aa.setRetmsg("succ");
        return aa;
    }

    //@Authorization("需token")
    @ApiOperation(value="根据活动id获取活动详情", notes="根据活动id获取活动详情")
    @RequestMapping(value = "getActivtiyById", method = RequestMethod.POST)
    @ApiImplicitParam(paramType="query", name = "id", value = "活动编号", required = true, dataType = "String")
    public AjaxResult getActivtiyById(@RequestParam String  id,@RequestParam String  openId)
    {

        Member mb=memberService.queryByID(openId);
        if(mb!=null) {
            MemberViewActivity memberViewActivity=new MemberViewActivity();
            memberViewActivity.setUserid(openId);
            memberViewActivity.setActivityid(id);
           List<MemberViewActivity> m= memberViewActivityMapper.select(memberViewActivity);
           if(m.size()==0) {

               memberViewActivity.setId(UUID.randomUUID().toString());
               SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
               memberViewActivity.setViewtime(df.format(new Date()));
               memberViewActivityMapper.insert(memberViewActivity);
           }


        }
        JsonResult r = new JsonResult();
        Activity activity = activityService.getActivityByid(id);
        AjaxResult aa = new AjaxResult();
        aa.setData(activity);
        aa.setRetmsg("succ");
        return aa;
    }



   // @Authorization("需token")
    @ApiOperation(value="根据活动获取活动感兴趣的人", notes="根据活动获取活动感兴趣的人")
    @RequestMapping(value = "getUserByActivtiyId", method = RequestMethod.POST)
    @ApiImplicitParam(paramType="query", name = "activtiyid", value = "活动编号", required = true, dataType = "String")
    public AjaxResult getUserByActivtiyId(@RequestParam String  activtiyid)
    {
        JsonResult r = new JsonResult();
        List<Member> list = activityMapper.getUserByActivtiyId(activtiyid);
        AjaxResult aa=new AjaxResult();
        aa.setData(list);
        aa.setRetmsg("succ");
        return aa;
    }


    @RequestMapping(value = "getUserOpenid", method = RequestMethod.POST)
    @ApiImplicitParam(paramType="query", name = "code", value = "code", required = true, dataType = "String")
    public AjaxResult getUserOpenid(@RequestParam String  code)
    {
        AjaxResult aa=new AjaxResult();
        String openid = OpenIdUtil.oauth2GetOpenid(code);
        aa.setData(openid);
        aa.setRetmsg("succ");
        return aa;
    }

    @RequestMapping(value = "getSessionKeyOropenid", method = RequestMethod.POST)
    @ApiImplicitParam(paramType="query", name = "code", value = "code", required = true, dataType = "String")
    public AjaxResult  getSessionKeyOropenid(String code){
        //微信端登录code值
        AjaxResult aa=new AjaxResult();
        JSONObject jsonObject  = OpenIdUtil.getSessionKeyOropenid(code);
        aa.setData(jsonObject);
        aa.setRetmsg("succ");
        return aa;

    }


    @RequestMapping(value = "getUserInfo", method = RequestMethod.POST)
    public AjaxResult  getUserInfo(String encryptedData,String sessionKey,String iv){
        //微信端登录code值
        AjaxResult aa=new AjaxResult();
        JSONObject jsonObject  = OpenIdUtil.getUserInfo(encryptedData,sessionKey,iv);
        aa.setData(jsonObject);
        aa.setRetmsg("succ");
        return aa;

    }



}
