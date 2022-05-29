package com.atlx.service;

import com.atlx.pojo.FoodOrder;
import com.atlx.pojo.HotelOrder;

import java.util.List;
import java.util.Map;

public interface IHotelOrderService {
    public List<HotelOrder> findAll();//查询所有
    public List<HotelOrder> findByAccount(String account);
    public boolean deleteById(Integer id);//通过id进行删除
    public boolean deleteByMap(Map map);//批量删除
    public boolean updateById(HotelOrder hotelOrder);//通过id进行修改
    public boolean insertOne(HotelOrder hotelOrder);//新增
}
