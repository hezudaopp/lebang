package com.youmayon.lebang.domain;

import com.youmayon.lebang.constant.LogicConstants;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

/**
 * Created by Jawinton on 17/05/02.
 * 任务步骤
 */
@Entity
public class TaskProcedure {
    @EmbeddedId
    private TaskProcedureId taskProcedureId;

    @NotNull
    @Column(columnDefinition = "VARCHAR(2000) COMMENT '描述'")
    private String description;

    @NotNull
    @Column(columnDefinition = "VARCHAR(500) COMMENT '图片'")
    private String images;

    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '创建时间'")
    private Long createdTime;

    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '修改时间'")
    private Long modifiedTime;

    public TaskProcedure() {}

    public TaskProcedureId getTaskProcedureId() {
        return taskProcedureId;
    }

    public void setTaskProcedureId(TaskProcedureId taskProcedureId) {
        this.taskProcedureId = taskProcedureId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
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
