package com.atlx.service;

import com.atlx.pojo.FoodMessage;

import java.util.List;

public interface IFoodMessageService {
    List<FoodMessage> findAllByName(String foodName);
    List<FoodMessage> findAllByAccount(String account);

}
