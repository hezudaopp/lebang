package com.youmayon.lebang.data;

import com.youmayon.lebang.domain.ReviewerTaskStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Jawinton on 17/05/03.
 */
@Repository
public interface ReviewerTaskStatisticsRepository extends JpaRepository<ReviewerTaskStatistics, Long>, ReviewerTaskStatisticsDao {
    ReviewerTaskStatistics findByReviewerUserIdAndBeginTimeAndEndTime(long reviewerUserId, long beginTime, long endTime);
}
