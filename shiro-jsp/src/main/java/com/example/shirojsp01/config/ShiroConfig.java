package com.example.shirojsp01.config;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hanbing
 * @date 2024-03-21
 */
@Configuration
public class ShiroConfig {

    /**
     * 创建shiro过滤器工厂
     * @param securityManager 安全管理器
     * @return ShiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") SecurityManager securityManager) {
        // 创建shiro过滤器工厂
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 注入安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 配置公共资源
        Map<String, String> authMap =  new HashMap<>(2);
        // 配置公共资源
        authMap.put("/login.jsp", "anon");
        authMap.put("/user/login", "anon");
        authMap.put("/register.jsp", "anon");
        authMap.put("/user/register", "anon");
        // 配置受限资源
        authMap.put("/**", "authc");
        authMap.put("/index.jsp", "authc");
        authMap.put("/", "authc");
        // 如果是受限资源，默认会重定向login.jsp
        // shiroFilterFactoryBean.setLoginUrl("/login.jsp");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(authMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 创建安全管理器
     * @param realm 自定义realm
     * @return DefaultSecurityManager
     */
    @Bean("securityManager")
    public DefaultWebSecurityManager getDefaultSecurityManager (@Qualifier("realm") Realm realm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm);
        return securityManager;
    }

    /**
     * 创建自定义realm
     * @return CustomerRealm
     */
    @Bean("realm")
    public Realm getRealm() {
        CustomerRealm customerRealm = new CustomerRealm();
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName("md5");
        credentialsMatcher.setHashIterations(1024);
        customerRealm.setCredentialsMatcher(credentialsMatcher);
        customerRealm.setCacheManager(new RedisCacheManager());
        // 开启全局缓存
        customerRealm.setCachingEnabled(true);
        // 开启认证缓存
        customerRealm.setAuthenticationCachingEnabled(true);
        // 开启授权缓存
        customerRealm.setAuthenticationCacheName("authenticationCache");
        customerRealm.setAuthorizationCachingEnabled(true);
        customerRealm.setAuthorizationCacheName("authorizationCache");
        return customerRealm;
    }
}
