package com.xe.demo.mapper;


import com.xe.demo.common.dao.MyMapper;
import com.xe.demo.model.Activity;
import com.xe.demo.model.Advert;
import com.xe.demo.model.Member;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface AdvertMapper extends MyMapper<Advert> {

    List<Advert> queryadList(Map map);

}