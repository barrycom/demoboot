package com.xe.demo.mapper;


import com.xe.demo.common.dao.MyMapper;
import com.xe.demo.model.DynamicType;
import com.xe.demo.model.DynamicType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DynamicTypeMapper extends MyMapper<DynamicType> {

    List<DynamicType> queryList(DynamicType dynamicType);

    DynamicType queryOne(@Param("id") Integer id);


    int update(DynamicType record);

}