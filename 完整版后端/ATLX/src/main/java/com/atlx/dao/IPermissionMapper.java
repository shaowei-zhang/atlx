package com.atlx.dao;

import com.atlx.pojo.Permission;
import com.atlx.pojo.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IPermissionMapper {
    List<Permission> queryPermissionByRole(Role role);
}
