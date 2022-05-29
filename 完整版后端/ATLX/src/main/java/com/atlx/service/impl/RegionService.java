package com.atlx.service.impl;

import com.atlx.dao.IRegionMapper;
import com.atlx.pojo.Region;
import com.atlx.service.IRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhangshaowei
 * @date 2022/5/10 9:45
 * @description
 */
@Service
public class RegionService implements IRegionService{
    @Autowired
    IRegionMapper iRegionMapper;

    @Override
    public Region queryName(String regionName) {
        return iRegionMapper.queryName(regionName);
    }

    @Override
    public List<Region> queryRegion() {
        return iRegionMapper.selectList(null);
    }

    @Override
    public Region queryRegionById(int id) {
        return iRegionMapper.selectById(id);
    }

    @Override
    public Region queryRegionByName(String regionName) {
        return iRegionMapper.queryRegionIDbyName(regionName);
    }

    @Override
    public boolean insertRegion(Region region) {
        return iRegionMapper.insert(region)>0;
    }

    @Override
    public boolean updateRegion(Region region) {
        return iRegionMapper.update(region,null)>0;
    }

    @Override
    public boolean deleteRegion(int id) {
        return iRegionMapper.deleteById(id)>0;
    }
}
