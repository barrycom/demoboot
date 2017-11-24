package com.xe.demo.api;

import com.xe.demo.common.Constant;
import com.xe.demo.common.pojo.AjaxResult;
import com.xe.demo.common.support.redis.IRedisService;
import com.xe.demo.model.Activity;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Condition;

import java.lang.reflect.Member;
import java.util.List;

/**
 * Created by Administrator on 2017-9-27.
 */
@RestController
public class ApiUserController {

    @ApiOperation(value="获取用户详细信息", notes="根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Integer", paramType = "path")
    @RequestMapping(value = "user/{id}", method = RequestMethod.GET)
    public ResponseEntity<JsonResult> getUserById (@PathVariable(value = "id") Integer id){
        JsonResult r = new JsonResult();

        return ResponseEntity.ok(r);
    }


    @ApiOperation(value="登录", notes="登录")
    @RequestMapping(value = "userLogin", method = RequestMethod.POST)
    public AjaxResult userLogin (String mobile,String password){

        AjaxResult aa=new AjaxResult();
  /*      String token=manager.createToken(mobile).toString();*/
       // aa.setData(manager.createToken(mobile));
        aa.setRetmsg("succ");
        return aa;
    }
}
