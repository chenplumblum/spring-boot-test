package com.plumblum.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

/**
 * @Auther: cpb
 * @Date: 2019/1/14 20:25
 * @Description:
 */
@Configuration
@EnableAuthorizationServer
public class OAuthAuthorizationConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private TokenStore tokenStore;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        client信息包括：clientId（客户端id）、secret（客户端密钥）、scope（权限范围）、authorizedGrantTypes（权限类型）、authorities（授予client的权限），token的有效期
        clients.inMemory().withClient("test")//配置客户端ID
                .authorizedGrantTypes("password", "refresh_token")//设置验证方式为密码模式：grant_type为password
                .scopes("read", "write")//设置权限范围
                .secret("123456")//设置客户端密钥
                .accessTokenValiditySeconds(10000) //token过期时间
                .refreshTokenValiditySeconds(10000); //refresh过期时间
    }

    /**
     *
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//        声明授权和token的端点以及token的服务的一些配置信息，比如采用什么存储方式，token的有效期等
        //相关授权类型
        //（1）authenticationManager：直接注入一个AuthenticationManager，自动开启密码授权类型
        //（2）userDetailsService：如果注入UserDetailsService，那么将会启动刷新token授权类型，会判断用户是否还是存活的
        //（3）authorizationCodeServices：AuthorizationCodeServices的实例，auth code 授权类型的服务
        //（4）implicitGrantService：imlpicit grant
        //（5）tokenGranter：
        endpoints
                .tokenStore(tokenStore)//token存储类型
                .authenticationManager(authenticationManager)//授权类型
                .userDetailsService(userDetailsService); //配置userService 这样每次认证的时候会去检验用户是否锁定，有效等
    }

    /**
     * 存储token
     * @return
     */
    @Bean
    public TokenStore tokenStore() {
//        token存储方式共有三种分别是InMemoryTokenStore：（1）存放内存中，不会持久化（2）JdbcTokenStore：存放数据库中（3）Jwt: json web token
        //使用内存的tokenStore
        return new InMemoryTokenStore();
    }


}
