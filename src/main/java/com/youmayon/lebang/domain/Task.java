package com.youmayon.lebang.domain;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;

/**
 * Created by Jawinton on 17/05/02.
 * 任务
 */
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT(20) UNSIGNED COMMENT '自增id'")
    private Long id;

    @NotNull
    @Column(columnDefinition = "BIGINT(20) UNSIGNED COMMENT '任务类型id'")
    private Long taskTypeId;

    @Size(min = 3, max = 100)
    @Column(columnDefinition = "VARCHAR(100) NOT NULL COMMENT '任务类型名称'")
    private String taskTypeName;

    @NotNull
    @Column(columnDefinition = "DECIMAL(6,2) UNSIGNED COMMENT '任务成本'")
    private Double cost;

    @NotNull
    @Column(columnDefinition = "DECIMAL(6,2) UNSIGNED COMMENT '任务单价'")
    private Double price;

    @NotNull
    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '任务数量'")
    private Long amount;

    @Column(columnDefinition = "INT(10) UNSIGNED NOT NULL COMMENT '任务余量'")
    private Long leftAmount;

    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '审核周期'")
    private Long reviewPeriod;

    @NotNull
    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '任务开始时间'")
    private Long beginTime;

    @NotNull
    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '任务结束时间'")
    private Long endTime;

    @NotNull
    @Max(127)
    @Column(columnDefinition = "TINYINT(3) UNSIGNED COMMENT '设备类型掩码'")
    private Double deviceTypeMask;

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
    @Min(1)
    @Max(5)
    @Column(columnDefinition = "TINYINT(1) UNSIGNED COMMENT '任务步骤数'")
    private Integer procedureNum;

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

    public Task() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getReviewPeriod() {
        return reviewPeriod;
    }

    public void setReviewPeriod(Long reviewPeriod) {
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

    public Double getDeviceTypeMask() {
        return deviceTypeMask;
    }

    public void setDeviceTypeMask(Double deviceTypeMask) {
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

    public Integer getProcedureNum() {
        return procedureNum;
    }

    public void setProcedureNum(Integer procedureNum) {
        this.procedureNum = procedureNum;
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
}
