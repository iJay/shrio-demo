package org.example.www;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.Arrays;
import java.util.List;

/**
 * @author hanbing
 * @date 2024-03-20
 */
public class CustomMd5Realm extends AuthorizingRealm {

    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // 获取当前身份信息
        String userName = principals.getPrimaryPrincipal().toString();
        // 获取权限对象
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        // 添加角色 从数据获取
        simpleAuthorizationInfo.addRoles(Arrays.asList("admin", "user"));
        // 添加权限
        simpleAuthorizationInfo.addStringPermissions(List.of("user:*:" + userName));
        return simpleAuthorizationInfo;
    }

    // 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 从token获取用户名
        String principal = authenticationToken.getPrincipal().toString();
        // 根据用户名查询数据库
        String userNameFromDB = "jack";
        if (userNameFromDB.equals(principal)) {
            // 参数1：返回数据库中正确的用户名
            // 参数2：返回数据库中md5+salt之后的密码
            // 参数3：注册时的随机盐 本例子中使用用户名作为盐
            // 参数4：当前realm的名字
            return new SimpleAuthenticationInfo(principal, "42df4a630b4b9915e8bcc0ef2d58c0eb", ByteSource.Util.bytes(principal), this.getName());
        }
        return null;
    }
}
