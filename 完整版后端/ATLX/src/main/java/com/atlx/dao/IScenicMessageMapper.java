package com.atlx.dao;

import com.atlx.pojo.ScenicMessage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IScenicMessageMapper extends BaseMapper<ScenicMessage> {
    List<ScenicMessage> findAllByName(String scenicName);
    List<ScenicMessage> findAllByAccount(String account);
}
