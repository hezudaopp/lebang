package com.youmayon.lebang.config;

import com.youmayon.lebang.constant.SecurityConstants;
import com.youmayon.lebang.domain.App;
import com.youmayon.lebang.domain.OAuth2Client;
import com.youmayon.lebang.service.AppService;
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
import org.springframework.security.oauth2.provider.token.TokenStore;

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

    /**
     * 客户端认证配置
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        ClientDetailsServiceBuilder csb = clients.inMemory();
        for (OAuth2Client client : SecurityConstants.O_AUTH2_CLIENTS) {
            csb.withClient(client.getId())
                    .secret(client.getSecret())
                    .authorizedGrantTypes(client.getGrantTypes())
                    .authorities(client.getRoles())
                    .scopes(client.getScopes())
                    .accessTokenValiditySeconds(client.getAccessTokenValidSeconds())
                    .refreshTokenValiditySeconds(client.getAccessTokenValidSeconds());
        }

        for (App app : appService.list(true)) {
            csb.withClient(app.getId().toString())
                    .secret(app.getSecret())
                    .authorizedGrantTypes(new String[]{"client_credentials"})
                    .authorities(new String[]{"ROLE_APP"})
                    .scopes(new String[]{"read", "trust"})
                    .accessTokenValiditySeconds(7200);
        }
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .tokenStore(tokenStore)
                .userApprovalHandler(userApprovalHandler)
                .authenticationManager(authenticationManager);
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
