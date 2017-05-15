package com.youmayon.lebang.data;

import com.youmayon.lebang.domain.ReviewerTaskStatistics;
import com.youmayon.lebang.domain.TaskAppStatistics;

import java.util.Collection;
import java.util.List;

/**
 * Created by Jawinton on 17/05/15.
 */
public interface ReviewerTaskStatisticsDao {
    List<ReviewerTaskStatistics> findByBeginTimeGreaterThanAndEndTimeLessThanGroupByReviewerUserIdAndBeginTimeAndEndTime(long beginTime, long endTime, int page, int size);

    List<ReviewerTaskStatistics> findByReviewerUserIdInAndBeginTimeAndEndTimeGroupByReviewerUserId(Collection<Long> reviewerUserIds, long beginTime, long endTime);
}
