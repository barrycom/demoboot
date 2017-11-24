package com.xe.demo.api;

import com.xe.demo.common.pojo.AjaxResult;
import com.xe.demo.common.pojo.PageAjax;
import com.xe.demo.common.support.redis.IRedisService;
import com.xe.demo.common.utils.UploadUtil;
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

import javax.servlet.http.HttpServletRequest;
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
    @Autowired
    private UploadUtil uploadUtil;

    //@Authorization("需token")
    @ApiOperation(value="获取需求广场数据", notes="获取需求广场数据")
    @ResponseBody
    @RequestMapping(value = "otherContent", method = RequestMethod.POST)
    public AjaxResult otherContent (@ApiParam(value = "用户id") @RequestParam String userid,
                                    @ApiParam(value = "行业类型") @RequestParam(required = false) Integer dynamictype_id){
        Map map=new HashedMap();
        if(dynamictype_id!=0) {
            map.put("dynamictype_id", dynamictype_id);
        }
        if(!userid.equals("0")) {
            map.put("userid", userid);
        }
      /*  map.put("begintime",begintime);
        map.put("endtime",endtime);*/
        List<Map<String, String>> list=memBerDynamicwzService.queryneed(map);
        for (Map li:list) {
            if(li.get("dynamicwz").toString().length()<=60){
                li.put("dynamicwzall",li.get("dynamicwz").toString());
                li.put("dynamicwz",li.get("dynamicwz").toString());
            }else{
                li.put("dynamicwzall",li.get("dynamicwz").toString());
                li.put("dynamicwz",li.get("dynamicwz").toString().substring(0,59)+"...");
            }

            UserCollecTiondy userCollecTiondy=new UserCollecTiondy();
            userCollecTiondy.setUserid(userid);
            userCollecTiondy.setDynamicwzid(Integer.parseInt(li.get("id").toString()));
            Integer uctd=userCollecTiondyService.queryCount(userCollecTiondy);
            if(uctd!=0){
                li.put("isinterest","");
            }else{
                li.put("isinterest","interested");
            }
        }

        AjaxResult ajaxResult=new AjaxResult();
        ajaxResult.setData(list);
        ajaxResult.setRetmsg("success");
        return ajaxResult;
    }

    //@Authorization("需token")
    @ApiOperation(value="感兴趣动态", notes="感兴趣动态")
    @ResponseBody
    @RequestMapping(value = "interest", method = RequestMethod.POST)
    public AjaxResult interest (@ApiParam(value = "用户id", required = true) @RequestParam String user_id,
                                @ApiParam(value = "动态id", required = true) @RequestParam Integer dynamic_id){
        UserCollecTiondy userCollecTiondy=new UserCollecTiondy();
        userCollecTiondy.setUserid(user_id);
        userCollecTiondy.setDynamicwzid(dynamic_id);
        AjaxResult ajaxResult=userCollecTiondyService.save(userCollecTiondy);

        return ajaxResult;
    }

    //@Authorization("需token")
    @ApiOperation(value="取消感兴趣动态", notes="感兴趣动态")
    @ResponseBody
    @RequestMapping(value = "notinterest", method = RequestMethod.POST)
    public AjaxResult interest (@ApiParam(value = "关注id", required = true) @RequestParam Integer id){
        AjaxResult ajaxResult=userCollecTiondyService.deleteByID(id);

        return ajaxResult;
    }

    //@Authorization("需token")
    @ApiOperation(value="获取我的动态数据", notes="获取我的动态数据")
    @ResponseBody
    @RequestMapping(value = "myContent", method = RequestMethod.POST)
    public AjaxResult myContent (@ApiParam(value = "用户id", required = true) @RequestParam String userid,
                                 @ApiParam(value = "行业类型") @RequestParam(required = false) Integer dynamictype_id){
        Map querymap=new HashedMap();
        querymap.put("userid",userid);
        if(dynamictype_id!=0) {
            querymap.put("dynamictype_id", dynamictype_id);
        }
        List<Map<String, String>> list=memBerDynamicwzService.querymycontent(querymap);
        for (Map map:list) {
            if(map.get("dynamicwz").toString().length()<=60){
                map.put("dynamicwzall",map.get("dynamicwz").toString());
                map.put("dynamicwz",map.get("dynamicwz").toString());
            }else{
                map.put("dynamicwzall",map.get("dynamicwz").toString());
                map.put("dynamicwz",map.get("dynamicwz").toString().substring(0,59)+"...");
            }
           String id= map.get("id").toString();
            List<Map> li=userCollecTiondyService.querycollecmycontent(Integer.parseInt(id));
            if(li.size()>0){
                map.put("collcer",li);
                map.put("collcersize",li.size());
                map.put("show",false);
            }else{
                map.put("collcer",0);
                map.put("show",true);
            }

        }


        AjaxResult ajaxResult=new AjaxResult();
        ajaxResult.setData(list);
        ajaxResult.setRetmsg("success");
        return ajaxResult;
    }


    //@Authorization("需token")
    @ApiOperation(value="发布动态", notes="发布动态")
    @ResponseBody
    @RequestMapping(value = "saveContent", method = RequestMethod.POST)
    public AjaxResult saveContent (@ApiParam(value = "用户id", required = true) @RequestParam String userid,
                                   @ApiParam(value = "动态文字", required = true) @RequestParam String dynamicwz,
                                   @ApiParam(value = "图片", required = true) @RequestParam String imgurl,
                                   @ApiParam(value = "行业分类", required = true) @RequestParam String dynamicid){
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


    //@Authorization("需token")
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



    //@Authorization("需token")
    @ApiOperation(value="获取我感兴趣的动态", notes="获取我感兴趣的动态")
    @ResponseBody
    @RequestMapping(value = "myinstrcontent", method = RequestMethod.POST)
    public AjaxResult myinstrcontent (@ApiParam(value = "用户id", required = true) @RequestParam String userid,
                                    /*  @ApiParam(value = "行业id", required = true) @RequestParam Integer dynamictype_id,*/
                                      @ApiParam(value = "开始时间", required = true) @RequestParam String begintime,
                                      @ApiParam(value = "结束时间", required = true) @RequestParam String endtime/*,
                                      @ApiParam(value = "当前页面", required = true) @RequestParam Integer pageNo,
                                      @ApiParam(value = "页面大小", required = true) @RequestParam Integer pageSize*/){
       /* PageAjax<MemBerDynamicwz> page=new PageAjax<MemBerDynamicwz>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);*/
        Map map=new HashedMap();
      /*  if(dynamictype_id!=0) {
            map.put("dynamictype_id", dynamictype_id);
        }*/
        map.put("userid",userid);
        map.put("begintime",begintime);
        map.put("endtime",endtime);
        List<Map<String, String>> list=memBerDynamicwzService.myinstrcontent(map);
        for (Map map1:list) {
            if (map1.get("dynamicwz").toString().length() <= 60) {
                map1.put("dynamicwzall", map1.get("dynamicwz").toString());
                map1.put("dynamicwz", map1.get("dynamicwz").toString());
            } else {
                map1.put("dynamicwzall", map1.get("dynamicwz").toString());
                map1.put("dynamicwz", map1.get("dynamicwz").toString().substring(0, 59) + "...");
            }
        }
        AjaxResult ajaxResult=new AjaxResult();
        ajaxResult.setData(list);
        ajaxResult.setRetmsg("success");
        return ajaxResult;
    }

    //@Authorization("需token")
    @ApiOperation(value="关注行业", notes="关注行业")
    @ResponseBody
    @RequestMapping(value = "choosedyn", method = RequestMethod.POST)
    public AjaxResult choosedyn (@ApiParam(value = "用户id", required = true) @RequestParam String userid,
                                 @ApiParam(value = "行业id", required = true) @RequestParam Integer dynamictype_id){
        UserCollecTindustry userCollecTindustry=new UserCollecTindustry();
        userCollecTindustry.setUserid(userid);
        userCollecTindustry.setDynamicwzid(dynamictype_id);
        AjaxResult ajaxResult=userCollecTindustryService.save(userCollecTindustry);
        return ajaxResult;
    }

   // @Authorization("需token")
    @ApiOperation(value="用户关组的行业", notes="用户关组的行业")
    @ResponseBody
    @RequestMapping(value = "notedyn", method = RequestMethod.POST)
    public AjaxResult notedyn (@ApiParam(value = "用户id", required = true) @RequestParam String userid){
        UserCollecTindustry userCollecTindustry=new UserCollecTindustry();
        userCollecTindustry.setUserid(userid);
        AjaxResult ajaxResult=new AjaxResult();
            List<Map<String,String>>  li=userCollecTindustryService.querydyn(userCollecTindustry);
        ajaxResult.setData(li);
        ajaxResult.setRetmsg("success");
        return ajaxResult;
    }

    // @Authorization("需token")
    @ApiOperation(value="图片上传", notes="图片上传")
    @ResponseBody
    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public String upload (HttpServletRequest request, String docBase, String path){
        String imgpath="";
        try {
            imgpath=uploadUtil.upload(request,null,path);
        }catch (Exception ex) {
            ex.getStackTrace();
        }
     /*   AjaxResult ajaxResult=new AjaxResult();
        ajaxResult.setData(imgpath);
        ajaxResult.setRetmsg("success");*/
        return imgpath;
    }

    // @Authorization("需token")
    @ApiOperation(value="通过动态id查询", notes="通过动态id查询")
    @ResponseBody
    @RequestMapping(value = "singlenew", method = RequestMethod.POST)
    public AjaxResult singlenew (@ApiParam(value = "动态id", required = true) @RequestParam String id,@ApiParam(value = "用户userid", required = true) @RequestParam String userid){
        AjaxResult ajaxResult=new AjaxResult();
        MemBerDynamicwz memBerDynamicwz=memBerDynamicwzService.queryOne(id);
        Integer uctd=0;
        if(memBerDynamicwz.getDynamicwz().length()<=15){
            memBerDynamicwz.setDynamicwzall(memBerDynamicwz.getDynamicwz());
        }else{
            memBerDynamicwz.setDynamicwzall(memBerDynamicwz.getDynamicwz());
            memBerDynamicwz.setDynamicwz(memBerDynamicwz.getDynamicwz().substring(0,14));
        }

        if(!userid.equals("") && userid!=null) {
            UserCollecTiondy userCollecTiondy = new UserCollecTiondy();
            userCollecTiondy.setUserid(userid);
            userCollecTiondy.setDynamicwzid(Integer.parseInt(id));
            uctd = userCollecTiondyService.queryCount(userCollecTiondy);
        }
        if(uctd!=0){
            memBerDynamicwz.setIsinterest("");
        }else{
            memBerDynamicwz.setIsinterest("interested");
        }

        ajaxResult.setData(memBerDynamicwz);
        ajaxResult.setRetmsg("success");
        return ajaxResult;
    }

    //@Authorization("需token")
    @ApiOperation(value="获取我的动态数据", notes="获取我的动态数据")
    @ResponseBody
    @RequestMapping(value = "myContentwithmember", method = RequestMethod.POST)
    public AjaxResult myContentwithmember (@ApiParam(value = "用户id", required = true) @RequestParam String userid,
                                 @ApiParam(value = "行业类型") @RequestParam(required = false) Integer dynamictype_id,
                                 @ApiParam(value = "当前用户id") @RequestParam(required = false) String member_id){
        Map querymap=new HashedMap();
        querymap.put("userid",userid);
        if(dynamictype_id!=0) {
            querymap.put("dynamictype_id", dynamictype_id);
        }
        List<Map<String, String>> list=memBerDynamicwzService.querymycontent(querymap);
        for (Map map:list) {
            if(map.get("dynamicwz").toString().length()<=60){
                map.put("dynamicwzall",map.get("dynamicwz").toString());
                map.put("dynamicwz",map.get("dynamicwz").toString());
            }else{
                map.put("dynamicwzall",map.get("dynamicwz").toString());
                map.put("dynamicwz",map.get("dynamicwz").toString().substring(0,59)+"...");
            }
            String id= map.get("id").toString();
            List<Map> li=userCollecTiondyService.querycollecmycontent(Integer.parseInt(id));
            if(li.size()>0){
                map.put("collcer",li);
                map.put("collcersize",li.size());
                map.put("show",false);
            }else{
                map.put("collcer",0);
                map.put("show",true);
            }
            if(!member_id.equals("")){
                UserCollecTiondy userCollecTiondy = new UserCollecTiondy();
                userCollecTiondy.setUserid(member_id);
                userCollecTiondy.setDynamicwzid(Integer.parseInt(id));
                Integer uctd = userCollecTiondyService.queryCount(userCollecTiondy);
                if (uctd != 0) {
                    map.put("isinterest", "");
                } else {
                    map.put("isinterest", "interested");
                }
            }
        }


        AjaxResult ajaxResult=new AjaxResult();
        ajaxResult.setData(list);
        ajaxResult.setRetmsg("success");
        return ajaxResult;
    }

}
