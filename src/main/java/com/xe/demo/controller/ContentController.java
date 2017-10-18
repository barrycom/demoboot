package com.xe.demo.controller;

import com.xe.demo.common.annotation.Authority;
import com.xe.demo.common.annotation.ControllerLog;
import com.xe.demo.common.pojo.AjaxResult;
import com.xe.demo.common.pojo.PageAjax;
import com.xe.demo.model.DynamicType;
import com.xe.demo.model.ITag;
import com.xe.demo.model.Industry;
import com.xe.demo.model.MemBerDynamicwz;
import com.xe.demo.service.DynamicTypeService;
import com.xe.demo.service.IndustryService;
import com.xe.demo.service.MemBerDynamicwzService;
import com.xe.demo.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017-10-9.
 */

@Controller
@RequestMapping("/admin/content/")
public class ContentController extends BaseController {
    @Autowired
    private MemBerDynamicwzService memBerDynamicwzService;

    @Autowired
    private TagService tagService;

    @Autowired
    private IndustryService industryService;

    @Autowired
    private DynamicTypeService dynamicTypeService;

    @Authority(opCode = "06", opName = "动态列表页面")
    @RequestMapping("dynamicsList")
    public String mainPage(Map<String, Object> map) {
        MemBerDynamicwz memberdynamicwz =new MemBerDynamicwz();
        return "MemBerDynamicwz/main";
    }


    @ControllerLog("查询用户列表")
    @RequestMapping("queryPage")
    @ResponseBody
    @Authority(opCode = "06", opName = "查询动态列表")
    public PageAjax<MemBerDynamicwz> queryPage(PageAjax<MemBerDynamicwz> page, MemBerDynamicwz memBerDynamicwz) {
        return memBerDynamicwzService.queryPage(page, memBerDynamicwz);
    }

    @ControllerLog("手机详情")
    @RequestMapping("showphone")
    @ResponseBody
    @Authority(opCode = "06", opName = "动态手机页面")
    public AjaxResult showphone(String id) {
        AjaxResult ajaxResult=new AjaxResult();
        String html="";
        MemBerDynamicwz mebdy=memBerDynamicwzService.queryOne(id);

        if(mebdy!=null){
                //手机头部样式
               String heand="<div id=\"u0\" class=\"ax_default\">" +
                       "                                    <div id=\"u0_state0\" class=\"panel_state\" data-label=\"State1\">" +
                       "                                        <div id=\"u0_state0_content\" class=\"panel_state_content\">" +
                       "                                            <div id=\"u1\" class=\"ax_default flow_shape\">" +
                       "                                                <div id=\"u1_div\" class=\"\"></div>" +
                       "                                                <div id=\"u2\" class=\"text\" style=\"display: none; visibility: hidden\">" +
                       "                                                    <p><span></span></p>" +
                       "                                                </div>" +
                       "                                            </div>" +
                       "                                        </div>" +
                       "                                    </div>" +
                       "                                </div>" +
                       "                                <div id=\"u3\" class=\"ax_default _图片\">" +
                       "                                    <img id=\"u3_img\" class=\"img \" src=\"/assets/images/index/u3.png\"/>" +
                       "                                    <div id=\"u4\" class=\"text\" style=\"display: none; visibility: hidden\">" +
                       "                                        <p><span></span></p>" +
                       "                                    </div>" +
                       "                                </div>" +
                       "                                <div id=\"u5\" class=\"ax_default ellipse\">" +
                       "                                    <img id=\"u5_img\" class=\"img \" src=\""+mebdy.getHeadimg()+"\"/>" +
                       "                                    <div id=\"u6\" class=\"text\" style=\"display: none; visibility: hidden;color: #4ab6d5;\">" +
                       "                                        <p><span></span></p>" +
                       "                                    </div>" +
                       "                                </div>";
                //用户信息部分
               String user="<div id=\"u7\" class=\"ax_default label\" style=\"background: #EFEFEF;\">" +
                       "                                    <div id=\"u7_div\" class=\"\"></div>" +
                       "                                    <div id=\"u8\" class=\"text\" style=\"visibility: visible;\">" +
                       "                                        <p><span>"+mebdy.getUname()+"</span></p>" +
                       "                                    </div>" +
                       "                                </div>" +
                       "                                <div id=\"u9\" class=\"ax_default button\">" +
                       "                                    <div id=\"u9_div\" class=\"\"></div>" +
                       "                                    <div id=\"u10\" class=\"text\" style=\"visibility: visible;\">" +
                       "                                        <p><span style=\"color: #4ab6d5;\">聊聊TA</span></p>" +
                       "                                    </div>" +
                       "                                </div>" +
                       "                                <div id=\"u11\" class=\"ax_default label\" style=\"background: #EFEFEF;\">" +
                       "                                    <div id=\"u11_div\" class=\"\"></div>" +
                       "                                    <div id=\"u12\" class=\"text\" style=\"visibility: visible;\">" +
                       "                                        <p><span>市场部门 | 拓展部</span></p>" +
                       "                                    </div>" +
                       "                                </div>";
                //动态文字
                String content="<div id=\"u13\" class=\"ax_default _文本段落\">" +
                        "                                    <div id=\"u13_div\" class=\"\"></div>" +
                        "                                    <div id=\"u14\" class=\"text\" style=\"visibility: visible;\">" +
                        "                                        <p><span>"+mebdy.getDynamicwz()+"</span></p></div></div>";

            //展示图片
            String img="";
            Integer ucount=15;
               String [] imglist=mebdy.getImgurl().split(",");
                     for(int i=0;i<imglist.length;i++) {
                         if (imglist[i] != null && !imglist[i].equals("")) {
                               if (ucount > 15) {
                                   ucount++;
                               }
                               img += "<div id=\"u" + ucount + "\" class=\"ax_default image\">" +
                                       "<img id=\"u" + ucount + "_img\" class=\"img \" src=\"" + imglist[i] + "\"/>" +
                                       "<div id=\"u" + (ucount + 1) + "\" class=\"text\" style=\"display: none; visibility: hidden\">" +
                                       "<p><span></span></p>" +
                                       "</div></div>";
                                ucount++;
                           }

                       }
            html=heand+user+content+img;
        }
        ajaxResult.setData(html);
        return ajaxResult;
    }

