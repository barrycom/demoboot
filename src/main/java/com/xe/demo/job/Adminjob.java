package com.xe.demo.job;

import com.xe.demo.common.datasource.DynamicDataSourceRegister;
import com.xe.demo.controller.BaseController;
import com.xe.demo.mapper.MemberMapper;
import com.xe.demo.service.MemberInfoService;
import com.xe.demo.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * Created by admin on 2017/11/13.
 */
@Configuration
@EnableScheduling
public class Adminjob {
    @Autowired
    private MemberService memberService;

    private static final Logger logger = LoggerFactory.getLogger(DynamicDataSourceRegister.class);


    //定时修改用户会员状态
    @Scheduled(cron="0 0 0 * * ?")
    public void executeFileDownLoadTask() {
        memberService.jobUpdateVipState();
        logger.info("ScheduledTest.executeFileDownLoadTask 定时任务 定时修改用户会员状态:");
    }
}
