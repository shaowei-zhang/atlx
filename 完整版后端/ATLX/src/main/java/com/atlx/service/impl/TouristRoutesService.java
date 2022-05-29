package com.atlx.service.impl;

import com.atlx.dao.ITouristRoutesMapper;
import com.atlx.pojo.TouristRoutes;
import com.atlx.service.ITouristRoutesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
@Service
public class TouristRoutesService implements ITouristRoutesService {
    @Resource
    ITouristRoutesMapper touristRoutesMapper;

    @Override
    public List<TouristRoutes> selectList() {
        return touristRoutesMapper.selectList(null);
    }

    @Override
    public int insert(TouristRoutes entity) {
        return touristRoutesMapper.insert(entity);
    }

    @Override
    public int deleteById(Integer id) {
        return touristRoutesMapper.deleteById(id);
    }

    @Override
    public TouristRoutes selectById(Integer id) {
        return touristRoutesMapper.selectById(id);
    }
//修改暂时不知能否实现
    @Override
    public int updateById(TouristRoutes touristRoutes) {
        return touristRoutesMapper.updateById(touristRoutes);
    }

    @Override
    public List<TouristRoutes> selectOne(String account) {
        return touristRoutesMapper.selectOne(account);
    }

}
