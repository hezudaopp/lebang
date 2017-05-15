package com.youmayon.lebang.service;

import com.youmayon.lebang.domain.ReviewerTaskStatistics;
import com.youmayon.lebang.domain.TaskAppStatistics;

import java.util.List;

/**
 * 任务APP渠道统计service类
 * Created by Jawinton on 17/05/03.
 */
public interface TaskAppStatisticsService {
    /**
     * 统计列表
     * @param beginTime
     * @param endTime
     * @param isDistinctTask
     * @param isDistinctApp
     * @return
     */
    List<TaskAppStatistics> list(long beginTime, long endTime, boolean isDistinctTask, boolean isDistinctApp);

    /**
     * APP渠道统计列表
     * @param appId
     * @param beginTime
     * @param endTime
     * @param isDistinctTask
     * @param isDistinctApp
     * @return
     */
    List<TaskAppStatistics> listAppStatistics(long appId, long beginTime, long endTime, boolean isDistinctTask, boolean isDistinctApp);

    /**
     * 任务统计列表
     * @param taskId
     * @param beginTime
     * @param endTime
     * @param isDistinctTask
     * @param isDistinctApp
     * @return
     */
    List<TaskAppStatistics> listTaskStatistics(long taskId, long beginTime, long endTime, boolean isDistinctTask, boolean isDistinctApp);

    /**
     * 任务APP渠道统计列表
     * @param taskId
     * @param appId
     * @param beginTime
     * @param endTime
     * @param isDistinctTask
     * @param isDistinctApp
     * @return
     */
    List<TaskAppStatistics> listTaskAppStatistics(long taskId, long appId, long beginTime, long endTime, boolean isDistinctTask, boolean isDistinctApp);

    /**
     * 生成任务APP渠道报表
     * @param beginTime
     * @param endTime
     */
    List<TaskAppStatistics> generateTaskAppStatistics(long beginTime, long endTime) throws IllegalAccessException;
}
