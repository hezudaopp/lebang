package com.youmayon.lebang.web;

import com.youmayon.lebang.domain.Task;
import com.youmayon.lebang.domain.UserTask;
import com.youmayon.lebang.enums.UserTaskStatus;
import com.youmayon.lebang.service.TaskCityService;
import com.youmayon.lebang.service.TaskService;
import com.youmayon.lebang.service.UserTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

/**
 * Created by Jawinton on 16/12/14.
 */
@RestController
@RequestMapping("/user_tasks")
public class UserTaskController extends BaseController {
    @Autowired
    TaskService taskService;

    @Autowired
    TaskCityService taskCityService;

    @Autowired
    UserTaskService userTaskService;

    /**
     * 用户领取任务
     * @param userTask
     * @param errors
     * @param ucb
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<UserTask> receiveTask(
            @Valid @RequestBody UserTask userTask,
            Errors errors,
            UriComponentsBuilder ucb) {
        assertFieldError(errors);

        Assert.notNull(userTask.getAppId(), "App id cannot be empty.");
        Assert.notNull(userTask.getAppUserId(), "App user id cannot be empty.");
        Assert.notNull(userTask.getTaskId(), "Task id cannot be empty.");
        Task task = taskService.findOne(userTask.getTaskId());
        Assert.notNull(task, "Task not found.");

        long now = System.currentTimeMillis() / 1000;
        Assert.isTrue(now >= task.getBeginTime(), "Task has not started.");
        Assert.isTrue(now <= task.getEndTime(), "Task has finished.");
        // check check
        if (task.getCityLimited()) {
            Assert.notNull(userTask.getProvinceId(), "Province id cannot be empty.");
            Assert.notNull(userTask.getCityId(), "City id cannot be empty.");
            Assert.isTrue(taskCityService.containsCity(userTask));
        }

        userTask.setTaskEndTime(task.getEndTime());
        userTask.setStatus(UserTaskStatus.ONGOING.value());
        userTask.setCreatedTime(now);
        userTask.setModifiedTime(now);
        userTask.setTaskName(task.getName());
        userTask.setTaskTypeName(task.getTaskTypeName());
        userTask.setPrice(task.getPrice());
        userTask.setCompletedTime(null);
        userTask.setReviewerUserId(null);
        userTask.setReviewEndTime(null);
        userTask.setReviewedTime(null);

        UserTask savedUserTask = userTaskService.receiveTask(userTask, task);

        HttpHeaders httpHeaders = new HttpHeaders();
        URI locationUri = ucb.path("/user_tasks/")
                .path(String.valueOf(savedUserTask.getId()))
                .build()
                .toUri();
        httpHeaders.setLocation(locationUri);

        return new ResponseEntity<>(savedUserTask, httpHeaders, HttpStatus.CREATED);
    }
}
