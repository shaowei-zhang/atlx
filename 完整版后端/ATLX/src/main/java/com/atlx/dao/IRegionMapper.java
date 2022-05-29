package com.atlx.dao;

import com.atlx.pojo.Region;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author zhangshaowei
 * @date 2022/5/10 9:39
 * @description
 */
@Mapper
@Repository //代表持久层
public interface IRegionMapper extends BaseMapper<Region> {
//    这是前端的addForm操作
    Region queryName(String regionName);
    //根据名称查询ID
    Region queryRegionIDbyName(String regionName);
}
