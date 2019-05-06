package com.woniu.yoga.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.woniu.yoga.user.realm.AppCodeRealm;
import com.woniu.yoga.user.realm.AppPwdRealm;
import org.apache.shiro.authc.AbstractAuthenticator;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: java类作用描述ShiroConfig
 *  配置shiro
 * @Author: lxy
 * @time: 2019/4/22 22:49
 */
//@Configuration
public class ConfigShiro {
    //配置过滤器 lxy
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();
        filterFactoryBean.setSecurityManager(securityManager);
        Map map = new LinkedHashMap();
        map.put("/loginPc.html", "anon");
        map.put("/loginApp.html", "anon");
        map.put("/findPwd.html", "anon");
        map.put("/regByEmail.html", "anon");
        map.put("/regByPhone.html", "anon");
        map.put("/regPc.html", "anon");
        map.put("/updateUserPwd.html", "anon");
        map.put("*.css", "anon");
//        map.put("/userApp/*","anon");
//        map.put("*.js", "anon");
        map.put("/*", "authc");
        map.put("/logout", "logout");
        filterFactoryBean.setFilterChainDefinitionMap(map);
        filterFactoryBean.setLoginUrl("loginPc.html");
        filterFactoryBean.setUnauthorizedUrl("403.html");
        return filterFactoryBean;

    }

    //配置安全管理器 lxy
    @Bean
    public SecurityManager securityManager1(AppPwdRealm appPwdRealm, AppCodeRealm appCodeRealm, AbstractAuthenticator abstractAuthenticator) {
        DefaultWebSecurityManager webSecurityManager = new DefaultWebSecurityManager();
        List<Realm> realms = new ArrayList<>();
        realms.add(appPwdRealm);
        realms.add(appCodeRealm);
        webSecurityManager.setRealms(realms);
        webSecurityManager.setAuthenticator(abstractAuthenticator);
        return webSecurityManager;
    }

    //配置自定义域邮箱登录
    @Bean
    public AppPwdRealm customRealm(HashedCredentialsMatcher matcher, MemoryConstrainedCacheManager memoryConstrainedCacheManager) {
        System.out.println("邮箱自定义");
        AppPwdRealm realm = new AppPwdRealm();
        realm.setCredentialsMatcher(matcher);
        realm.setCacheManager(memoryConstrainedCacheManager);
        return realm;
    }

    //配置手机验证码登录
    @Bean
    public AppCodeRealm phoneRealm(MemoryConstrainedCacheManager memoryConstrainedCacheManager) {
        System.out.println("手机自定义");
        AppCodeRealm appCodeRealm = new AppCodeRealm();
        appCodeRealm.setCacheManager(memoryConstrainedCacheManager);
        return appCodeRealm;
    }

    /**
     * 认证器
     */
    @Bean
    public AbstractAuthenticator abstractAuthenticator(AppPwdRealm appPwdRealm, AppCodeRealm appCodeRealm) {
        // 自定义模块化认证器，用于解决多realm抛出异常问题
        ModularRealmAuthenticator authenticator = new ModularRealmAuthenticator();
        // 认证策略：AtLeastOneSuccessfulStrategy(默认)，AllSuccessfulStrategy，FirstSuccessfulStrategy
        authenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());

        // 加入realms
        List<Realm> realms = new ArrayList<>();
        realms.add(appPwdRealm);
        realms.add(appCodeRealm);
        authenticator.setRealms(realms);

        return authenticator;
    }

    //密码匹配器
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("md5");
        matcher.setHashIterations(2);
        return matcher;
    }

    //整合thymeleaf
    @Bean
    public ShiroDialect shiroDialect() {

        ShiroDialect dialect = new ShiroDialect();
        return dialect;
    }

    //针对类使用aop代理，因为shiro使用注解方式实现对请求进行验证
    @Bean
    public DefaultAdvisorAutoProxyCreator proxyCreator() {
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }

    //注解生效通知
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager manager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(manager);
        return advisor;
    }

    //缓存管理
    @Bean
    public MemoryConstrainedCacheManager memoryConstrainedCacheManager() {
        MemoryConstrainedCacheManager memoryConstrainedCacheManager = new MemoryConstrainedCacheManager();
        return memoryConstrainedCacheManager;
    }
}