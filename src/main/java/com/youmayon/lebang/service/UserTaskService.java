package com.youmayon.lebang.service;

import com.youmayon.lebang.domain.*;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Set;

/**
 * 任务service类
 * Created by Jawinton on 17/05/03.
 */
public interface UserTaskService {
    /**
     * 今天审核通过的任务数量
     * @return
     */
    long todayAcceptedAmount();

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
    UserTask reviewTask(User user, UserTask userTask, Task task, int toStatus, String remark);


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
    UserTask findOne(long appId, String appUserId, long taskId);

    /**
     * 审核人员的用户任务列表
     * @param reviewerUserId
     * @param statusSet
     * @param taskName
     * @param taskTypeName
     * @param appName
     * @param page
     * @param size
     * @return
     */
    Page<UserTask> list(long reviewerUserId, Set<Integer> statusSet, String taskName, String taskTypeName, String appName, int page, int size);

    /**
     * 渠道用户的用户任务列表
     * @param appName
     * @param appUserId
     * @param statusSet
     * @param page
     * @param size
     * @return
     */
    Page<UserTask> list(String appName, String appUserId, Set<Integer> statusSet, int page, int size);

    /**
     * 用户已领取的任务数量
     * @param appId
     * @param appUserId
     * @param taskId
     * @return
     */
    int count(long appId, String appUserId, long taskId);

    /**
     * 用户已领取的任务数量
     * @param appName
     * @param appUserId
     * @param taskId
     * @return
     */
    int count(String appName, String appUserId, long taskId);

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

    /**
     * 根据审核人员统计审核通过任务数量
     * @param beginTime
     * @param endTime
     * @return
     */
    List<ReviewerTaskStatistics> acceptedAmountAndTotalFlowOfReviewer(long beginTime, long endTime);

    /**
     * 根据审核人员统计已经审核的任务数量
     * @param beginTime
     * @param endTime
     * @return
     */
    List<ReviewerTaskStatistics> reviewedAmountOfReviewer(long beginTime, long endTime);
}
