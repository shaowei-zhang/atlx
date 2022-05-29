package com.atlx.dao;

import com.atlx.pojo.FoodLocal;
import com.atlx.pojo.FoodMessage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * @author zhangshaowei
 * @date 2022/5/9 10:57
 * @description
 */
@Mapper
@Repository //代表持久层
public interface IFoodLocalMapper extends BaseMapper<FoodLocal> {

    //查询美食列表
    List<FoodLocal> queryAllMessage(int pageSize,int pageCode);
    List<FoodLocal> queryByName(String foodName);
    List<FoodLocal>  queryAllFoodInfo();

    FoodLocal queryFoodLocalByName(String foodName);

}
