package com.atlx.service.impl;

import com.atlx.dao.IHotelMessageMapper;
import com.atlx.pojo.HotelMessage;
import com.atlx.service.IHotelMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelMessageService implements IHotelMessageService {
    @Autowired
    IHotelMessageMapper iHotelMessageMapper;

    @Override
    public List<HotelMessage> findAllByName(String hotelName) {
        return iHotelMessageMapper.findAllByName(hotelName);
    }

    @Override
    public List<HotelMessage> findAllByAccount(String account) {
        return iHotelMessageMapper.findAllByAccount(account);
    }
}
