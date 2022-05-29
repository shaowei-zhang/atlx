package com.atlx.service;

import com.atlx.pojo.SysChildrenMenu;
import com.atlx.pojo.SysFatherMenu;

import java.util.List;

public interface IMenuService {
    public List<SysChildrenMenu> findChildrenMenuByFatherMenuId(Integer fatherMenuId);
    public List<SysFatherMenu> findFatherMenu();
}
