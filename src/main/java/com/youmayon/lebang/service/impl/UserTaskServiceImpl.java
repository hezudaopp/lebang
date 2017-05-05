package com.youmayon.lebang.service.impl;

import com.youmayon.lebang.data.UserTaskRepository;
import com.youmayon.lebang.domain.Task;
import com.youmayon.lebang.domain.User;
import com.youmayon.lebang.domain.UserTask;
import com.youmayon.lebang.domain.UserTaskLog;
import com.youmayon.lebang.enums.Role;
import com.youmayon.lebang.enums.UserStatus;
import com.youmayon.lebang.enums.UserTaskStatus;
import com.youmayon.lebang.service.TaskService;
import com.youmayon.lebang.service.UserService;
import com.youmayon.lebang.service.UserTaskLogService;
import com.youmayon.lebang.service.UserTaskService;
import com.youmayon.lebang.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Created by Jawinton on 17/05/04.
 */
@Service
public class UserTaskServiceImpl implements UserTaskService {
    @Autowired
    UserTaskRepository userTaskRepository;

    @Autowired
    TaskService taskService;

    @Autowired
    UserTaskLogService userTaskLogService;

    @Autowired
    UserService userService;

    @Override
    @Transactional
    public UserTask receiveTask(UserTask userTask, Task task) {
        // check task amount limit
        if (task.getEachPersonLimit() > 0) {
            Assert.isTrue(count(userTask.getAppId(), userTask.getAppUserId(), userTask.getTaskId()) < task.getEachPersonLimit(), "您领取的任务数量已达到上限。");
        }

        // check task day cycle
        if (task.getRecycleDaysLimit() > 0) {
            UserTask latestReceivedUserTask = findOne(userTask.getAppId(), userTask.getAppUserId(), userTask.getTaskId());
            if (latestReceivedUserTask != null) {
                Assert.isTrue(TimeUtil.daysBetween(latestReceivedUserTask.getCreatedTime(), userTask.getCreatedTime()) >= task.getRecycleDaysLimit(), "您最近" + task.getRecycleDaysLimit() + "天已领取过该任务。");
            }
        }

        // decrease task left amount.
        Assert.isTrue(task.getLeftAmount() > 0, "No task any more.");
        task.setLeftAmount(task.getLeftAmount() - 1);
        taskService.save(task);

        // save user task
        UserTask savedUserTask = userTaskRepository.save(userTask);

        // save log
        UserTaskLog userTaskLog = new UserTaskLog();
        userTaskLog.setUserTaskId(savedUserTask.getId());
        userTaskLog.setOperatorAppId(userTask.getAppId());
        userTaskLog.setOperatorAppUserId(userTask.getAppUserId());
        userTaskLog.setCreatedTime(userTask.getCreatedTime());
        userTaskLog.setFromStatus(userTask.getStatus());
        userTaskLog.setToStatus(userTask.getStatus());
        userTaskLogService.save(userTaskLog);

        return savedUserTask;
    }

    @Override
    @Transactional
    public UserTask completeTask(UserTask userTask, Task task, int fromStatus) {
        // set review end time
        long reviewHours = (task.getReviewPeriod() == null || task.getReviewPeriod() == 0L) ? Integer.MAX_VALUE / 3600 : task.getReviewPeriod();
        userTask.setReviewEndTime(userTask.getCompletedTime() + reviewHours * 3600);

        // set reviewer user id.
        User reviewerUser = userService.findOneRandomUser(Role.ROLE_TASK_REVIEWER.name(), UserStatus.NORMAL.value());
        if (reviewerUser != null) {
            userTask.setReviewerUserId(reviewerUser.getId());
        }

        // save user task
        UserTask savedUserTask = userTaskRepository.save(userTask);

        // save log
        UserTaskLog userTaskLog = new UserTaskLog();
        userTaskLog.setUserTaskId(savedUserTask.getId());
        userTaskLog.setOperatorAppId(userTask.getAppId());
        userTaskLog.setOperatorAppUserId(userTask.getAppUserId());
        userTaskLog.setCreatedTime(userTask.getCompletedTime());
        userTaskLog.setFromStatus(fromStatus);
        userTaskLog.setToStatus(savedUserTask.getStatus());
        userTaskLogService.save(userTaskLog);

        return savedUserTask;
    }

    @Override
    public UserTask reviewTask(User user, UserTask userTask, int toStatus) {
        // save user task
        UserTask savedUserTask = userTaskRepository.save(userTask);

        // save log
        UserTaskLog userTaskLog = new UserTaskLog();
        userTaskLog.setUserTaskId(savedUserTask.getId());
        userTaskLog.setOperatorUserId(user.getId());
        userTaskLog.setCreatedTime(userTask.getCompletedTime());
        userTaskLog.setFromStatus(UserTaskStatus.COMPLETED.value());
        userTaskLog.setToStatus(toStatus);
        userTaskLogService.save(userTaskLog);

        return savedUserTask;
    }

    @Override
    public UserTask findOne(long id) {
        return userTaskRepository.findOne(id);
    }

    @Override
    public UserTask findOne(long appId, String appUserId, long taskId) {
        Page<UserTask> userTaskPage = userTaskRepository.findByAppIdAndAppUserIdAndTaskIdOrderByCreatedTimeDesc(appId, appUserId, taskId, new PageRequest(0, 1));
        if (!userTaskPage.hasContent()) {
            return null;
        }
        return userTaskPage.getContent().get(0);
    }

    @Override
    public int count(long appId, String appUserId, long taskId) {
        return userTaskRepository.countByAppIdAndAppUserIdAndTaskId(appId, appUserId, taskId);
    }
}
