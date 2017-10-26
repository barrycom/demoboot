package com.xe.demo.mapper;


import com.xe.demo.common.dao.MyMapper;
import com.xe.demo.model.Activity;
import com.xe.demo.model.AuthUser;
import com.xe.demo.model.Member;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ActivityMapper extends MyMapper<Activity> {
 /*   int deleteByPrimaryKey(String id);*/

    List<Activity> queryList(@Param("activity")Activity activity);

    List<Activity> queryListStringing(@Param("activity")Activity activity);

    List<Activity> queryListTwoMonth(@Param("activity")Activity activity);


    List<Activity> queryListOneMonth(@Param("activity")Activity activity);

    List<Member> getUserByActivtiyId(@Param("id")String id);

    List<Activity> getActivtiyByMemberId(String memberId);




/*
    int insert(Activity record);*/

/*    int insertSelective(Activity record);

*//*    Activity selectByPrimaryKey(String id);*//*

    int updateByPrimaryKeySelective(Activity record);

    int updateByPrimaryKeyWithBLOBs(Activity record);

    int updateByPrimaryKey(Activity record);*/
}