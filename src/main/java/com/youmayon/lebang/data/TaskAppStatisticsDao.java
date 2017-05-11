package com.youmayon.lebang.data;

import com.youmayon.lebang.domain.TaskAppStatistics;

import java.util.List;

/**
 * Created by Jawinton on 17/05/11.
 */
public interface TaskAppStatisticsDao {
    List<TaskAppStatistics> findByTaskIdAndAppIdAndBeginTimeGreaterThanAndEndTimeLessThanGroupByBeginTimeAndEndTime(long taskId, long appId, long beginTime, long endTime);
}
