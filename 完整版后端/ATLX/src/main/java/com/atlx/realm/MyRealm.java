package com.atlx.realm;

import com.atlx.pojo.Permission;
import com.atlx.pojo.Role;
import com.atlx.pojo.User;
import com.atlx.service.IPermissionService;
import com.atlx.service.IRoleService;
import com.atlx.service.IUserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class MyRealm extends AuthorizingRealm {
    @Autowired
    private IUserService iUserService;
    @Autowired
    private IRoleService iRoleService;
    @Autowired
    private IPermissionService iPermissionService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String account = (String)principalCollection.getPrimaryPrincipal();

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        List<User> user = iUserService.findUserByAccount(account);
        Role role = iRoleService.queryRoleById(user.get(0).getRoleId());

        role.setPermissions(iPermissionService.queryPermissionByRole(role));

        info.addRole(role.getRoleName());

        //遍历权限信息并添加SimpleAuthorizationInfo里面
        for (Permission p:role.getPermissions()){
            info.addStringPermission(p.getUrl());
        }

        return info;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String account = authenticationToken.getPrincipal().toString();
        List<User> user = iUserService.findUserByAccount(account);

        return new SimpleAuthenticationInfo(user.get(0).getAccount(),user.get(0).getPassword(), ByteSource.Util.bytes(user.get(0).getSalt()),this.getName());
    }


}
