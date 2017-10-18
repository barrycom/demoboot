package com.xe.demo.service;

import com.github.pagehelper.page.PageMethod;
import com.xe.demo.common.annotation.ServiceLog;
import com.xe.demo.common.pojo.AjaxResult;
import com.xe.demo.common.pojo.PageAjax;
import com.xe.demo.common.utils.AppUtil;
import com.xe.demo.mapper.IndustryMapper;
import com.xe.demo.model.Industry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017-10-9.
 */
@Service
public class IndustryService extends AbstratService<Industry> {
    @Autowired
    private IndustryMapper industryMapper;

    @ServiceLog("查询活动列表")
    public PageAjax<Industry> queryPage(PageAjax<Industry> page, Industry industry) {
        PageMethod.startPage(page.getPageNo(), page.getPageSize());
        List<Industry> list = industryMapper.queryList(industry);
        return AppUtil.returnPage(list);
    }

    @ServiceLog("根据id查询活动信息")
    public Industry queryOne(Integer id) {
        Industry one = industryMapper.queryOne(id);
        return one;
    }

    @ServiceLog("根据id查询修改状态")
    public AjaxResult update(Industry industry) {
        int ret = industryMapper.update(industry);
        String result = null;
        if(ret <= 0){
            result = "更新失败";
        }
        return AppUtil.returnObj(result);
    }

}