    @Authority(opCode = "06", opName = "修改")
    @ResponseBody
    @RequestMapping("screening")
    public AjaxResult screening(String id) {
        MemBerDynamicwz meb=new MemBerDynamicwz();
        meb.setId(id);
        meb.setState("1");
        return memBerDynamicwzService.update(meb);
    }


    /*
    * 标签管理
    *
    */
    @Authority(opCode = "06", opName = "标签页面")
    @RequestMapping("tagpage")
    public String qurytagpage(Map<String, Object> map) {

        return "MemBerDynamicwz/tagpage";
    }

    @ControllerLog("查询标签列表")
    @RequestMapping("tagList")
    @ResponseBody
    @Authority(opCode = "06", opName = "查询标签列表")
    public PageAjax<ITag> tagList(PageAjax<ITag> page, ITag iTag) {
        return tagService.queryPage(page, iTag);
    }


    @Authority(opCode = "06", opName = "修改标签页面")
    @RequestMapping("tagupdatepage")
    public String tagupdatepage(Map<String, Object> map,Integer id) {
        if(id!=null && id!=0){
            ITag itag=tagService.queryOne(id);
            map.put("itag",itag);
        }
        return "MemBerDynamicwz/tagupdatepage";
    }

    @Authority(opCode = "06", opName = "添加标签页面")
    @RequestMapping("taginstrpage")
    public String taginstrpage(Map<String, Object> map) {

        return "MemBerDynamicwz/taginstrpage";
    }

    @Authority(opCode = "06", opName = "修改")
    @ResponseBody
    @RequestMapping("tagupdate")
    public AjaxResult tagupdate(ITag iTag) {
        return tagService.update(iTag);
    }

    @Authority(opCode = "06", opName = "删除标签")
    @ResponseBody
    @RequestMapping("tagdelect/{id}")
    public AjaxResult tagdelect(@PathVariable("id") int id) {
        AjaxResult aa=tagService.deleteByID(id);
        return aa;
    }

    @Authority(opCode = "06", opName = "添加标签")
    @ResponseBody
    @RequestMapping("instrtag")
    public AjaxResult instrtag(ITag iTag) {
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式
        String daynow = dateFormat.format(now);
        iTag.setCreat_time(daynow);
        iTag.setState("0");
        return tagService.save(iTag);
    }

    /*
    * 行业分类管理
    *
    */


    @Authority(opCode = "06", opName = "行业分类页面")
    @RequestMapping("industrypage")
    public String industrypage(Map<String, Object> map) {
        return "industry/main";
    }

    @ControllerLog("行业分类列表")
    @RequestMapping("industryList")
    @ResponseBody
    @Authority(opCode = "06", opName = "行业分类列表")
    public PageAjax<Industry> industryList(PageAjax<Industry> page, Industry industry) {
        return industryService.queryPage(page, industry);
    }

