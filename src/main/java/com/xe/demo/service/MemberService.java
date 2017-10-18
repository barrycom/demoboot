package com.xe.demo.service;

import com.github.pagehelper.page.PageMethod;
import com.xe.demo.common.annotation.ServiceLog;
import com.xe.demo.common.pojo.PageAjax;
import com.xe.demo.common.utils.AppUtil;
import com.xe.demo.mapper.ActivityMapper;
import com.xe.demo.mapper.MemberMapper;
import com.xe.demo.model.Activity;
import com.xe.demo.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017-10-9.
 */
@Service
public class MemberService extends AbstratService<Member> {
    @Autowired
    private MemberMapper memberMapper;

    @ServiceLog("查询普通用户列表")
    public PageAjax<Member> querySearchPage(PageAjax<Member> page, Member member) {
        PageMethod.startPage(page.getPageNo(), page.getPageSize());
        List<Member> list = memberMapper.queryList(member);
        return AppUtil.returnPage(list);
    }
}
