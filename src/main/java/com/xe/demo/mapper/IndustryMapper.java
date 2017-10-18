package com.xe.demo.mapper;


import com.xe.demo.common.dao.MyMapper;
import com.xe.demo.model.Industry;
import com.xe.demo.model.Industry;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IndustryMapper extends MyMapper<Industry> {

    List<Industry> queryList(Industry industry);

    Industry queryOne(@Param("id") Integer id);


    int update(Industry record);

}