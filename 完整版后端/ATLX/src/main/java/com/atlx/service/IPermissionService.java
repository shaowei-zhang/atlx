package com.atlx.service;

import com.atlx.pojo.Permission;
import com.atlx.pojo.Role;

import java.util.List;

public interface IPermissionService {
    List<Permission> queryPermissionByRole(Role role);
}
