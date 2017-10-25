package com.xe.demo.service;

import com.github.pagehelper.page.PageMethod;
import com.xe.demo.common.annotation.ServiceLog;
import com.xe.demo.common.pojo.AjaxResult;
import com.xe.demo.common.pojo.PageAjax;
import com.xe.demo.common.utils.AppUtil;
import com.xe.demo.mapper.DynamicTypeMapper;
import com.xe.demo.model.DynamicType;
import com.xe.demo.model.DynamicType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017-10-9.
 */
@Service
public class DynamicTypeService extends AbstratService<DynamicType> {
    @Autowired
    private DynamicTypeMapper DynamicTypeMapper;

    @ServiceLog("查询活动列表")
    public PageAjax<DynamicType> queryPage(PageAjax<DynamicType> page, DynamicType dynamicType) {
        PageMethod.startPage(page.getPageNo(), page.getPageSize());
        List<DynamicType> list = DynamicTypeMapper.queryList(dynamicType);
        return AppUtil.returnPage(list);
    }

    @ServiceLog("根据id查询活动信息")
    public DynamicType queryOne(Integer id) {
        DynamicType one = DynamicTypeMapper.queryOne(id);
        return one;
    }

    @ServiceLog("根据id查询修改状态")
    public AjaxResult update(DynamicType dynamicType) {
        int ret = DynamicTypeMapper.update(dynamicType);
        String result = null;
        if(ret <= 0){
            result = "更新失败";
        }
        return AppUtil.returnObj(result);
    }

    public List<DynamicType> getMemberDynamicType(String memberId) {
        return  DynamicTypeMapper.getMemberDynamicType(memberId);
    }
}
