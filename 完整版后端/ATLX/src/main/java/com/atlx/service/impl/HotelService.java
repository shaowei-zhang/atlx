package com.atlx.service.impl;

import com.atlx.dao.IHotelMapper;
import com.atlx.pojo.FoodLocal;
import com.atlx.pojo.Hotel;
import com.atlx.service.IHotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService implements IHotelService {
    @Autowired
    private IHotelMapper iHotelMapper;
    @Override
    public List<Hotel> queryHotel() {
        return iHotelMapper.selectList(null);
    }

    @Override
    public List<Hotel> query() {
        return iHotelMapper.queryHotel();
    }

    @Override
    public Hotel queryHotelById(Integer id) {
        return iHotelMapper.selectById(id);
    }

    @Override
    public boolean addHotel(Hotel hotel) {
        return iHotelMapper.insert(hotel)>0;
    }

    @Override
    public boolean updateHotel(Hotel hotel) {
        return iHotelMapper.updateById(hotel)>0;
    }

    @Override
    public boolean delHotel(Integer id) {
        return iHotelMapper.deleteById(id)>0;
    }

    @Override
    public List<Hotel> queryByName(String hotelName) {
        return iHotelMapper.queryByName(hotelName);
    }

    @Override
    public List<String> queryHotelImg() {
        return iHotelMapper.queryHotelImg();
    }

    @Override
    public List<Hotel> queryHotelComment(String hotelName) {
        return iHotelMapper.queryHotelComment(hotelName);
    }

    @Override
    public Hotel queryIdByName(String hotelName) {
        return iHotelMapper.queryIdByName(hotelName);
    }

    @Override
    public List<Hotel> queryComment(String account) {
        return iHotelMapper.queryComment(account);
    }


}
