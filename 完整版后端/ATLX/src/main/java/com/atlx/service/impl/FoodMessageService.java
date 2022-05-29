package com.atlx.service.impl;

import com.atlx.dao.IFoodMessageMapper;
import com.atlx.pojo.FoodMessage;
import com.atlx.service.IFoodMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FoodMessageService implements IFoodMessageService {
    @Autowired
    private IFoodMessageMapper iFoodMessageMapper;

    @Override
    public List<FoodMessage> findAllByName(String foodName) {
        return iFoodMessageMapper.findAllByName(foodName);
    }

    @Override
    public List<FoodMessage> findAllByAccount(String account) {
        return iFoodMessageMapper.findAllByAccount(account);
    }
}
