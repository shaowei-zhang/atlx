package com.atlx.dao;

import com.atlx.pojo.Permission;
import com.atlx.pojo.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IRoleMapper {
    //查询所有的角色信息
    List<Role> queryRole();

    //根据信息查询角色
    Role queryRoleById(int id);

    //根据角色名称账号查询
    Role queryRoleByRoleName(String description);

    //添加角色
    int insertRole(Role role);

    //更新角色
    int updateRole(Role role);

    //根据id删除角色
    int deleteRole(Integer id);

    Permission queryPermissionIdByRoleId(Integer id);
}