    @Authority(opCode = "06", opName = "修改行业分类")
    @ResponseBody
    @RequestMapping("industryupdate")
    public AjaxResult industryupdate(Industry industry) {
        return industryService.update(industry);
    }

    @Authority(opCode = "06", opName = "删除行业分类")
    @ResponseBody
    @RequestMapping("industrydelect/{id}")
    public AjaxResult industrydelect(@PathVariable("id") int id) {
        AjaxResult aa=industryService.deleteByID(id);
        return aa;
    }

    @Authority(opCode = "06", opName = "修改行业分类页面")
    @RequestMapping("industryupdatepage")
    public String industryupdatepage(Map<String, Object> map,Integer id) {
        if(id!=null && id!=0){
            Industry industry=industryService.queryOne(id);
            map.put("industry",industry);
        }
        return "industry/industryupdatepage";
    }

    @Authority(opCode = "06", opName = "添加行业分类页面")
    @RequestMapping("industryaddpage")
    public String industryaddpage(Map<String, Object> map) {
        return "industry/industryadd";
    }

    @Authority(opCode = "06", opName = "添加行业分类")
    @ResponseBody
    @RequestMapping("industryadd")
    public AjaxResult industryadd(Industry industry) {
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式
        String daynow = dateFormat.format(now);
        String [] name=industry.getIndustryname().split(",");
        //AjaxResult result=industryService.save("22",name);
        AjaxResult result=new AjaxResult();
        for (int i=0;i<name.length;i++){
            Industry ind=new Industry();
            if (name[i]!=null && !name[i].equals("")){
                ind.setCreat_time(daynow);
                ind.setIndustryname(name[i]);
                result=industryService.save(ind);
                if(result.getRetcode()!=1){
                    break;
                }
            }

        }
        return result;
    }


    /*
    * 动态分类管理
    *
    */


    @Authority(opCode = "06", opName = "动态分类页面")
    @RequestMapping("dynamicpage")
    public String dynamicpage(Map<String, Object> map) {
        return "dynamictype/main";
    }

    @ControllerLog("动态分类列表")
    @RequestMapping("dynamicList")
    @ResponseBody
    @Authority(opCode = "06", opName = "动态分类列表")
    public PageAjax<DynamicType> dynamicList(PageAjax<DynamicType> page, DynamicType dynamicType) {
        return dynamicTypeService.queryPage(page,dynamicType);
    }

    @Authority(opCode = "06", opName = "修改动态分类")
    @ResponseBody
    @RequestMapping("dynamicupdate")
    public AjaxResult dynamicupdate(DynamicType dynamicType) {
        return dynamicTypeService.update(dynamicType);
    }

    @Authority(opCode = "06", opName = "删除动态分类")
    @ResponseBody
    @RequestMapping("dynamicdelect/{id}")
    public AjaxResult dynamicdelect(@PathVariable("id") int id) {
        AjaxResult aa=dynamicTypeService.deleteByID(id);
        return aa;
    }

    @Authority(opCode = "06", opName = "修改动态分类页面")
    @RequestMapping("dynamicupdatepage")
    public String dynamicupdatepage(Map<String, Object> map,Integer id) {
        if(id!=null && id!=0){
            DynamicType dynamicType=dynamicTypeService.queryOne(id);
            map.put("dynamicType",dynamicType);
        }
        return "dynamictype/dynamicupdatepage";
    }

    @Authority(opCode = "06", opName = "添加动态分类页面")
    @RequestMapping("dynamicaddpage")
    public String dynamicaddpage(Map<String, Object> map) {
        return "dynamictype/dynamicadd";
    }

    @Authority(opCode = "06", opName = "添加动态分类")
    @ResponseBody
    @RequestMapping("dynamicadd")
    public AjaxResult dynamicadd(DynamicType dynamicType) {
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式
        String daynow = dateFormat.format(now);
        String [] name=dynamicType.getDynamicname().split(",");
        //AjaxResult result=industryService.save("22",name);
        AjaxResult result=new AjaxResult();
        for (int i=0;i<name.length;i++){
            DynamicType dyn=new DynamicType();
            if (name[i]!=null && !name[i].equals("")){
                dyn.setCreat_time(daynow);
                dyn.setDynamicname(name[i]);
                result=dynamicTypeService.save(dyn);
                if(result.getRetcode()!=1){
                    break;
                }
            }

        }
        return result;
    }
}

