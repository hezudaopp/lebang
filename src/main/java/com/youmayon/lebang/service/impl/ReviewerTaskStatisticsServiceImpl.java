package com.youmayon.lebang.service.impl;

import com.youmayon.lebang.data.ReviewerTaskStatisticsRepository;
import com.youmayon.lebang.domain.ReviewerTaskStatistics;
import com.youmayon.lebang.service.ReviewerTaskStatisticsService;
import com.youmayon.lebang.service.UserTaskService;
import com.youmayon.lebang.util.ReflectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jawinton on 17/05/03.
 */
@Service
public class ReviewerTaskStatisticsServiceImpl implements ReviewerTaskStatisticsService {
    @Autowired
    UserTaskService userTaskService;

    @Autowired
    ReviewerTaskStatisticsRepository reviewerTaskStatisticsRepository;

    @Override
    public List<ReviewerTaskStatistics> generateReviewerTaskStatistics(long beginTime, long endTime) throws IllegalAccessException {
        List<ReviewerTaskStatistics> reviewerTaskStatisticsList = userTaskService.reviewedAmountOfReviewer(beginTime, endTime);
        reviewerTaskStatisticsList.addAll(userTaskService.acceptedAmountAndTotalFlowOfReviewer(beginTime, endTime));
        Map<ReviewerTaskStatistics, ReviewerTaskStatistics> reviewerTaskStatisticsSet = new HashMap<>();
        for (ReviewerTaskStatistics reviewerTaskStatistics : reviewerTaskStatisticsList) {
            if (reviewerTaskStatisticsSet.containsKey(reviewerTaskStatistics)) {
                ReviewerTaskStatistics existTaskAppStatistics = reviewerTaskStatisticsSet.get(reviewerTaskStatistics);
                ReflectUtil.mergeObject(existTaskAppStatistics, reviewerTaskStatistics);
            }

            if (reviewerTaskStatistics.getId() == null) {
                ReviewerTaskStatistics savedReviewerTaskStatistics = reviewerTaskStatisticsRepository.findByReviewerUserIdAndBeginTimeAndEndTime(reviewerTaskStatistics.getReviewerUserId(), beginTime, endTime);
                if (savedReviewerTaskStatistics != null) {
                    reviewerTaskStatistics.setId(savedReviewerTaskStatistics.getId());
                }
            }

            reviewerTaskStatisticsSet.put(reviewerTaskStatistics, reviewerTaskStatistics);
        }
        return reviewerTaskStatisticsRepository.save(reviewerTaskStatisticsSet.values());
    }

    @Override
    public List<ReviewerTaskStatistics> listGroupByReviewerUserId(Collection<Long> reviewerUserIds, long beginTime, long endTime) {
        return reviewerTaskStatisticsRepository.findByReviewerUserIdInAndBeginTimeAndEndTimeGroupByReviewerUserId(reviewerUserIds, beginTime, endTime);
    }

    @Override
    public List<ReviewerTaskStatistics> listGroupByReviewerUserId(long beginTime, long endTime, int page, int size) {
        return reviewerTaskStatisticsRepository.findByBeginTimeGreaterThanAndEndTimeLessThanGroupByReviewerUserIdAndBeginTimeAndEndTime(beginTime, endTime, page, size);
    }

    @Override
    public List<ReviewerTaskStatistics> list(long beginTime, long endTime) {
        return reviewerTaskStatisticsRepository.findByBeginTimeGreaterThanAndEndTimeLessThanGroupByBeginTimeAndEndTime(beginTime, endTime);
    }
}
