package com.youmayon.lebang.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.youmayon.lebang.constant.LogicConstants;
import com.youmayon.lebang.enums.UserStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Jawinton on 17/05/02.
 * 用户，使用username登录到系统
 */
@Entity
public class App {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT(20) UNSIGNED COMMENT '自增id'")
    private Long id;

    @NotNull
    @Column(columnDefinition = "VARCHAR(50) COMMENT 'app名称'")
    private String name;

    @Column(columnDefinition = "CHAR(" + LogicConstants.APP_SECRET_LEN + ") NOT NULL COMMENT 'app_secret'")
    private String secret;

    @Column(columnDefinition = "TINYINT(1) UNSIGNED NOT NULL COMMENT '是否启用'")
    private Boolean enabled;

    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '创建时间'")
    private Long createdTime;

    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '修改时间'")
    private Long modifiedTime;

    public App() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }

    public Long getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Long modifiedTime) {
        this.modifiedTime = modifiedTime;
    }
}
