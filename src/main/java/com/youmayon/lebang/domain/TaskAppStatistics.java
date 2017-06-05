package com.youmayon.lebang.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Jawinton on 17/05/10.
 * 任务渠道统计
 */
@Entity
@Table(indexes = { @Index(name = "uk_task_app_begin_end", columnList = "taskId, appId, beginTime, endTime", unique = true)})
public class TaskAppStatistics {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT(20) UNSIGNED COMMENT '自增id'")
    private Long id;

    @NotNull
    @Column(columnDefinition = "BIGINT(20) UNSIGNED COMMENT '任务id'")
    private Long taskId;

    @Column(columnDefinition = "VARCHAR(100) COMMENT '任务名称'")
    private String taskName;

    @NotNull
    @Column(columnDefinition = "BIGINT(20) UNSIGNED COMMENT 'app渠道id'")
    private Long appId;

    @Column(columnDefinition = "VARCHAR(50) COMMENT '用户来源渠道app名称'")
    private String appName;

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

    public TaskAppStatistics(Long taskId, String taskName, Long appId, String appName, Long beginTime, Long endTime, Double totalFlow, Long receivedAmount, Long completedAmount, Long acceptedAmount) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.appId = appId;
        this.appName = appName;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.totalFlow = totalFlow;
        this.receivedAmount = receivedAmount;
        this.completedAmount = completedAmount;
        this.acceptedAmount = acceptedAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaskAppStatistics that = (TaskAppStatistics) o;

        if (taskId != null ? !taskId.equals(that.taskId) : that.taskId != null) return false;
        if (appId != null ? !appId.equals(that.appId) : that.appId != null) return false;
        if (beginTime != null ? !beginTime.equals(that.beginTime) : that.beginTime != null) return false;
        return endTime != null ? endTime.equals(that.endTime) : that.endTime == null;

    }

    @Override
    public int hashCode() {
        int result = taskId != null ? taskId.hashCode() : 0;
        result = 31 * result + (appId != null ? appId.hashCode() : 0);
        result = 31 * result + (beginTime != null ? beginTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
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

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
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

    public void setNullDigitalToZero() {
        if (this.getAcceptedAmount() == null) {
            this.setAcceptedAmount(0L);
        }
        if (this.getCompletedAmount() == null) {
            this.setCompletedAmount(0L);
        }
        if (this.getReceivedAmount() == null) {
            this.setReceivedAmount(0L);
        }
        if (this.getTotalFlow() == null) {
            this.setTotalFlow(0D);
        }
    }
}
