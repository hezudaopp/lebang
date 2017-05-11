package com.youmayon.lebang.service.impl;

import com.youmayon.lebang.data.TaskAppStatisticsRepository;
import com.youmayon.lebang.domain.TaskAppStatistics;
import com.youmayon.lebang.service.TaskAppStatisticsService;
import com.youmayon.lebang.service.UserTaskService;
import com.youmayon.lebang.util.ReflectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jawinton on 17/05/03.
 */
@Service
public class TaskAppStatisticsServiceImpl implements TaskAppStatisticsService {
    @Autowired
    TaskAppStatisticsRepository taskAppStatisticsRepository;

    @Autowired
    UserTaskService userTaskService;

    @Override
    public List<TaskAppStatistics> list(long beginTime, long endTime, boolean isDistinctTask, boolean isDistinctApp) {
        return listTaskAppStatistics(0, 0, beginTime, endTime, isDistinctTask, isDistinctApp);
    }

    @Override
    public List<TaskAppStatistics> listAppStatistics(long appId, long beginTime, long endTime, boolean isDistinctTask, boolean isDistinctApp) {
        return listTaskAppStatistics(0, appId, beginTime, endTime, isDistinctTask, isDistinctApp);
    }

    @Override
    public List<TaskAppStatistics> listTaskStatistics(long taskId, long beginTime, long endTime, boolean isDistinctTask, boolean isDistinctApp) {
        return listTaskAppStatistics(taskId, 0, beginTime, endTime, isDistinctTask, isDistinctApp);
    }

    @Override
    public List<TaskAppStatistics> listTaskAppStatistics(long taskId, long appId, long beginTime, long endTime, boolean isDistinctTask, boolean isDistinctApp) {
        List<TaskAppStatistics> taskAppStatisticsList = taskAppStatisticsRepository.findByTaskIdAndAppIdAndBeginTimeGreaterThanAndEndTimeLessThanGroupByBeginTimeAndEndTime(taskId, appId, beginTime, endTime, isDistinctTask, isDistinctApp);

        if (!isDistinctApp) {
            for (TaskAppStatistics taskAppStatistics : taskAppStatisticsList) {
                taskAppStatistics.setAppId(null);
            }
        }

        if (!isDistinctTask) {
            for (TaskAppStatistics taskAppStatistics : taskAppStatisticsList) {
                taskAppStatistics.setTaskId(null);
            }
        }

        return taskAppStatisticsList;
    }

    @Override
    public List<TaskAppStatistics> generateTaskAppStatistics(long beginTime, long endTime) throws IllegalAccessException {
        List<TaskAppStatistics> taskAppStatisticsList = userTaskService.receivedAmountOfTaskIdAndAppId(beginTime, endTime);
        taskAppStatisticsList.addAll(userTaskService.completedAmountOfTaskIdAndAppId(beginTime, endTime));
        taskAppStatisticsList.addAll(userTaskService.acceptedAmountAndTotalFlowOfTaskIdAndAppId(beginTime, endTime));
        Map<TaskAppStatistics, TaskAppStatistics> taskAppStatisticsSet = new HashMap<>();
        for (TaskAppStatistics taskAppStatistics : taskAppStatisticsList) {
            if (taskAppStatisticsSet.containsKey(taskAppStatistics)) {
                TaskAppStatistics existTaskAppStatistics = taskAppStatisticsSet.get(taskAppStatistics);
                ReflectUtil.mergeObject(existTaskAppStatistics, taskAppStatistics);
            }

            if (taskAppStatistics.getId() == null) {
                TaskAppStatistics savedTaskAppStatistics = taskAppStatisticsRepository.findFirstByTaskIdAndAppIdAndBeginTimeAndEndTime(taskAppStatistics.getTaskId(), taskAppStatistics.getAppId(), beginTime, endTime);
                if (savedTaskAppStatistics != null) {
                    taskAppStatistics.setId(savedTaskAppStatistics.getId());
                }
            }

            taskAppStatisticsSet.put(taskAppStatistics, taskAppStatistics);
        }
        return taskAppStatisticsRepository.save(taskAppStatisticsSet.values());
    }
}
