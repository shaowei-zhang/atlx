package com.atlx.service.impl;

import com.atlx.dao.IRegionMapper;
import com.atlx.dao.IScenicMapper;
import com.atlx.pojo.Region;
import com.atlx.pojo.Scenic;
import com.atlx.service.IScenicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ScenicService implements IScenicService {
    @Resource
    IScenicMapper scenicMapper;
    @Resource
    IRegionMapper regionMapper;
    @Override
    public List<Scenic> queryScenic() {
        return scenicMapper.selectList(null);
    }

    @Override
    public List<Scenic> queryById(int id) {
        return scenicMapper.queryById(id);
    }


    @Override
    public boolean insertScenic(Scenic scenic) {

        return scenicMapper.insert(scenic)>0;
    }

    @Override
    public boolean updateScenic(Scenic scenic) {
        return scenicMapper.update(scenic,null)>0;
    }

    @Override
    public boolean deleteScenic(int id) {
        return scenicMapper.deleteById(id)>0;
    }

    @Override
    public List<Scenic> query() {
        return scenicMapper.query();
    }

    @Override
    public List<Scenic> queryByName(String scenicName) {
        return scenicMapper.queryByName(scenicName);
    }

    @Override
    public List<Scenic> queryComment(String scenicName) {
        return scenicMapper.queryComment(scenicName);
    }

    @Override
    public Scenic queryIdByName(String scenicName) {
        return scenicMapper.queryIdByName(scenicName);
    }


}
