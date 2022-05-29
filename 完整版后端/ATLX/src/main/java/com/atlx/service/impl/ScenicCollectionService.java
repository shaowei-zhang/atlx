package com.atlx.service.impl;

import com.atlx.dao.ScenicCollectionMapper;
import com.atlx.pojo.ScenicCollection;
import com.atlx.service.IScenicCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScenicCollectionService implements IScenicCollectionService {
    @Autowired
    ScenicCollectionMapper scenicCollectionMapper;
    @Override
    public List<ScenicCollection> queryScenicCollection() {
        return scenicCollectionMapper.selectList(null);
    }

    @Override
    public ScenicCollection queryScenicCollectionById(int id) {
        return scenicCollectionMapper.selectById(id);
    }

    @Override
    public boolean insertScenicCollection(ScenicCollection scenicCollection) {
        return scenicCollectionMapper.insert(scenicCollection)>0;
    }

    @Override
    public boolean updateScenicCollection(ScenicCollection scenicCollection) {
        return scenicCollectionMapper.update(scenicCollection,null)>0;
    }

    @Override
    public boolean deleteScenicCollection(int id) {
        return scenicCollectionMapper.delete(null)>0;
    }

    @Override
    public List<ScenicCollection> queryAllByAccount(String account) {
        return scenicCollectionMapper.queryAllByAccount(account);
    }
}
