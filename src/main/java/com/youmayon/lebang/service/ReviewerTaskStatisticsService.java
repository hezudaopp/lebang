package com.youmayon.lebang.service;

import com.youmayon.lebang.domain.ReviewerTaskStatistics;

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
}
