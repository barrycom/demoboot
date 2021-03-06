package com.xe.demo.mapper;


import com.xe.demo.common.dao.MyMapper;
import com.xe.demo.model.DynamicType;
import com.xe.demo.model.DynamicwzImg;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DynamicwzImgMapper extends MyMapper<DynamicwzImg> {

    List<DynamicwzImg> queryList(DynamicwzImg dynamicwzImg);

    DynamicwzImg queryOne(@Param("id") Integer id);


    int update(DynamicwzImg record);

}