package com.youmayon.lebang.domain;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by Jawinton on 2017/06/06.
 */
@Entity
@Table(indexes = { @Index(name = "uk_client_id", columnList = "clientId", unique = true)})
public class OauthClientDetails {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT(20) UNSIGNED COMMENT '自增id'")
    private Long id;

    @Pattern(regexp = "^[A-Za-z0-9]*$")
    @Size(min = 2, max = 64)
    @Column(columnDefinition = "VARCHAR(64) COMMENT '客户端id，只能包含字母和数字'")
    private String clientId;

    @Column(columnDefinition = "VARCHAR(255) COMMENT '资源id'")
    private String resourceIds;

    @Column(columnDefinition = "VARCHAR(255) COMMENT '密码'")
    private String clientSecret;

    @Column(columnDefinition = "VARCHAR(255) COMMENT '范围'")
    private String scope;

    @Column(columnDefinition = "VARCHAR(255) COMMENT '认证授权类型'")
    private String authorizedGrantTypes;

    @Column(columnDefinition = "VARCHAR(255) COMMENT '重定向uri'")
    private String webServerRedirectUri;

    @Column(columnDefinition = "VARCHAR(255) COMMENT '权限'")
    private String authorities;

    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT 'access token 有效期，单位为s'")
    private Integer accessTokenValidity;

    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT 'refresh token 有效期，单位为s'")
    private Integer refreshTokenValidity;

    @Column(columnDefinition = "VARCHAR(2048) COMMENT '附加信息'")
    private String additionalInformation;

    @Column(columnDefinition = "VARCHAR(255) COMMENT '???'")
    private String autoapprove;

    public OauthClientDetails() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(String resourceIds) {
        this.resourceIds = resourceIds;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getAuthorizedGrantTypes() {
        return authorizedGrantTypes;
    }

    public void setAuthorizedGrantTypes(String authorizedGrantTypes) {
        this.authorizedGrantTypes = authorizedGrantTypes;
    }

    public String getWebServerRedirectUri() {
        return webServerRedirectUri;
    }

    public void setWebServerRedirectUri(String webServerRedirectUri) {
        this.webServerRedirectUri = webServerRedirectUri;
    }

    public String getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }

    public Integer getAccessTokenValidity() {
        return accessTokenValidity;
    }

    public void setAccessTokenValidity(Integer accessTokenValidity) {
        this.accessTokenValidity = accessTokenValidity;
    }

    public Integer getRefreshTokenValidity() {
        return refreshTokenValidity;
    }

    public void setRefreshTokenValidity(Integer refreshTokenValidity) {
        this.refreshTokenValidity = refreshTokenValidity;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    public String getAutoapprove() {
        return autoapprove;
    }

    public void setAutoapprove(String autoapprove) {
        this.autoapprove = autoapprove;
    }
}
