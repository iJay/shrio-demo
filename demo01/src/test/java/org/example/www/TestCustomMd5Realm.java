package org.example.www;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * @author hanbing
 * @date 2024-03-20
 */
public class TestCustomMd5Realm {
    @Test
    public void test () {
        // 1. 创建安全管理器对象
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        // 2. 设置realm
        CustomMd5Realm customMd5Realm = new CustomMd5Realm();
        // 2.1 使用hash凭证匹配器
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        // 2.2 设置加密算法
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        // 2.3 设置散列次数
        hashedCredentialsMatcher.setHashIterations(1024);
        // 2.4 设置realm的md5加密凭据匹配器
        customMd5Realm.setCredentialsMatcher(hashedCredentialsMatcher);
        // 2.5 设置realm
        securityManager.setRealm(customMd5Realm);
        // 3. SecurityUtils 绑定安全管理器
        SecurityUtils.setSecurityManager(securityManager);
        // 4. 获取subject
        Subject subject = SecurityUtils.getSubject();
        // 5. 创建令牌
        UsernamePasswordToken token = new UsernamePasswordToken("jack", "123");
        // 6. 用户认证
        try {
            System.out.println("认证前：" + subject.isAuthenticated());
            subject.login(token);
            System.out.println("认证后：" + subject.isAuthenticated());
            System.out.println("认证成功");
        }  catch (UnknownAccountException unknownAccountException) {
            unknownAccountException.printStackTrace();
            System. out .println( "用户名验证错误" );
        }  catch (IncorrectCredentialsException incorrectCredentialsException) {
            incorrectCredentialsException.printStackTrace();
            System. out .println( "密码验证错误" );
        }
    }
}
