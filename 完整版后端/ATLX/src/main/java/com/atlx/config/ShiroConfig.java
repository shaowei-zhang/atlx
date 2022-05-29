package com.atlx.config;

import com.atlx.realm.MyRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class ShiroConfig {
    //过滤器
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();
        filterFactoryBean.setSecurityManager(securityManager);
        //过滤器链的获取
        // 获取shiro的内置过滤器链
        Map<String, String> filterMap = filterFactoryBean.getFilterChainDefinitionMap();
        //过滤规则的实现
        //添加所有路径的过滤，并排除到静态资源信息
        filterMap.put("/","anon");
        filterMap.put("/login/*","anon");
        //filterMap.put("/**","authc");---身份认证功能完成后才可打开该注解，否则页面无法访问

        return filterFactoryBean;

    }


    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("SHA-256");//散列算法:MD2、MD5、SHA-1、SHA-256、SHA-384、SHA-512等。
        hashedCredentialsMatcher.setHashIterations(1024);//散列的次数，默认1次， 设置两次相当于 md5(md5(""));
        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(false);

        return hashedCredentialsMatcher;
    }

    /**
     * 注册身份验证
     * @param hashedCredentialsMatcher 凭证匹配器
     * @return
     */
    @Bean
    public MyRealm authRealm(HashedCredentialsMatcher hashedCredentialsMatcher) {
        MyRealm myRealm = new MyRealm();
        myRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return myRealm;
    }

    //安全管理器
    @Bean("securityManager")
    public DefaultWebSecurityManager defaultWebSecurityManager(@Qualifier("authRealm") MyRealm authRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(authRealm);
        return securityManager;
    }


}
