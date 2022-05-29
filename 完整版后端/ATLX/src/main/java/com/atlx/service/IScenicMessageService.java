package com.atlx.service;

import com.atlx.pojo.HotelMessage;
import com.atlx.pojo.ScenicMessage;

import java.util.List;

public interface IScenicMessageService {
    List<ScenicMessage> findAllByName(String scenicName);
    List<ScenicMessage> findAllByAccount(String account);
}
