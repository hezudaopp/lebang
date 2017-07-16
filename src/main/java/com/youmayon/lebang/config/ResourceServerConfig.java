package com.youmayon.lebang.config;

import com.youmayon.lebang.constant.SecurityConstants;
import com.youmayon.lebang.enums.ClientRole;
import com.youmayon.lebang.enums.Role;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
                .antMatchers(HttpMethod.POST, "/user_tasks").access("#oauth2.clientHasRole('" + ClientRole.ROLE_APP.name() + "')")
                .antMatchers(HttpMethod.PATCH, "/user_tasks/**/completed").access("#oauth2.clientHasRole('" + ClientRole.ROLE_APP.name() + "')")
                .antMatchers(HttpMethod.GET, "/tasks").access("#oauth2.clientHasAnyRole('" + ClientRole.ROLE_APP.name() + ", " + ClientRole.ROLE_CLIENT.name() + "')")
                .antMatchers(HttpMethod.GET, "/tasks/**").access("#oauth2.clientHasAnyRole('" + ClientRole.ROLE_APP.name() + ", " + ClientRole.ROLE_CLIENT.name() + "')")
                .antMatchers(HttpMethod.GET, "/user_tasks").access("#oauth2.clientHasAnyRole('" + ClientRole.ROLE_APP.name() + ", " + ClientRole.ROLE_CLIENT.name() + "')")
                .antMatchers("/image/**").access("#oauth2.clientHasAnyRole('" + ClientRole.ROLE_APP.name() + ", " + ClientRole.ROLE_CLIENT.name() + "')")

                .antMatchers(POST, "/users").hasAuthority(Role.ROLE_ADMIN.name())
                .antMatchers(GET, "/users/me").authenticated()
                .antMatchers(PATCH, "/users/password").authenticated()
                .antMatchers(DELETE, "/users/logout").authenticated()
                .antMatchers("/users/**").hasAuthority(Role.ROLE_ADMIN.name())

                .antMatchers("/tasks").hasAuthority(Role.ROLE_ADMIN.name())
                .antMatchers("/tasks/**").hasAuthority(Role.ROLE_ADMIN.name())
                .antMatchers("/task_types/**").hasAuthority(Role.ROLE_ADMIN.name())
                .antMatchers("/task_procedures/**").hasAnyAuthority(Role.ROLE_ADMIN.name())
                .antMatchers("/apps/**").hasAuthority(Role.ROLE_ADMIN.name())

                .antMatchers("/user_tasks/**").hasAnyAuthority(Role.ROLE_ADMIN.name(), Role.ROLE_TASK_REVIEWER.name())

                .antMatchers("/statistics/**").hasAnyAuthority(Role.ROLE_ADMIN.name(), Role.ROLE_REPORT_VIEWER.name())

                .anyRequest().authenticated();
//        .anyRequest().permitAll();
        http.exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
    }
}
