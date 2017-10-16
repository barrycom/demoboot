package com.xe.demo.mapper;


import com.xe.demo.common.dao.MyMapper;
import com.xe.demo.model.Member;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MemberMapper extends MyMapper<Member> {
 /*   int deleteByPrimaryKey(String id);*/

    List<Member> queryList(@Param("Member") Member member);
}