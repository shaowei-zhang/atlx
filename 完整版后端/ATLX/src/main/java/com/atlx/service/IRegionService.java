package com.atlx.service;

import com.atlx.pojo.Region;

import java.util.List;

/**
 * @author zhangshaowei
 * @date 2022/5/10 9:44
 * @description
 */
public interface IRegionService {
    Region queryName(String regionName);
    //查询区域信息
    List<Region> queryRegion();
    //根据ID查询区域信息
    Region queryRegionById(int id);
    //根据区域名查询数据
    Region queryRegionByName(String regionName);
    //新增区域信息
    boolean insertRegion(Region region);
    //修改区域信息
    boolean updateRegion(Region region);
    //删除信息
    boolean deleteRegion(int id);
}
