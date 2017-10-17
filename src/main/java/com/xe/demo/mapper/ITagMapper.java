package com.xe.demo.mapper;


import com.xe.demo.common.dao.MyMapper;
import com.xe.demo.model.ITag;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ITagMapper extends MyMapper<ITag> {

    List<ITag> queryList(ITag itag);

    ITag queryOne(@Param("id") Integer id);


    int update(ITag record);

}