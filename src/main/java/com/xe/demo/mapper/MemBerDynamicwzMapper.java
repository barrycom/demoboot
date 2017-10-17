package com.xe.demo.mapper;


import com.xe.demo.common.dao.MyMapper;
import com.xe.demo.model.MemBerDynamicwz;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MemBerDynamicwzMapper extends MyMapper<MemBerDynamicwz> {
 /*   int deleteByPrimaryKey(String id);*/

    List<MemBerDynamicwz> queryList(MemBerDynamicwz  memBerDynamicwz);

    MemBerDynamicwz queryOne(@Param("id") String id);


    int update(MemBerDynamicwz record);

    /*int insert(MemBerDynamicwz record);

    int insertSelective(MemBerDynamicwz record);



    int updateByPrimaryKeyWithBLOBs(MemBerDynamicwz record);

    int updateByPrimaryKey(MemBerDynamicwz record);*/
}