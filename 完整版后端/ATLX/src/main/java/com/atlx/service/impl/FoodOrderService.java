package com.atlx.service.impl;

import com.atlx.dao.IFoodOrderMapper;
import com.atlx.pojo.FoodOrder;
import com.atlx.service.IFoodOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class FoodOrderService implements IFoodOrderService {

    @Resource
    IFoodOrderMapper iFoodOrderMapper;

    @Override
    public List<FoodOrder> findAll() {
        return iFoodOrderMapper.findAll();
    }

    @Override
    public List<FoodOrder> findByAccount(String account) {
        return iFoodOrderMapper.findByAccount(account);
    }

    @Override
    public boolean deleteById(Integer id) {
        return iFoodOrderMapper.deleteById(id)>0;
    }

    @Override
    public boolean deleteByMap(Map map) {
        return iFoodOrderMapper.deleteByMap(map)>0;
    }

    @Override
    public boolean updateById(FoodOrder foodOrder) {
        return iFoodOrderMapper.updateById(foodOrder)>0;
    }

    @Override
    public boolean insertOne(FoodOrder foodOrder) {
        return iFoodOrderMapper.insert(foodOrder)>0;
    }
}
