package com.atlx.service.impl;

import com.atlx.pojo.Role;
import com.atlx.dao.IRoleMapper;
import com.atlx.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService implements IRoleService {

    @Autowired
    IRoleMapper iRoleMapper;

    @Override
    public List<Role> queryRole() {
        return iRoleMapper.queryRole();
    }

    @Override
    public Role queryRoleById(int id) {
        return iRoleMapper.queryRoleById(id);
    }

    @Override
    public Role queryRoleByRoleName(String description) {
        return iRoleMapper.queryRoleByRoleName(description);
    }

    @Override
    public boolean insertRole(Role role) {
        return iRoleMapper.insertRole(role)>0;
    }

    @Override
    public boolean updateRole(Role role) {
        return iRoleMapper.updateRole(role)>0;
    }

    @Override
    public boolean deleteRole(Integer id) {
        return iRoleMapper.deleteRole(id)>0;
    }

}
