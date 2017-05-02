package com.youmayon.lebang.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Jawinton on 17/05/02.
 * 用户任务操作日志
 */
@Entity
public class UserTaskLog {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT(20) UNSIGNED COMMENT '自增id'")
    private Long id;

    @NotNull
    @Column(columnDefinition = "BIGINT(20) UNSIGNED COMMENT '用户任务id'")
    private Long userTaskId;

    @NotNull
    @Column(columnDefinition = "TINYINT(2) UNSIGNED COMMENT '操作类型'")
    private Integer operateType;

    @NotNull
    @Column(columnDefinition = "BIGINT(20) UNSIGNED COMMENT '操作用户'")
    private Long operatorUserId;

    @NotNull
    @Column(columnDefinition = "TINYINT(2) UNSIGNED COMMENT '操作前任务进度'")
    private Integer fromStatus;

    @NotNull
    @Column(columnDefinition = "TINYINT(2) UNSIGNED COMMENT '操作后任务进度'")
    private Integer toStatus;

    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '创建时间'")
    private Long createdTime;

    public UserTaskLog() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserTaskId() {
        return userTaskId;
    }

    public void setUserTaskId(Long userTaskId) {
        this.userTaskId = userTaskId;
    }

    public Integer getOperateType() {
        return operateType;
    }

    public void setOperateType(Integer operateType) {
        this.operateType = operateType;
    }

    public Long getOperatorUserId() {
        return operatorUserId;
    }

    public void setOperatorUserId(Long operatorUserId) {
        this.operatorUserId = operatorUserId;
    }

    public Integer getFromStatus() {
        return fromStatus;
    }

    public void setFromStatus(Integer fromStatus) {
        this.fromStatus = fromStatus;
    }

    public Integer getToStatus() {
        return toStatus;
    }

    public void setToStatus(Integer toStatus) {
        this.toStatus = toStatus;
    }

    public Long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }
}
