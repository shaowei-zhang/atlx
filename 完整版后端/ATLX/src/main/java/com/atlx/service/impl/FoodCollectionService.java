package com.atlx.service.impl;

import com.atlx.dao.IFoodCollectionMapper;
import com.atlx.pojo.FoodCollection;
import com.atlx.service.IFoodCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhangshaowei
 * @date 2022/5/10 16:08
 * @description
 */
@Service
public class FoodCollectionService implements IFoodCollectionService {
    @Autowired
    private IFoodCollectionMapper iFoodCollectionMapper;
    @Override
    public List<FoodCollection> queryAllCollection() {
        return iFoodCollectionMapper.queryAllCollection();
    }

    @Override
    public List<FoodCollection> queryAllByAccount(String account) {
        return iFoodCollectionMapper.queryAllByAccount(account);
    }
}
