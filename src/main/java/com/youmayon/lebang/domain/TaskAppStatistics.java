package com.youmayon.lebang.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Jawinton on 17/05/10.
 * 任务渠道统计
 */
@Entity
public class TaskAppStatistics {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT(20) UNSIGNED COMMENT '自增id'")
    private Long id;

    @NotNull
    @Column(columnDefinition = "BIGINT(20) UNSIGNED COMMENT '任务id'")
    private Long taskId;

    @NotNull
    @Column(columnDefinition = "BIGINT(20) UNSIGNED COMMENT '渠道app id'")
    private Long appId;

    @NotNull
    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '统计开始时间'")
    private Long beginTime;

    @NotNull
    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '统计结束时间'")
    private Long endTime;

    @Column(columnDefinition = "DECIMAL(11,2) UNSIGNED NOT NULL COMMENT '任务渠道流水'")
    private Double totalFlow;

    @Column(columnDefinition = "INT(10) UNSIGNED NOT NULL COMMENT '任务渠道领取量'")
    private Long receivedAmount;

    @Column(columnDefinition = "INT(10) UNSIGNED NOT NULL COMMENT '任务渠道完成量'")
    private Long completedAmount;

    @Column(columnDefinition = "INT(10) UNSIGNED NOT NULL COMMENT '任务渠道通过量'")
    private Long acceptedAmount;

    public TaskAppStatistics() {}

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

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public Long getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Long beginTime) {
        this.beginTime = beginTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Double getTotalFlow() {
        return totalFlow;
    }

    public void setTotalFlow(Double totalFlow) {
        this.totalFlow = totalFlow;
    }

    public Long getReceivedAmount() {
        return receivedAmount;
    }

    public void setReceivedAmount(Long receivedAmount) {
        this.receivedAmount = receivedAmount;
    }

    public Long getCompletedAmount() {
        return completedAmount;
    }

    public void setCompletedAmount(Long completedAmount) {
        this.completedAmount = completedAmount;
    }

    public Long getAcceptedAmount() {
        return acceptedAmount;
    }

    public void setAcceptedAmount(Long acceptedAmount) {
        this.acceptedAmount = acceptedAmount;
    }
}
