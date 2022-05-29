package com.atlx.service.impl;

import com.atlx.dao.IScenicMessageMapper;
import com.atlx.pojo.ScenicMessage;
import com.atlx.service.IScenicMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScenicMessageService implements IScenicMessageService {
    @Autowired
    IScenicMessageMapper iScenicMessageMapper;


    @Override
    public List<ScenicMessage> findAllByName(String scenicName) {
        return iScenicMessageMapper.findAllByName(scenicName);
    }

    @Override
    public List<ScenicMessage> findAllByAccount(String account) {
        return iScenicMessageMapper.findAllByAccount(account);
    }
}
