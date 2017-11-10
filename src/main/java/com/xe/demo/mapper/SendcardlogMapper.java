package com.xe.demo.mapper;

import com.xe.demo.common.dao.MyMapper;
import com.xe.demo.model.Activity;
import com.xe.demo.model.Member;
import com.xe.demo.model.MemberInfo;
import com.xe.demo.model.Sendcardlog;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2017-11-6.
 */
public interface SendcardlogMapper  extends MyMapper<Sendcardlog> {
    List<HashMap<String,String>> queryList(@Param("member")Member member);

    List<HashMap> queryCorporateList(@Param("member")Member member);
}
