package com.xe.demo.api;

import com.xe.demo.common.pojo.AjaxResult;
import com.xe.demo.common.pojo.PageAjax;
import com.xe.demo.mapper.ActivityMapper;
import com.xe.demo.model.Activity;
import com.xe.demo.model.ActivityType;
import com.xe.demo.model.AuthRole;
import com.xe.demo.model.Member;
import com.xe.demo.service.ActivityService;
import com.xe.demo.service.ActivityTypeService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

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
    @Authorization("需token")
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

     @Authorization("需token")
     @ApiOperation(value="根据活动类型获取对应的活动", notes="根据活动类型获取对应的活动")
     @RequestMapping(value = "getActivtiyByType", method = RequestMethod.POST)
     @ApiImplicitParam(paramType="query", name = "typeid", value = "活动类型编号", required = true, dataType = "String")
    public AjaxResult getActivtiyByType(@RequestParam String  typeid)
     {
        JsonResult r = new JsonResult();
        Condition condition=new Condition(Activity.class);
        condition.createCriteria().andCondition("activitytype = "+typeid+"");
        condition.setOrderByClause("createtime desc");
        List<Activity> list = activityMapper.selectByExample(condition);
        AjaxResult aa=new AjaxResult();
        aa.setData(list);
        aa.setRetmsg("succ");
        return aa;
    }


    @Authorization("需token")
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
}
