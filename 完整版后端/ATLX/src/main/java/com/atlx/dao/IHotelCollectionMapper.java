package com.atlx.dao;

/**
 * @author zhangshaowei
 * @date 2022/5/22 13:00
 * @description
 */
import com.atlx.pojo.FoodCollection;
import com.atlx.pojo.HotelCollection;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface IHotelCollectionMapper extends BaseMapper<HotelCollection> {
    List<HotelCollection> queryAllByAccount(String account);
}
