package com.youmayon.lebang.domain;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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

    @NotNull
    @Column(columnDefinition = "BIGINT(20) UNSIGNED COMMENT '领取任务的用户id'")
    private Long userId;

    @Column(columnDefinition = "INT(10) UNSIGNED DEFAULT NULL COMMENT '用户来源app'")
    private Long appId;

    @Size(min = 2, max = 32)
    @Column(columnDefinition = "VARCHAR(32) DEFAULT NULL COMMENT '用户在来源app中user_id'")
    private String appUserId;

    @NotNull
    @Column(columnDefinition = "BIGINT(20) UNSIGNED COMMENT '任务id'")
    private Long taskId;

    @NotNull
    @Column(columnDefinition = "BIGINT(20) UNSIGNED COMMENT '省份id'")
    private Long provinceId;

    @NotNull
    @Column(columnDefinition = "BIGINT(20) UNSIGNED COMMENT '城市id'")
    private Long cityId;

    @Column(columnDefinition = "VARCHAR(200) DEFAULT NULL COMMENT '用户完成任务留言'")
    private String note;

    @Column(columnDefinition = "VARCHAR(1000) DEFAULT NULL COMMENT '用户完成任务图片'")
    private String images;

    @NotNull
    @Column(columnDefinition = "TINYINT(2) UNSIGNED COMMENT '任务进度'")
    private Integer status;

    @NotNull
    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '任务结束时间'")
    private Long taskEndTime;

    @Column(columnDefinition = "BIGINT(20) UNSIGNED DEFAULT NULL COMMENT '审核用户id'")
    private Long reviewerUserId;

    @Column(columnDefinition = "INT(10) UNSIGNED DEFAULT NULL COMMENT '审核截至时间'")
    private Long reviewEndTime;

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
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

    public Long getReviewEndTime() {
        return reviewEndTime;
    }

    public void setReviewEndTime(Long reviewEndTime) {
        this.reviewEndTime = reviewEndTime;
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
