package com.atlx.service;

import com.atlx.pojo.Region;
import com.atlx.pojo.Scenic;

import java.util.List;

public interface IScenicService {
    //查询景区信息
    List<Scenic> queryScenic();
    //根据ID查询景区信息
    List<Scenic> queryById(int id);

    //新增景区信息
    boolean insertScenic(Scenic scenic);
    //修改景区信息
    boolean updateScenic(Scenic scenic);
    //删除景区信息
    boolean deleteScenic(int id);
    //两表查询
    List<Scenic> query();
    //根据名称查询景区信息
    List<Scenic> queryByName(String scenicName);
    List<Scenic> queryComment(String scenicName);

    Scenic queryIdByName(String scenicName);
}
