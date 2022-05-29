package com.atlx.dao;

import com.atlx.pojo.FoodCollection;
import com.atlx.pojo.FoodLocal;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zhangshaowei
 * @date 2022/5/10 15:28
 * @description
 */
@Mapper
@Repository
public interface IFoodCollectionMapper extends BaseMapper<FoodCollection> {
    List<FoodCollection> queryAllCollection();
    /*
    * 这是点击个人收藏时，根据Account来这个人收藏的美食展示出来
    * */
    List<FoodCollection> queryAllByAccount(String account);
}
