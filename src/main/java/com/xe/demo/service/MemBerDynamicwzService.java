package com.xe.demo.service;

import com.github.pagehelper.page.PageMethod;
import com.xe.demo.common.annotation.ServiceLog;
import com.xe.demo.common.pojo.AjaxResult;
import com.xe.demo.common.pojo.PageAjax;
import com.xe.demo.common.utils.AppUtil;
import com.xe.demo.mapper.MemBerDynamicwzMapper;
import com.xe.demo.mapper.UserCollecTiondyMapper;
import com.xe.demo.model.MemBerDynamicwz;
import com.xe.demo.model.UserCollecTiondy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017-10-9.
 */
@Service
public class MemBerDynamicwzService extends AbstratService<MemBerDynamicwz> {
    @Autowired
    private MemBerDynamicwzMapper memBerDynamicwzMapper;



    @ServiceLog("查询活动列表")
    public PageAjax<MemBerDynamicwz> queryPage(PageAjax<MemBerDynamicwz> page, MemBerDynamicwz MemBerDynamicwz) {
        PageMethod.startPage(page.getPageNo(), page.getPageSize());
        List<MemBerDynamicwz> list = memBerDynamicwzMapper.queryList(MemBerDynamicwz);
        return AppUtil.returnPage(list);
    }

    @ServiceLog("根据id查询活动信息")
    public MemBerDynamicwz queryOne(String id) {
        MemBerDynamicwz one = memBerDynamicwzMapper.queryOne(id);
        return one;
    }

    @ServiceLog("根据id查询修改状态")
    public AjaxResult update(MemBerDynamicwz meb) {
        int ret = memBerDynamicwzMapper.update(meb);
        String result = null;
        if(ret <= 0){
            result = "更新失败";
        }
        return AppUtil.returnObj(result);
    }

    @ServiceLog("需求广场")
    public List<Map<String, String>> queryneed(Map map,PageAjax<MemBerDynamicwz> page) {
        PageMethod.startPage(page.getPageNo(), page.getPageSize());
        List<Map<String, String>> list = memBerDynamicwzMapper.queryneed(map);
        return list;
    }

    @ServiceLog("需求广场")
    public List<Map<String, String>> querymycontent(PageAjax<MemBerDynamicwz> page,Integer userid){
        PageMethod.startPage(page.getPageNo(), page.getPageSize());
        List<Map<String, String>> list = memBerDynamicwzMapper.querymycontent(userid);
        return list;
    }

    @ServiceLog("新增动态")
    public AjaxResult savecontent(MemBerDynamicwz meb) {
        int ret = memBerDynamicwzMapper.savecontent(meb);
        String result = null;
        if(ret <= 0){
            result = "更新失败";
        }
        return AppUtil.returnObj(result);
    }

    @ServiceLog("我感兴趣的动态")
    public List<Map<String, String>> myinstrcontent(Map map,PageAjax<MemBerDynamicwz> page) {
        PageMethod.startPage(page.getPageNo(), page.getPageSize());
        List<Map<String, String>> list = memBerDynamicwzMapper.myinstrcontent(map);
        return list;
    }
}
