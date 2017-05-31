package com.youmayon.lebang.service;

import com.youmayon.lebang.domain.ReviewerTaskStatistics;

import java.util.Collection;
import java.util.List;

/**
 * 审核人员统计service类
 * Created by Jawinton on 17/05/03.
 */
public interface ReviewerTaskStatisticsService {
    /**
     * 生成审核人员统计报表
     * @param beginTime
     * @param endTime
     */
    List<ReviewerTaskStatistics> generateReviewerTaskStatistics(long beginTime, long endTime) throws IllegalAccessException;

    /**
     * 统计审核人员的总审核数量
     * @param reviewerUserIds
     * @return
     */
    List<ReviewerTaskStatistics> listGroupByReviewerUserId(Collection<Long> reviewerUserIds, long beginTime, long endTime);

    /**
     * 统计审核人员的月审核数量
     * @param beginTime
     * @param endTime
     * @param page
     * @param size
     * @return
     */
    List<ReviewerTaskStatistics> listGroupByReviewerUserId(long beginTime, long endTime, int page, int size);

    /**
     * 月流水统计列表
     * @param beginTime
     * @param endTime
     * @return
     */
    List<ReviewerTaskStatistics> list(long beginTime, long endTime);
}
