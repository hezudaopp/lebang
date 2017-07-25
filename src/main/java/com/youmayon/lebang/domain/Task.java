package com.youmayon.lebang.domain;

import com.youmayon.lebang.constant.LogicConstants;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Set;

/**
 * Created by Jawinton on 17/05/02.
 * 任务
 */
@Entity
@Table(indexes = { @Index(name = "uk_name", columnList = "name", unique = true)})
public class Task {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT(20) UNSIGNED COMMENT '自增id'")
    private Long id;

    @NotNull
    @Size(min = 2, max = 100)
    @Column(columnDefinition = "VARCHAR(100) COMMENT '任务名称'")
    private String name;

    @NotNull
    @Column(columnDefinition = "BIGINT(20) UNSIGNED COMMENT '任务类型id'")
    private Long taskTypeId;

    @Size(min = 2, max = 100)
    @Column(columnDefinition = "VARCHAR(100) NOT NULL COMMENT '任务类型名称'")
    private String taskTypeName;

    @Column(columnDefinition = "DECIMAL(6,2) UNSIGNED DEFAULT NULL COMMENT '任务成本'")
    private Double cost;

    @NotNull
    @Column(columnDefinition = "DECIMAL(6,2) UNSIGNED COMMENT '任务单价'")
    private Double price;

    @NotNull
    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '任务数量'")
    private Long amount;

    @Column(columnDefinition = "INT(10) UNSIGNED NOT NULL COMMENT '任务余量'")
    private Long leftAmount;

    @Column(columnDefinition = "INT(10) UNSIGNED NOT NULL COMMENT '任务完成量'")
    private Long completedAmount;

    @Column(columnDefinition = "INT(10) UNSIGNED NOT NULL COMMENT '任务拒绝量'")
    private Long rejectedAmount;

    @Column(columnDefinition = "INT(10) UNSIGNED NOT NULL COMMENT '任务通过量'")
    private Long acceptedAmount;

    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '审核周期'")
    private Integer reviewPeriod;

    @NotNull
    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '任务开始时间'")
    private Long beginTime;

    @NotNull
    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '任务结束时间'")
    private Long endTime;

    @NotNull
    @Max(LogicConstants.ALL_DEVICE_TYPES_MASK)
    @Column(columnDefinition = "TINYINT(3) UNSIGNED COMMENT '设备类型掩码'")
    private Integer deviceTypeMask;

    @NotNull
    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '每人限制次数'")
    private Long eachPersonLimit;

    @NotNull
    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '每次完成限制周期'")
    private Long recycleDaysLimit;

    @Size(max = 20)
    @Column(columnDefinition = "VARCHAR(20) DEFAULT NULL COMMENT '推荐人'")
    private String recommendedPerson;

    @NotNull
    @Column(columnDefinition = "TINYINT(1) UNSIGNED DEFAULT TRUE COMMENT '是否启用'")
    private Boolean enabled;

    @NotNull
    @Column(columnDefinition = "TINYINT(1) UNSIGNED COMMENT '是否限定城市'")
    private Boolean cityLimited;

    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '创建时间'")
    private Long createdTime;

    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '修改时间'")
    private Long modifiedTime;

    @Transient
    private Collection<TaskProcedure> taskProcedures;

    @Transient
    private Collection<TaskCity> taskCities;

    public Task() {}

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

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getLeftAmount() {
        return leftAmount;
    }

    public void setLeftAmount(Long leftAmount) {
        this.leftAmount = leftAmount;
    }

    public Long getCompletedAmount() {
        return completedAmount;
    }

    public void setCompletedAmount(Long completedAmount) {
        this.completedAmount = completedAmount;
    }

    public Long getRejectedAmount() {
        return rejectedAmount;
    }

    public void setRejectedAmount(Long rejectedAmount) {
        this.rejectedAmount = rejectedAmount;
    }

    public Long getAcceptedAmount() {
        return acceptedAmount;
    }

    public void setAcceptedAmount(Long acceptedAmount) {
        this.acceptedAmount = acceptedAmount;
    }

    public Integer getReviewPeriod() {
        return reviewPeriod;
    }

    public void setReviewPeriod(Integer reviewPeriod) {
        this.reviewPeriod = reviewPeriod;
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

    public Integer getDeviceTypeMask() {
        return deviceTypeMask;
    }

    public void setDeviceTypeMask(Integer deviceTypeMask) {
        this.deviceTypeMask = deviceTypeMask;
    }

    public Long getEachPersonLimit() {
        return eachPersonLimit;
    }

    public void setEachPersonLimit(Long eachPersonLimit) {
        this.eachPersonLimit = eachPersonLimit;
    }

    public Long getRecycleDaysLimit() {
        return recycleDaysLimit;
    }

    public void setRecycleDaysLimit(Long recycleDaysLimit) {
        this.recycleDaysLimit = recycleDaysLimit;
    }

    public String getRecommendedPerson() {
        return recommendedPerson;
    }

    public void setRecommendedPerson(String recommendedPerson) {
        this.recommendedPerson = recommendedPerson;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getCityLimited() {
        return cityLimited;
    }

    public void setCityLimited(Boolean cityLimited) {
        this.cityLimited = cityLimited;
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

    public Collection<TaskProcedure> getTaskProcedures() {
        return taskProcedures;
    }

    public void setTaskProcedures(Collection<TaskProcedure> taskProcedures) {
        this.taskProcedures = taskProcedures;
    }

    public Collection<TaskCity> getTaskCities() {
        return taskCities;
    }

    public void setTaskCities(Collection<TaskCity> taskCities) {
        this.taskCities = taskCities;
    }
}
