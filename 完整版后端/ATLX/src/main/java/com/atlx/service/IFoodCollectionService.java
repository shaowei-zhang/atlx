package com.atlx.service;

import com.atlx.pojo.FoodCollection;

import java.util.List;

/**
 * @author zhangshaowei
 * @date 2022/5/10 16:08
 * @description
 */
public interface IFoodCollectionService {
    List<FoodCollection> queryAllCollection();
    /*
     * 这是点击个人收藏时，根据Account来这个人收藏的美食展示出来
     * */
    List<FoodCollection> queryAllByAccount(String account);
}
