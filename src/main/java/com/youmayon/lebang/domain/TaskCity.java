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
@Table(indexes = { @Index(name = "uk_task_city", columnList = "taskId, cityId", unique = true)})
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

    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '创建时间'")
    private Long createdTime;

    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '修改时间'")
    private Long modifiedTime;

    public TaskCity() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaskCity taskCity = (TaskCity) o;

        if (taskId != null ? !taskId.equals(taskCity.taskId) : taskCity.taskId != null) return false;
        return cityId != null ? cityId.equals(taskCity.cityId) : taskCity.cityId == null;

    }

    @Override
    public int hashCode() {
        int result = taskId != null ? taskId.hashCode() : 0;
        result = 31 * result + (cityId != null ? cityId.hashCode() : 0);
        return result;
    }

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
