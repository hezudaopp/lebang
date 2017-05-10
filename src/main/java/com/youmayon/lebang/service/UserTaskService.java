package com.youmayon.lebang.service;

import com.youmayon.lebang.domain.Task;
import com.youmayon.lebang.domain.TaskAppStatistics;
import com.youmayon.lebang.domain.User;
import com.youmayon.lebang.domain.UserTask;

import java.util.List;

/**
 * 任务service类
 * Created by Jawinton on 17/05/03.
 */
public interface UserTaskService {
    /**
     * 用户领取任务
     * @param userTask
     * @return
     */
    UserTask receiveTask(UserTask userTask, Task task);

    /**
     * 用户完成任务
     * @param userTask
     * @param task
     * @return
     */
    UserTask completeTask(UserTask userTask, Task task, int fromStatus);

    /**
     * 审核任务
     * @param user
     * @param userTask
     * @param task
     * @param toStatus
     * @return
     */
    UserTask reviewTask(User user, UserTask userTask, Task task, int toStatus);


    /**
     * 用户任务详情
     * @param id
     * @return
     */
    UserTask findOne(long id);

    /**
     * 用户最近一次领取的任务详情
     * @param appId
     * @param appUserId
     * @param taskId
     * @return
     */
    UserTask findOne(String appId, String appUserId, long taskId);

    /**
     * 用户已领取的任务数量
     * @param appId
     * @param appUserId
     * @param taskId
     * @return
     */
    int count(String appId, String appUserId, long taskId);

    /**
     * 根据任务和APP渠道统计领取任务数据
     * @param beginTime
     * @param endTime
     * @return
     */
    List<TaskAppStatistics> receivedAmountOfTaskIdAndAppId(long beginTime, long endTime);

    /**
     * 根据任务和APP渠道统计完成任务数据
     * @param beginTime
     * @param endTime
     * @return
     */
    List<TaskAppStatistics> completedAmountOfTaskIdAndAppId(long beginTime, long endTime);

    /**
     * 根据任务和APP渠道统计审核通过任务和流水数据
     * @param beginTime
     * @param endTime
     * @return
     */
    List<TaskAppStatistics> acceptedAmountAndTotalFlowOfTaskIdAndAppId(long beginTime, long endTime);

}
