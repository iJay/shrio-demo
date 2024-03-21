package org.example.www;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @author hanbing
 * @date 2024-03-20
 */
public class CustomRealm extends AuthorizingRealm {

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 通过token获取用户名
        String username = authenticationToken.getPrincipal().toString();
        // 模拟从数据库中获取用户名对应的密码
        String userNameFormDB = "lisi";
        if (userNameFormDB.equals(username)) {
            return new SimpleAuthenticationInfo(username, "123", this.getName());
        }
        return null;
    }
}
