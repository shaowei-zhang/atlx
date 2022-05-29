package com.atlx.service;

import com.atlx.pojo.TouristRoutes;
import com.baomidou.mybatisplus.core.conditions.Wrapper;

import java.io.Serializable;
import java.util.List;

public interface ITouristRoutesService {
    //展示所有路线
    public List<TouristRoutes> selectList() ;
    //添加路线
     int insert(TouristRoutes entity) ;
//通过Id删除路线
    public int deleteById(Integer id) ;
//通过Id查询路线
    public TouristRoutes selectById(Integer id) ;
    //修改路线
    public int updateById(TouristRoutes touristRoutes);
    //起始地点查询
    List<TouristRoutes> selectOne(String account);



}
