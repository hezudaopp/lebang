package com.youmayon.lebang.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Jawinton on 17/05/02.
 * 用户任务
 */
@Entity
public class UserTask {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT(20) UNSIGNED COMMENT '自增id'")
    private Long id;

    @Column(columnDefinition = "BIGINT(20) UNSIGNED NOT NULL COMMENT '用户来源渠道app'")
    private Long appId;

    @Column(columnDefinition = "VARCHAR(50) COMMENT '用户来源渠道app名称'")
    private String appName;

    @Column(columnDefinition = "VARCHAR(50) COMMENT '领取任务所用的设备id'")
    private String deviceId;

    @NotNull
    @Size(min = 2, max = 32)
    @Column(columnDefinition = "VARCHAR(32) COMMENT '用户在来源app中user_id'")
    private String appUserId;

    @NotNull
    @Column(columnDefinition = "BIGINT(20) UNSIGNED COMMENT '任务id'")
    private Long taskId;

    @Column(columnDefinition = "VARCHAR(100) NOT NULL COMMENT '任务名称'")
    private String taskName;

    @Column(columnDefinition = "DECIMAL(6,2) UNSIGNED NOT NULL COMMENT '任务单价'")
    private Double price;

    @Column(columnDefinition = "BIGINT(20) UNSIGNED NOT NULL COMMENT '任务类型id'")
    private Long taskTypeId;

    @Size(min = 2, max = 100)
    @Column(columnDefinition = "VARCHAR(100) NOT NULL COMMENT '任务类型名称'")
    private String taskTypeName;

    @Column(columnDefinition = "BIGINT(20) UNSIGNED DEFAULT NULL COMMENT '省份id'")
    private Long provinceId;

    @Column(columnDefinition = "BIGINT(20) UNSIGNED DEFAULT NULL COMMENT '城市id'")
    private Long cityId;

    @Column(columnDefinition = "VARCHAR(200) DEFAULT NULL COMMENT '用户完成任务留言'")
    private String note;

    @Column(columnDefinition = "VARCHAR(1000) DEFAULT NULL COMMENT '用户完成任务图片'")
    private String images;

    @Column(columnDefinition = "TINYINT(2) UNSIGNED NOT NULL COMMENT '任务进度'")
    private Integer status;

    @Column(columnDefinition = "INT(10) UNSIGNED NOT NULL COMMENT '任务结束时间'")
    private Long taskEndTime;

    @Column(columnDefinition = "BIGINT(20) UNSIGNED DEFAULT NULL COMMENT '审核用户id'")
    private Long reviewerUserId;

    @Column(columnDefinition = "VARCHAR(20) COMMENT '审核人员用户名'")
    private String reviewerUsername;

    @Column(columnDefinition = "INT(10) UNSIGNED DEFAULT NULL COMMENT '审核截至时间'")
    private Long reviewEndTime;

    @Column(columnDefinition = "INT(10) UNSIGNED DEFAULT NULL COMMENT '任务完成时间'")
    private Long completedTime;

    @Column(columnDefinition = "INT(10) UNSIGNED DEFAULT NULL COMMENT '任务审核时间'")
    private Long reviewedTime;

    @Column(columnDefinition = "VARCHAR(256) DEFAULT NULL COMMENT '审核意见'")
    private String reviewNote;

    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '创建时间'")
    private Long createdTime;

    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '修改时间'")
    private Long modifiedTime;

    public UserTask() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
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

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getTaskTypeId() {
        return taskTypeId;
    }

    public void setTaskTypeId(Long taskTypeId) {
        this.taskTypeId = taskTypeId;
    }

    public String getTaskTypeName() {
        return taskTypeName;
    }

    public void setTaskTypeName(String taskTypeName) {
        this.taskTypeName = taskTypeName;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getTaskEndTime() {
        return taskEndTime;
    }

    public void setTaskEndTime(Long taskEndTime) {
        this.taskEndTime = taskEndTime;
    }

    public Long getReviewerUserId() {
        return reviewerUserId;
    }

    public void setReviewerUserId(Long reviewerUserId) {
        this.reviewerUserId = reviewerUserId;
    }

    public String getReviewerUsername() {
        return reviewerUsername;
    }

    public void setReviewerUsername(String reviewerUsername) {
        this.reviewerUsername = reviewerUsername;
    }

    public Long getReviewEndTime() {
        return reviewEndTime;
    }

    public void setReviewEndTime(Long reviewEndTime) {
        this.reviewEndTime = reviewEndTime;
    }

    public String getReviewNote() {
        return reviewNote;
    }

    public void setReviewNote(String reviewNote) {
        this.reviewNote = reviewNote;
    }

    public Long getCompletedTime() {
        return completedTime;
    }

    public void setCompletedTime(Long completedTime) {
        this.completedTime = completedTime;
    }

    public Long getReviewedTime() {
        return reviewedTime;
    }

    public void setReviewedTime(Long reviewedTime) {
        this.reviewedTime = reviewedTime;
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
