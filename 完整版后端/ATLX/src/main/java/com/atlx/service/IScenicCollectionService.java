package com.atlx.service;

import com.atlx.pojo.ScenicCollection;

import java.util.List;

public interface IScenicCollectionService {
    //查询景区收藏
    List<ScenicCollection> queryScenicCollection();
    //根据信息查询景区收藏
    ScenicCollection queryScenicCollectionById(int id);
    //新增新增景区收藏
    boolean insertScenicCollection(ScenicCollection scenicCollection);
    //修改景区收藏
    boolean updateScenicCollection(ScenicCollection scenicCollection);
    //删除景区收藏
    boolean deleteScenicCollection(int id);

    List<ScenicCollection> queryAllByAccount(String account);
}
