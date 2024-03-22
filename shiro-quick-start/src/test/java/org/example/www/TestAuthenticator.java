package org.example.www;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * @author hanbing
 * @date 2024-03-20
 */
public class TestAuthenticator {
    @Test
    public void test() {
        // 1. 创建安全管理器对象
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        // 2. 设置realm
        securityManager.setRealm(new IniRealm("classpath:shiro.ini"));
        // 3. SecurityUtils 绑定安全管理器
        SecurityUtils.setSecurityManager(securityManager);
        // 4. 获取主体对象
        Subject subject = SecurityUtils.getSubject();
        // 5. 创建令牌
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "456");
        // 6. 用户认证
        try {
            System.out.println("认证前：" + subject.isAuthenticated());
            subject.login(token);
            System.out.println("认证后：" + subject.isAuthenticated());
            System.out.println("认证成功");
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            System.out.println("认证失败: 用户名不存在~");
        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            System.out.println("认证失败: 密码错误~");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("认证失败: 其他错误~");
        }
        // 7. 退出
        subject.logout();
    }
}
