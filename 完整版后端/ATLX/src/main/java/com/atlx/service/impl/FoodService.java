package com.atlx.service.impl;

import com.atlx.dao.IFoodLocalMapper;
import com.atlx.pojo.FoodLocal;
import com.atlx.service.IFoodLocalService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhangshaowei
 * @date 2022/5/9 11:02
 * @description
 */
@Service
public class FoodService implements IFoodLocalService {
    @Autowired
    IFoodLocalMapper iFoodLocal;

    @Override
    public List<FoodLocal> queryAllMessage(int pageSize, int pageCode) {
        return iFoodLocal.queryAllMessage(pageSize,pageCode);
    }

    @Override
    public List<FoodLocal> queryByName(String foodName) {
        return iFoodLocal.queryByName(foodName);
    }

    @Override
    public FoodLocal queryFoodLocalByName(String foodName) {
        return iFoodLocal.queryFoodLocalByName(foodName);
    }

    @Override
    public List<FoodLocal> queryAllFoodInfo() {
        return iFoodLocal.queryAllFoodInfo();
    }


}
