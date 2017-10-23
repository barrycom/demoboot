package com.xe.demo.mapper;


import com.xe.demo.common.dao.MyMapper;
import com.xe.demo.model.MemBerDynamicwz;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface MemBerDynamicwzMapper extends MyMapper<MemBerDynamicwz> {
 /*   int deleteByPrimaryKey(String id);*/

    List<MemBerDynamicwz> queryList(MemBerDynamicwz  memBerDynamicwz);

    MemBerDynamicwz queryOne(@Param("id") String id);


    int update(MemBerDynamicwz record);

    List<Map<String, String>>  queryneed(Map map);

    List<Map<String, String>>  myinstrcontent(Map map);

    List<Map<String, String>>  querymycontent(Integer userid);


    int savecontent(MemBerDynamicwz record);

    /*int insertSelective(MemBerDynamicwz record);



    int updateByPrimaryKeyWithBLOBs(MemBerDynamicwz record);

    int updateByPrimaryKey(MemBerDynamicwz record);*/
}