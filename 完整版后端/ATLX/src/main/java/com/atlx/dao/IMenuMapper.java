package com.atlx.dao;

import com.atlx.pojo.SysChildrenMenu;
import com.atlx.pojo.SysFatherMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IMenuMapper {
    public List<SysChildrenMenu> findChildrenMenuByFatherMenuId(Integer fatherMenuId);
    public List<SysFatherMenu> findFatherMenu();
}
