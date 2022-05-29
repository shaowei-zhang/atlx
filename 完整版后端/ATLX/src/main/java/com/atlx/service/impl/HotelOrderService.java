package com.atlx.service.impl;

import com.atlx.dao.IHotelOrderMapper;
import com.atlx.pojo.HotelOrder;
import com.atlx.service.IHotelOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class HotelOrderService implements IHotelOrderService {
    @Resource
    IHotelOrderMapper iHotelOrderMapper;

    @Override
    public List<HotelOrder> findAll() {
        return iHotelOrderMapper.findAll();
    }

    @Override
    public List<HotelOrder> findByAccount(String account) {
        return iHotelOrderMapper.findByAccount(account);
    }

    @Override
    public boolean deleteById(Integer id) {
        return iHotelOrderMapper.deleteById(id)>0;
    }

    @Override
    public boolean deleteByMap(Map map) {
        return iHotelOrderMapper.deleteByMap(map)>0;
    }

    @Override
    public boolean updateById(HotelOrder hotelOrder) {
        return iHotelOrderMapper.updateById(hotelOrder)>0;
    }

    @Override
    public boolean insertOne(HotelOrder hotelOrder) {
        return iHotelOrderMapper.insert(hotelOrder)>0;
    }
}
