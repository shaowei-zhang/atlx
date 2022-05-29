package com.atlx.service;

import com.atlx.dao.IFoodLocalMapper;
import com.atlx.pojo.FoodLocal;

import java.util.List;

/**
 * @author zhangshaowei
 * @date 2022/5/9 10:59
 * @description
 */
public interface IFoodLocalService {
    List<FoodLocal> queryAllMessage(int pageSize,int pageCode);
    List<FoodLocal> queryByName(String foodName);
    FoodLocal queryFoodLocalByName(String foodName);
    List<FoodLocal>  queryAllFoodInfo();
}
