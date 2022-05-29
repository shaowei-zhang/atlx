package com.atlx.service;

import com.atlx.pojo.FoodOrder;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

public interface IFoodOrderService {
    public List<FoodOrder> findAll();//查询所有
    public List<FoodOrder> findByAccount(String account);
    public boolean deleteById(Integer id);//通过id进行删除
    public boolean deleteByMap(Map map);//批量删除
    public boolean updateById(FoodOrder foodOrder);//通过id进行修改
    public boolean insertOne(FoodOrder foodOrder);//新增
}
