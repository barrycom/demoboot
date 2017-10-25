package com.xe.demo.mapper;


import com.xe.demo.common.dao.MyMapper;
import com.xe.demo.model.ITag;
import com.xe.demo.model.UserCollecTiondy;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserCollecTiondyMapper extends MyMapper<UserCollecTiondy> {

    List<UserCollecTiondy> queryList(UserCollecTiondy userCollecTiondy);

    List<Map>   querycollecmycontent(Integer dynamicwzid);

}