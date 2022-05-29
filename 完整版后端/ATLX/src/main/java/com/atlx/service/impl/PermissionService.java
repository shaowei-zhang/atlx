package com.atlx.service.impl;

import com.atlx.pojo.Permission;
import com.atlx.pojo.Role;
import com.atlx.dao.IPermissionMapper;
import com.atlx.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService implements IPermissionService {
    @Autowired
    private IPermissionMapper iPermissionMapper;
    @Override
    public List<Permission> queryPermissionByRole(Role role) {
        return iPermissionMapper.queryPermissionByRole(role);
    }
}
