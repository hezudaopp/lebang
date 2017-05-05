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
@Table(indexes = { @Index(name = "uk_task_procedure_order", columnList = "taskId, procedureOrder", unique = true)})
public class TaskProcedure {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT(20) UNSIGNED COMMENT '自增id'")
    private Long id;

    @NotNull
    @Column(columnDefinition = "BIGINT(20) UNSIGNED COMMENT '任务id'")
    private Long taskId;

    @NotNull
    @Max(LogicConstants.TASK_MAX_PROCEDURE_NUM)
    @Column(columnDefinition = "TINYINT(1) UNSIGNED COMMENT '顺序'")
    private Integer procedureOrder;

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

    public Integer getProcedureOrder() {
        return procedureOrder;
    }

    public void setProcedureOrder(Integer procedureOrder) {
        this.procedureOrder = procedureOrder;
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
