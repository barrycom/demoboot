package com.xe.demo.service;

import com.github.pagehelper.page.PageMethod;
import com.xe.demo.common.annotation.ServiceLog;
import com.xe.demo.common.pojo.AjaxResult;
import com.xe.demo.common.pojo.PageAjax;
import com.xe.demo.common.utils.AppUtil;
import com.xe.demo.mapper.ITagMapper;
import com.xe.demo.mapper.UserCollecTiondyMapper;
import com.xe.demo.model.ITag;
import com.xe.demo.model.UserCollecTiondy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017-10-9.
 */
@Service
public class UserCollecTiondyService extends AbstratService<UserCollecTiondy> {

    @Autowired
    private UserCollecTiondyMapper userCollecTiondyMapper;



   public List<Map>   querycollecmycontent(Integer dynamicwzid){
       return  userCollecTiondyMapper.querycollecmycontent(dynamicwzid);
    }

}
