package com.youmayon.lebang.domain;

import com.youmayon.lebang.enums.UserStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Collection;

/**
 * Created by Jawinton on 17/05/02.
 * 用户，使用username登录到系统
 */
@Entity
@Table(indexes = { @Index(name = "uk_username", columnList = "username", unique = true),
        @Index(name = "uk_mobile", columnList = "mobile", unique = true)})
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT(20) UNSIGNED COMMENT '自增id'")
    private Long id;

    @NotNull
    @Size(min = 3, max = 20)
    @Column(columnDefinition = "VARCHAR(20) COMMENT '用户账号（登录用）'")
    private String username;

    @Size(min = 6, max = 80)
    @Column(columnDefinition = "CHAR(80) COMMENT '用户密码（登录用）,使用Spring Security的BaseEncoder加密'")
    private String password;

    @Column(columnDefinition = "INT(10) UNSIGNED DEFAULT NULL COMMENT '用户来源app'")
    private Long appId;

    @Size(min = 2, max = 32)
    @Column(columnDefinition = "VARCHAR(32) DEFAULT NULL COMMENT '用户在来源app中user_id'")
    private String appUserId;

    @NotNull
    @Pattern(regexp = "^1[34578][0-9]{9}$")
    @Size(min = 11, max = 11)
    @Column(columnDefinition = "CHAR(11) COMMENT '手机号'")
    private String mobile;

    @Column(columnDefinition = "DECIMAL(6,2) DEFAULT NULL COMMENT '历史全部余额'")
    private Double allHistoryBalance;

    @Column(columnDefinition = "DECIMAL(6,2) DEFAULT NULL COMMENT '当前账户余额'")
    private Double balance;

    @Column(columnDefinition = "DECIMAL(6,2) DEFAULT NULL COMMENT '冻结金额'")
    private Double freezeBalance;

    @Size(min = 15, max = 15)
    @Column(columnDefinition = "CHAR(15) DEFAULT NULL COMMENT '手机IMEI号'")
    private String imei;

    @Column(columnDefinition = "INT(10) DEFAULT NULL COMMENT '上次登录时间'")
    private Long lastLoginTime;

    @NotNull
    @Column(columnDefinition = "TINYINT(2) UNSIGNED COMMENT '用户状态'")
    private Integer status;

    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '创建时间'")
    private Long createdTime;

    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '修改时间'")
    private Long modifiedTime;

    public User() {}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return status == UserStatus.NORMAL.value();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public String getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(String appUserId) {
        this.appUserId = appUserId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Double getAllHistoryBalance() {
        return allHistoryBalance;
    }

    public void setAllHistoryBalance(Double allHistoryBalance) {
        this.allHistoryBalance = allHistoryBalance;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getFreezeBalance() {
        return freezeBalance;
    }

    public void setFreezeBalance(Double freezeBalance) {
        this.freezeBalance = freezeBalance;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public Long getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Long lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
