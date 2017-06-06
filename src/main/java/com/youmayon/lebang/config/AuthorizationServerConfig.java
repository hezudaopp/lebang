package com.youmayon.lebang.config;

import com.youmayon.lebang.constant.SecurityConstants;
import com.youmayon.lebang.enums.ClientRole;
import com.youmayon.lebang.service.AppService;
import com.youmayon.lebang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.builders.ClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

import javax.sql.DataSource;

/**
 * Auth2 认证服务器配置类
 * Created by Jawinton on 16/12/24.
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private AppService appService;

    @Autowired
    private UserApprovalHandler userApprovalHandler;

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthorizationCodeServices authorizationCodeServices;

    @Autowired
    private DataSource dataSource;

    /**
     * 客户端认证配置
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        ClientDetailsServiceBuilder csb = clients.jdbc(dataSource);
        csb.withClient("lebang_client")
                .secret("lnpOeaUQrmQj7r9a6f94ltjCuzqY7jEvO")
                .authorizedGrantTypes(new String[]{"password", "refresh_token"})
                .authorities(new String[]{ClientRole.ROLE_CLIENT.name()})
                .scopes(new String[]{"read", "write"})
                .accessTokenValiditySeconds(7200)
                .refreshTokenValiditySeconds(86400 * 30);

//        for (App app : appService.list(true)) {
//            csb.withClient(app.getId().toString())
//                    .secret(app.getSecret())
//                    .authorizedGrantTypes(new String[]{"client_credentials"})
//                    .authorities(new String[]{ClientRole.ROLE_APP.name()})
//                    .scopes(new String[]{"read", "trust"})
//                    .accessTokenValiditySeconds(7200);
//        }
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .tokenStore(tokenStore)
                .userApprovalHandler(userApprovalHandler)
                .authenticationManager(authenticationManager)
                .authorizationCodeServices(authorizationCodeServices)
                .approvalStoreDisabled();
    }

    /**
     * 配置realm域
     * @param oauthServer
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.realm(SecurityConstants.REALM);
    }
}
