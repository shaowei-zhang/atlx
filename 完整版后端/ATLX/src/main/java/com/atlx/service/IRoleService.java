package com.atlx.service;

import com.atlx.pojo.Role;

import java.util.List;

public interface IRoleService {
    //查询所有的角色（岗位）信息
    List<Role> queryRole();

    //根据信息查询角色（岗位）
    Role queryRoleById(int id);

    //根据角色（岗位）名称账号查询
    Role queryRoleByRoleName(String description);

    //添加角色（岗位）
    boolean insertRole(Role role);

    //更新角色（岗位）
    boolean updateRole(Role role);

    //根据id删除角色（岗位）
    boolean deleteRole(Integer id);

}
