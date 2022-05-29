package com.atlx.dao;

import com.atlx.pojo.TouristRoutes;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.io.Serializable;
import java.util.List;
@Mapper
public interface ITouristRoutesMapper extends BaseMapper<TouristRoutes> {

    @Select("SELECT * FROM `tourist_routes`WHERE end_name=#{account} OR start_name=#{account}")
    List<TouristRoutes> selectOne(String account);
}
