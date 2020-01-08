package com.storm.mapper;

import com.storm.model.StormTest;

public interface StormTestMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StormTest record);

    int insertSelective(StormTest record);

    StormTest selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StormTest record);

    int updateByPrimaryKey(StormTest record);
}