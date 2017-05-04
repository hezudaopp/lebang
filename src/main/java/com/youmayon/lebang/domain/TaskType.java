package com.youmayon.lebang.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Jawinton on 17/05/02.
 * 任务类型
 */
@Entity
public class TaskType {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT(20) UNSIGNED COMMENT '自增id'")
    private Long id;

    @NotNull
    @Size(min = 2, max = 100)
    @Column(columnDefinition = "VARCHAR(100) COMMENT '任务类型名称'")
    private String name;

    @NotNull
    @Column(columnDefinition = "TINYINT(1) UNSIGNED DEFAULT TRUE COMMENT '是否启用'")
    private Boolean enabled;

    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '创建时间'")
    private Long createdTime;

    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '修改时间'")
    private Long modifiedTime;

    public TaskType() {}

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
