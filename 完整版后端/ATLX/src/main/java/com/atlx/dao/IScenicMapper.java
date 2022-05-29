package com.atlx.dao;

import com.atlx.pojo.Scenic;
import com.atlx.pojo.ScenicMessage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.tomcat.util.net.SecureNioChannel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface IScenicMapper extends BaseMapper<Scenic>{
    //两表查询
    List<Scenic> query();
    //根据名称查询景区信息
    List<Scenic> queryByName(String scenicName);
    //根据ID查询景区信息
    List<Scenic> queryById(int id);
    //修改景区信息
    List<Scenic> updateScenic(String scenicName,String scenicImg);
//    查询评论
    List<Scenic> queryComment(String scenicName);

    //这个是用来通过scenicName来查询ID，进而插入到scenicMessage表中的
    Scenic queryIdByName(String scenicName);

}
