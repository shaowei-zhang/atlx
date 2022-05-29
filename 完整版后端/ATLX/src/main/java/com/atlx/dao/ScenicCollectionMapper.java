package com.atlx.dao;

import com.atlx.pojo.ScenicCollection;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ScenicCollectionMapper extends BaseMapper<ScenicCollection> {
    List<ScenicCollection> queryAllByAccount(String account);
}
