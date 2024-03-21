package com.example.shirojsp01.config;

import com.example.shirojsp01.model.Perms;
import com.example.shirojsp01.model.Role;
import com.example.shirojsp01.model.User;
import com.example.shirojsp01.service.PermsService;
import com.example.shirojsp01.service.RoleService;
import com.example.shirojsp01.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.security.auth.login.AccountNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author hanbing
 * @date 2024-03-21
 */
public class CustomerRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermsService permsService;

    /**
     * 授权
     * @param principalCollection 主体集合
     * @return AuthorizationInfo
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user = (User) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        // 根据用户名查询角色列表
        List<Role> roles = roleService.findRolesByUserName(user.getUsername());
        List<String> roleNames;
        if (!ObjectUtils.isEmpty(roles)) {
            roleNames = roles.stream().map(Role::getName).collect(Collectors.toList());
            simpleAuthorizationInfo.addRoles(roleNames);
        }
        if (!ObjectUtils.isEmpty(roles)) {
            List<String> roleIds = roles.stream().map(role -> {
                return role.getId().toString();
            }).collect(Collectors.toList());
            List<Perms> perms = permsService.findPermsByRoleIds(roleIds);
            if (!ObjectUtils.isEmpty(perms)) {
                List<String> permNames = perms.stream().map(Perms::getName).collect(Collectors.toList());
                simpleAuthorizationInfo.addStringPermissions(permNames);
            }
        }
        return simpleAuthorizationInfo;
    }

    /**
     * 认证
     * @param authenticationToken 认证令牌
     * @return AuthenticationInfo
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("==========================");
        String userName = (String) authenticationToken.getPrincipal();
        User user = userService.findByUsername(userName);
        if (ObjectUtils.isEmpty(user)) {
            throw new AuthenticationException("账号不存在");
        }
        return new SimpleAuthenticationInfo(user, user.getPassword(), ByteSource.Util.bytes(user.getSalt()), this.getName());
    }
}
