package com.xe.demo.mapper;


import com.xe.demo.common.dao.MyMapper;
import com.xe.demo.model.Advert;
import com.xe.demo.model.Daysign;

import java.util.List;
import java.util.Map;

public interface DaysignMapper extends MyMapper<Daysign> {

    List<Daysign> queryadList(Map map);

}