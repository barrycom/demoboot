package com.xe.demo.service;

import com.github.pagehelper.page.PageMethod;
import com.xe.demo.common.annotation.ServiceLog;
import com.xe.demo.common.pojo.PageAjax;
import com.xe.demo.common.utils.AppUtil;
import com.xe.demo.common.utils.DateUtil;
import com.xe.demo.mapper.ActivityMapper;
import com.xe.demo.mapper.MemberMapper;
import com.xe.demo.model.Activity;
import com.xe.demo.model.Member;
import com.xe.demo.model.Regions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017-10-9.
 */
@Service
public class MemberService extends AbstratService<Member> {
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private MemberService memberService;

    @ServiceLog("查询普通用户列表")
    public PageAjax<Member> querySearchPage(PageAjax<Member> page, Member member) {
        PageMethod.startPage(page.getPageNo(), page.getPageSize());
        List<Member> list = memberMapper.queryList(member);
        return AppUtil.returnPage(list);
    }

    public Boolean jobUpdateVipState() {
        Boolean b = true;
        Condition condition=new Condition(Member.class);
        condition.createCriteria().andCondition("ishy =1");
        memberMapper.selectByExample(condition).stream().forEach(i->{
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            try {
                Date d1 = df.parse(i.getViptimeend());
                Date d2=df.parse(DateUtil.getCurDateTime());
                if(d2.getTime() > d1.getTime()){
                    i.setIshy("0");
                    memberService.update(i);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

        });

        return b;
    }
}
