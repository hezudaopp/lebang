package com.youmayon.lebang.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Jawinton on 17/05/12.
 * 任务渠道统计
 */
@Entity
@Table(indexes = { @Index(name = "uk_reviewer_begin_end", columnList = "taskId, appId, beginTime, endTime", unique = true)})
public class ReviewerTaskStatistics {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT(20) UNSIGNED COMMENT '自增id'")
    private Long id;

    @NotNull
    @Column(columnDefinition = "BIGINT(20) UNSIGNED COMMENT '审核人员UserId'")
    private Long reviewerUserId;

    @NotNull
    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '统计开始时间'")
    private Long beginTime;

    @NotNull
    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '统计结束时间'")
    private Long endTime;

    @Column(columnDefinition = "INT(10) UNSIGNED NOT NULL COMMENT '任务渠道领取量'")
    private Long reviewedAmount;

    @Column(columnDefinition = "INT(10) UNSIGNED NOT NULL COMMENT '任务渠道通过量'")
    private Long acceptedAmount;

    public ReviewerTaskStatistics() {}

    public ReviewerTaskStatistics(Long reviewerUserId, Long beginTime, Long endTime, Long reviewedAmount, Long acceptedAmount) {
        this.reviewerUserId = reviewerUserId;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.reviewedAmount = reviewedAmount;
        this.acceptedAmount = acceptedAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReviewerTaskStatistics that = (ReviewerTaskStatistics) o;

        if (reviewerUserId != null ? !reviewerUserId.equals(that.reviewerUserId) : that.reviewerUserId != null)
            return false;
        if (beginTime != null ? !beginTime.equals(that.beginTime) : that.beginTime != null) return false;
        return endTime != null ? endTime.equals(that.endTime) : that.endTime == null;

    }

    @Override
    public int hashCode() {
        int result = reviewerUserId != null ? reviewerUserId.hashCode() : 0;
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

    public Long getReviewerUserId() {
        return reviewerUserId;
    }

    public void setReviewerUserId(Long reviewerUserId) {
        this.reviewerUserId = reviewerUserId;
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

    public Long getReviewedAmount() {
        return reviewedAmount;
    }

    public void setReviewedAmount(Long reviewedAmount) {
        this.reviewedAmount = reviewedAmount;
    }

    public Long getAcceptedAmount() {
        return acceptedAmount;
    }

    public void setAcceptedAmount(Long acceptedAmount) {
        this.acceptedAmount = acceptedAmount;
    }
}
