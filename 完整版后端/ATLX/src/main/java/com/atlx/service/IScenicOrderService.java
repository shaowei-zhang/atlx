package com.atlx.service;

import com.atlx.pojo.FoodOrder;
import com.atlx.pojo.ScenicOrder;

import java.util.List;
import java.util.Map;

public interface IScenicOrderService {
    public List<ScenicOrder> findAll();//查询所有
    public List<ScenicOrder> findByAccount(String account);
    public boolean deleteById(Integer id);//通过id进行删除
    public boolean deleteByMap(Map map);//批量删除
    public boolean updateById(ScenicOrder scenicOrder);//通过id进行修改
    public boolean insertOne(ScenicOrder scenicOrder);//新增
}
