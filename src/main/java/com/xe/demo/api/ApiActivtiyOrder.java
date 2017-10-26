package com.xe.demo.api;

import com.xe.demo.common.pojo.AjaxResult;
import com.xe.demo.model.ActivityType;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Administrator on 2017-10-24.
 */
@RestController
@RequestMapping("/api")
public class ApiActivtiyOrder {

  /*  @Authorization("需token")
    @ApiOperation(value="新增活动订单", notes="新增活动订单")
    @RequestMapping(value = "addActivtiyOrder", method = RequestMethod.POST)
    public AjaxResult getActivtiyallType (){
        JsonResult r = new JsonResult();
        List<ActivityType> list=activityTypeService.queryAll();
        AjaxResult aa=new AjaxResult();
        aa.setData(list);
        aa.setRetmsg("succ");
        return aa;
    }*/


}
