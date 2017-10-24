package com.xe.demo.api;

import com.xe.demo.common.pojo.AjaxResult;
import com.xe.demo.common.pojo.PageAjax;
import com.xe.demo.common.support.redis.IRedisService;
import com.xe.demo.mapper.UserCollecTiondyMapper;
import com.xe.demo.model.*;
import com.xe.demo.service.*;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import io.swagger.models.auth.In;
import org.apache.commons.collections.map.HashedMap;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017-9-27.
 */
@RestController
@RequestMapping("/api/content/")
public class ApiContentController {

    @Autowired
    private MemBerDynamicwzService memBerDynamicwzService;
    @Autowired
    private UserCollecTiondyService userCollecTiondyService;
    @Autowired
    private DynamicwzImgService dynamicwzImgService;
    @Autowired
    private DynamicTypeService dynamicTypeService;
    @Autowired
    private UserCollecTindustryService userCollecTindustryService;

    @Authorization("需token")
    @ApiOperation(value="获取需求广场数据", notes="获取需求广场数据")
    @ResponseBody
    @RequestMapping(value = "otherContent", method = RequestMethod.POST)
    public AjaxResult otherContent (Integer userid,Integer dynamictype_id,Integer pageNo,Integer pageSize){//String begintime,String endtime,
        PageAjax<MemBerDynamicwz> page=new PageAjax<MemBerDynamicwz>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        Map map=new HashedMap();
        map.put("dynamictype_id",dynamictype_id);
      /*  map.put("begintime",begintime);
        map.put("endtime",endtime);*/
        List<Map<String, String>> list=memBerDynamicwzService.queryneed(map,page);
        for (Map li:list) {
            UserCollecTiondy userCollecTiondy=new UserCollecTiondy();
            userCollecTiondy.setUserid(userid);
            userCollecTiondy.setDynamicwzid(Integer.parseInt(li.get("id").toString()));
            Integer uctd=userCollecTiondyService.queryCount(userCollecTiondy);
            if(uctd!=0){
                li.put("isinterest","0");
            }else{
                li.put("isinterest","1");
            }
        }

        AjaxResult ajaxResult=new AjaxResult();
        ajaxResult.setData(list);
        ajaxResult.setRetmsg("success");
        return ajaxResult;
    }

    @Authorization("需token")
    @ApiOperation(value="感兴趣动态", notes="感兴趣动态")
    @ResponseBody
    @RequestMapping(value = "interest", method = RequestMethod.POST)
    public AjaxResult interest (Integer user_id,Integer dynamic_id){
        UserCollecTiondy userCollecTiondy=new UserCollecTiondy();
        userCollecTiondy.setUserid(user_id);
        userCollecTiondy.setDynamicwzid(dynamic_id);
        AjaxResult ajaxResult=userCollecTiondyService.save(userCollecTiondy);

        return ajaxResult;
    }

    @Authorization("需token")
    @ApiOperation(value="取消感兴趣动态", notes="感兴趣动态")
    @ResponseBody
    @RequestMapping(value = "notinterest", method = RequestMethod.POST)
    public AjaxResult interest (Integer id){
        AjaxResult ajaxResult=userCollecTiondyService.deleteByID(id);

        return ajaxResult;
    }

    @Authorization("需token")
    @ApiOperation(value="获取我的动态数据", notes="获取我的动态数据")
    @ResponseBody
    @RequestMapping(value = "myContent", method = RequestMethod.POST)
    public AjaxResult myContent (Integer userid,int pageNo,int pageSize){
        PageAjax<MemBerDynamicwz> page=new PageAjax<MemBerDynamicwz>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        List<Map<String, String>> list=memBerDynamicwzService.querymycontent(page,userid);
        AjaxResult ajaxResult=new AjaxResult();
        ajaxResult.setData(list);
        ajaxResult.setRetmsg("success");
        return ajaxResult;
    }


