package com.xe.demo.mapper;


import com.xe.demo.common.dao.MyMapper;
import com.xe.demo.model.ITag;
import com.xe.demo.model.UserCollecTindustry;
import com.xe.demo.model.UserCollecTiondy;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserCollecTindustryMapper extends MyMapper<UserCollecTindustry> {

    List<UserCollecTindustry> queryList(UserCollecTindustry userCollecTindustry);

    UserCollecTindustry queryOne(@Param("id") Integer id);

    List<Map<String,String>>  querydyn(UserCollecTindustry userCollecTindustry);

}