package com.youmayon.lebang.config;

import com.youmayon.lebang.constant.SecurityConstants;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

import static org.springframework.http.HttpMethod.*;

/**
 * Auth2 资源配置类
 * Created by Jawinton on 16/12/24.
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    /**
     * 配置唯一资源ID
     * @param resources
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(SecurityConstants.RESOURCE_ID).stateless(false);
    }

    /**
     * 配置资源访问权限
     * @param http
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(POST, "/users").hasAuthority("ROLE_ADMIN")
                .antMatchers(GET, "/users/me").authenticated()
                .antMatchers(PATCH, "/users/password").authenticated()
                .antMatchers(DELETE, "/users/logout").authenticated()
                .antMatchers("/users/**").hasAuthority("ROLE_ADMIN")

                .antMatchers("/task_types/**").hasAuthority("ROLE_ADMIN")

                .antMatchers("/tasks/**").hasAuthority("ROLE_ADMIN")

                .anyRequest().authenticated();
        http.exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
    }
}