    @Authorization("需token")
    @ApiOperation(value="发布动态", notes="发布动态")
    @ResponseBody
    @RequestMapping(value = "saveContent", method = RequestMethod.POST)
    public AjaxResult saveContent (Integer userid,String dynamicwz,String imgurl,String dynamicid){
        //SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        MemBerDynamicwz memBerDynamicwz=new MemBerDynamicwz();
        memBerDynamicwz.setUserid(userid.toString());
        memBerDynamicwz.setDynamicwz(dynamicwz);
        memBerDynamicwz.setDynamicid(dynamicid);
        memBerDynamicwz.setState("0");
       // memBerDynamicwz.setCreatetime(df.format(new Date()));

        AjaxResult ajaxResult=memBerDynamicwzService.savecontent(memBerDynamicwz);
        if(ajaxResult.getRetcode()!=1){
            ajaxResult.setRetmsg("false");
            return ajaxResult;
        }

        DynamicwzImg dynamicwzImg=new DynamicwzImg();
        dynamicwzImg.setDynamicwzid(memBerDynamicwz.getId().toString());
        dynamicwzImg.setImgurl(imgurl);
        ajaxResult=dynamicwzImgService.save(dynamicwzImg);
        if(ajaxResult.getRetcode()!=1){
            ajaxResult.setRetmsg("false");
            return ajaxResult;
        }

        return ajaxResult;
    }


    @Authorization("需token")
    @ApiOperation(value="获取行业分类", notes="获取行业分类")
    @ResponseBody
    @RequestMapping(value = "getdynamictype", method = RequestMethod.POST)
    public AjaxResult getdynamictype (){//int pageNo,int pageSize
       /* PageAjax<DynamicType> page=new PageAjax<DynamicType>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);*/
        List<DynamicType> list=dynamicTypeService.queryAll();
        AjaxResult ajaxResult=new AjaxResult();
        ajaxResult.setData(list);
        ajaxResult.setRetmsg("success");
        return ajaxResult;
    }



    @Authorization("需token")
    @ApiOperation(value="获取我感兴趣的动态", notes="获取我感兴趣的动态")
    @ResponseBody
    @RequestMapping(value = "myinstrcontent", method = RequestMethod.POST)
    public AjaxResult myinstrcontent (Integer userid,Integer dynamictype_id,String begintime,String endtime,Integer pageNo,Integer pageSize){
        PageAjax<MemBerDynamicwz> page=new PageAjax<MemBerDynamicwz>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        Map map=new HashedMap();
        map.put("dynamictype_id",dynamictype_id);
        map.put("begintime",begintime);
        map.put("endtime",endtime);
        List<Map<String, String>> list=memBerDynamicwzService.myinstrcontent(map,page);
        AjaxResult ajaxResult=new AjaxResult();
        ajaxResult.setData(list);
        ajaxResult.setRetmsg("success");
        return ajaxResult;
    }

    @Authorization("需token")
    @ApiOperation(value="关注行业", notes="关注行业")
    @ResponseBody
    @RequestMapping(value = "choosedyn", method = RequestMethod.POST)
    public AjaxResult choosedyn (Integer userid, Integer dynamictype_id){
        UserCollecTindustry userCollecTindustry=new UserCollecTindustry();
        userCollecTindustry.setUserid(userid);
        userCollecTindustry.setDynamicwzid(dynamictype_id);
        AjaxResult ajaxResult=userCollecTindustryService.save(userCollecTindustry);
        return ajaxResult;
    }

    @Authorization("需token")
    @ApiOperation(value="用户关组的行业", notes="用户关组的行业")
    @ResponseBody
    @RequestMapping(value = "notedyn", method = RequestMethod.POST)
    public AjaxResult notedyn (Integer userid){
        UserCollecTindustry userCollecTindustry=new UserCollecTindustry();
        userCollecTindustry.setUserid(userid);
        AjaxResult ajaxResult=new AjaxResult();
        List<Map<String,String>>  li=userCollecTindustryService.querydyn(userCollecTindustry);
        ajaxResult.setData(li);
        ajaxResult.setRetmsg("success");
        return ajaxResult;
    }

}
