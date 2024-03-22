package org.example.www;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author hanbing
 * @date 2024-03-21
 */
public class TestCustomerMd5RealmAuthenticator {
    @Test
    public void test () {
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        CustomMd5Realm customMd5Realm = new CustomMd5Realm();
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher("md5");
        hashedCredentialsMatcher.setHashIterations(1024);
        customMd5Realm.setCredentialsMatcher(hashedCredentialsMatcher);
        securityManager.setRealm(customMd5Realm);
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("jack", "123");
        try {
            subject.login(token);
            System.out.println("登录成功");
        }catch (UnknownAccountException e) {
            e.printStackTrace();
            System.out.println("用户名错误");
        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            System.out.println("密码错误");
        }

        if (subject.isAuthenticated()) {
            System.out.println("************基于角色的控制************");
            // 基于角色的权限控制，是否有admin角色
            System.out.println(subject.hasRole("admin"));
            // 基于角色的权限控制，是否有其中的一种角色信息
            System.out.println(Arrays.toString(subject.hasRoles(Arrays.asList("admin", "super"))));
            // 基于角色的权限控制，是否同时具有提供的所有权限
            System.out.println(subject.hasAllRoles(Arrays.asList("admin", "super")));

            try {
                System.out.println("************基于权限的控制************");
                // 资源标识符:操作:资源类型，比如当前用户是否对user模块具有所有权限就可以这样写（user:*:xiaochen）
                System.out.println(subject.isPermitted("user:*:jack"));
                // 分别具有哪些权限
                System.out.println(Arrays.toString(subject.isPermitted("user:*:jack", "user:update:jack")));
                // 同时具有哪些权限
                System.out.println(subject.isPermittedAll("user:*:jack", "user:update:jack"));
            } catch (AuthorizationException e) {
                e.printStackTrace();
                System.out.println("未经授权，无法访问");
            }
        }
    }
}
