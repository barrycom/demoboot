package com.xe.demo.job;

import com.xe.demo.mapper.MemberMapper;
import com.xe.demo.model.Member;
import com.xe.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@EnableScheduling
public class SchedulingConfig {
    //private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private MemberService memberService;

    @Scheduled(cron = "0 0 0 * * ?")
    public void checkvip() throws ParseException {
        Date day=new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
       List<Member> list=memberMapper.selectAll();
        for (Member li:list) {
            if(li.getIshy().equals("1")) {
               String end= li.getViptimeend();
               System.out.print(end);
                if (end!= null) {
                    Date endtime = sdf.parse(li.getViptimeend());
                    Date nowtime = sdf.parse(sdf.format(day));
                    if (day.compareTo(endtime) > 0) {
                        Member member = new Member();
                        member.setId(li.getId());
                        member.setIshy("0");
                        member.setViptimeend("");
                        member.setViptimestart("");
                        memberService.updateByID(member);
                    }
                }
            }
        }
    }

}