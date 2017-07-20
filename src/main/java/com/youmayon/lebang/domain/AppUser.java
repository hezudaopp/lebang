package com.youmayon.lebang.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Jawinton on 17/05/02.
 * 渠道用户表
 */
@Entity
@Table(indexes = { @Index(name = "uk_app_user_id", columnList = "appUserId", unique = true)})
public class AppUser {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT(20) UNSIGNED COMMENT '自增id'")
    private Long id;

    @NotNull
    @Size(min = 2, max = 32)
    @Column(columnDefinition = "VARCHAR(32) COMMENT 'app user_id'")
    private String appUserId;

    @NotNull
    @Size(min = 5, max = 50)
    @Column(columnDefinition = "VARCHAR(50) COMMENT '最近一次更新的设备id'")
    private String deviceId;

    @Column(columnDefinition = "VARCHAR(500) COMMENT '额外信息'")
    private String extraInfo;

    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '创建时间'")
    private Long createdTime;

    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '修改时间'")
    private Long modifiedTime;

    public AppUser() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(String appUserId) {
        this.appUserId = appUserId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
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
