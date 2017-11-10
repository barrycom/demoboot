package com.xe.demo.service;

import com.github.pagehelper.page.PageMethod;
import com.xe.demo.common.annotation.ServiceLog;
import com.xe.demo.common.pojo.PageAjax;
import com.xe.demo.common.utils.AppUtil;
import com.xe.demo.mapper.*;
import com.xe.demo.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2017-10-9.
 */
@Service
public class ChatService extends AbstratService<Sendcardlog> {
    @Autowired
    private SendcardlogMapper sendcardlogMapper;

    @ServiceLog("查询互换名片")
    public PageAjax<HashMap<String,String>> queryList(PageAjax<HashMap<String,String>> page, Member member) {
        PageMethod.startPage(page.getPageNo(), page.getPageSize());
        List<HashMap<String,String>> list = sendcardlogMapper.queryList(member);
        return AppUtil.returnPage(list);
    }

    @ServiceLog("查询公司数据")
    public PageAjax<HashMap> queryCorporateList(PageAjax<HashMap> page, Member member) {
        PageMethod.startPage(page.getPageNo(), page.getPageSize());
        List<HashMap> list = sendcardlogMapper.queryCorporateList(member);
        return AppUtil.returnPage(list);
    }
}
