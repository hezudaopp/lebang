package com.youmayon.lebang.domain;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Jawinton on 17/05/02.
 * 任务分派城市
 */
@Entity
@Table(indexes = { @Index(name = "uk_task_city", columnList = "taskId,cityId", unique = true)})
public class TaskCity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT(20) UNSIGNED COMMENT '自增id'")
    private Long id;

    @NotNull
    @Column(columnDefinition = "BIGINT(20) UNSIGNED COMMENT '任务id'")
    private Long taskId;

    @NotNull
    @Column(columnDefinition = "BIGINT(20) UNSIGNED COMMENT '省份id'")
    private Long provinceId;

    @NotNull
    @Column(columnDefinition = "BIGINT(20) UNSIGNED COMMENT '城市id'")
    private Long cityId;

    @NotNull
    @Column(columnDefinition = "TINYINT(1) UNSIGNED COMMENT '是否启用'")
    private Boolean enabled;

    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '创建时间'")
    private Long createdTime;

    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '修改时间'")
    private Long modifiedTime;

    @Transient
    private Task task;

    public TaskCity() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
