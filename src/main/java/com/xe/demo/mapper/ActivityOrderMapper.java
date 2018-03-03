package com.xe.demo.mapper;

import com.xe.demo.common.dao.MyMapper;
import com.xe.demo.model.Activity;
import com.xe.demo.model.ActivityOrder;
import com.xe.demo.model.AuthUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017-10-18.
 */
public interface ActivityOrderMapper extends MyMapper<ActivityOrder> {
    List<ActivityOrder> queryList(@Param("aorder") ActivityOrder aorder);
    ActivityOrder selectOneById(@Param("id") String id);
    List<Map> selectList(@Param("activity") ActivityOrder activity);
}
