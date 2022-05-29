package com.atlx.service.impl;

import com.atlx.dao.IScenicOrderMapper;
import com.atlx.pojo.ScenicOrder;
import com.atlx.service.IScenicOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class ScenicOrderService implements IScenicOrderService {
    @Resource
    IScenicOrderMapper iScenicOrderMapper;

    @Override
    public List<ScenicOrder> findAll() {
        return iScenicOrderMapper.findAll();
    }

    @Override
    public List<ScenicOrder> findByAccount(String account) {
        return iScenicOrderMapper.findByAccount(account);
    }

    @Override
    public boolean deleteById(Integer id) {
        return iScenicOrderMapper.deleteById(id)>0;
    }

    @Override
    public boolean deleteByMap(Map map) {
        return iScenicOrderMapper.deleteByMap(map)>0;
    }

    @Override
    public boolean updateById(ScenicOrder scenicOrder) {
        return iScenicOrderMapper.updateById(scenicOrder)>0;
    }

    @Override
    public boolean insertOne(ScenicOrder scenicOrder) {
        return iScenicOrderMapper.insert(scenicOrder)>0;
    }
}
