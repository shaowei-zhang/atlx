package com.atlx.dao;

import com.atlx.pojo.FoodLocal;
import com.atlx.pojo.FoodMessage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface IFoodMessageMapper extends BaseMapper<FoodMessage> {
    List<FoodMessage> findAllByName(String foodName);
    List<FoodMessage> findAllByAccount(String account);
}
