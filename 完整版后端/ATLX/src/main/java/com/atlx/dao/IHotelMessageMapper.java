package com.atlx.dao;

import com.atlx.pojo.FoodMessage;
import com.atlx.pojo.HotelMessage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IHotelMessageMapper extends BaseMapper<HotelMessage> {
    List<HotelMessage> findAllByName(String hotelName);
    List<HotelMessage> findAllByAccount(String account);
}
