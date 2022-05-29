package com.atlx.service.impl;

import com.atlx.dao.IMenuMapper;
import com.atlx.pojo.SysChildrenMenu;
import com.atlx.pojo.SysFatherMenu;
import com.atlx.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService implements IMenuService {

    @Autowired
    IMenuMapper iMenuMapper;

    @Override
    public List<SysChildrenMenu> findChildrenMenuByFatherMenuId(Integer fatherMenuId) {
        return iMenuMapper.findChildrenMenuByFatherMenuId(fatherMenuId);
    }

    @Override
    public List<SysFatherMenu> findFatherMenu() {
        return iMenuMapper.findFatherMenu();
    }
}
