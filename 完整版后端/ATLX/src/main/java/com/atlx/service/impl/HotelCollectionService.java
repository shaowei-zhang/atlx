package com.atlx.service.impl;

import com.atlx.dao.IHotelCollectionMapper;
import com.atlx.dao.IHotelMessageMapper;
import com.atlx.pojo.HotelCollection;
import com.atlx.service.IHotelCoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelCollectionService implements IHotelCoService {
    @Autowired
    private IHotelCollectionMapper iHotelCollectionMapper;

    @Override
    public List<HotelCollection> queryHotelCollection() {
        return iHotelCollectionMapper.selectList(null);
    }

    @Override
    public HotelCollection queryById(Integer id) {
        return iHotelCollectionMapper.selectById(id);
    }

    @Override
    public boolean addHotelCollection(HotelCollection hotelCollection) {
        return iHotelCollectionMapper.insert(hotelCollection)>0;
    }

    @Override
    public boolean updateHotelCollection(HotelCollection hotelCollection) {
        return iHotelCollectionMapper.updateById(hotelCollection)>0;
    }

    @Override
    public boolean delHotelCollection(Integer id) {
        return iHotelCollectionMapper.deleteById(id)>0;
    }

    @Override
    public List<HotelCollection> queryAllByAccount(String account) {
        return iHotelCollectionMapper.queryAllByAccount(account);
    }
}
