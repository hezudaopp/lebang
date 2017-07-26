package com.youmayon.lebang.web;

import com.youmayon.lebang.constant.LogicConstants;
import com.youmayon.lebang.domain.OauthClientDetails;
import com.youmayon.lebang.domain.Task;
import com.youmayon.lebang.domain.User;
import com.youmayon.lebang.domain.UserTask;
import com.youmayon.lebang.enums.Role;
import com.youmayon.lebang.enums.UserTaskStatus;
import com.youmayon.lebang.service.OauthClientDetailsService;
import com.youmayon.lebang.service.TaskCityService;
import com.youmayon.lebang.service.TaskService;
import com.youmayon.lebang.service.UserTaskService;
import com.youmayon.lebang.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Map;
import java.util.Set;

/**
 * Created by Jawinton on 17/05/04.
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

    @Autowired
    OauthClientDetailsService oauthClientDetailsService;


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
            UriComponentsBuilder ucb,
            OAuth2Authentication auth) {
        assertFieldError(errors);

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

        userTask.setAppName(auth.getOAuth2Request().getClientId());
        OauthClientDetails oauthClientDetails = oauthClientDetailsService.findOne(userTask.getAppName());
        Assert.notNull(oauthClientDetails, "App not found.");
        userTask.setAppId(oauthClientDetails.getId());
        userTask.setAppName(userTask.getAppName());
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

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public UserTask get(@PathVariable long id) {
        UserTask savedUserTask = userTaskService.findOne(id);
        Assert.notNull(savedUserTask, "User task not found.");
        return savedUserTask;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Page<UserTask> list(
            @RequestParam(value = "status", defaultValue = LogicConstants.EMPTY_STRING) String status,
            @RequestParam(value = "appUserId", defaultValue = LogicConstants.EMPTY_STRING) String appUserId,
            @RequestParam(value = "page", defaultValue = LogicConstants.DEFAULT_PAGE) int page,
            @RequestParam(value = "size", defaultValue = LogicConstants.DEFAULT_SIZE) int size,
            @AuthenticationPrincipal User user,
            OAuth2Authentication auth) {
        Set<Integer> statusSet = StringUtil.splitStrToIntSet(status);
        if (user == null && auth != null) {
            Assert.isTrue(!LogicConstants.EMPTY_STRING.equals(appUserId) && appUserId != null, "AppUserId cannot be empty.");
            String appName = auth.getOAuth2Request().getClientId();
            return userTaskService.list(appName, appUserId, statusSet, page, size);
        }
        long reviewerUserId = user.getId();
        if (Role.ROLE_ADMIN.name().equals(user.getRole())) {
            reviewerUserId = -1L;
        }
        return userTaskService.list(reviewerUserId, statusSet, page, size);
    }

    @RequestMapping(value = "/{id}/completed", method = RequestMethod.PATCH, consumes = "application/json")
    public UserTask patch(
            @PathVariable long id,
            @RequestBody UserTask unsavedUserTask,
            OAuth2Authentication auth) {

        UserTask savedUserTask = userTaskService.findOne(id);
        Assert.notNull(savedUserTask, "User task not found.");

        Task task = taskService.findOne(savedUserTask.getTaskId());
        Assert.notNull(task, "Task not found.");

        Assert.isTrue(savedUserTask.getStatus() == UserTaskStatus.ONGOING.value() || savedUserTask.getStatus() == UserTaskStatus.REDOING.value(), "User task from status error.");
        String appName = auth.getOAuth2Request().getClientId();
        Assert.isTrue(appName.equals(savedUserTask.getAppName()), "App is different.");
        Assert.isTrue(savedUserTask.getAppUserId().equals(unsavedUserTask.getAppUserId()), "App user id is different.");
        Assert.notNull(unsavedUserTask.getNote(), "Note cannot be empty.");
        Assert.notNull(unsavedUserTask.getImages(), "Images cannot be empty.");

        long now = System.currentTimeMillis() / 1000;
        Assert.isTrue(now <= savedUserTask.getTaskEndTime(), "Task has finished.");

        int fromStatus = savedUserTask.getStatus();
        savedUserTask.setStatus(UserTaskStatus.COMPLETED.value());
        savedUserTask.setModifiedTime(now);
        savedUserTask.setCompletedTime(now);
        savedUserTask.setNote(unsavedUserTask.getNote());
        savedUserTask.setImages(unsavedUserTask.getImages());
        if (unsavedUserTask.getProvinceId() != null) {
            savedUserTask.setProvinceId(unsavedUserTask.getProvinceId());
        }
        if (unsavedUserTask.getCityId() != null) {
            savedUserTask.setCityId(unsavedUserTask.getCityId());
        }

        return userTaskService.completeTask(savedUserTask, task, fromStatus);
    }

    /**
     * 审核任务
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}/status/{userTaskStatus}", method = RequestMethod.PATCH, consumes = "application/json")
    public UserTask patch(
            @PathVariable long id,
            @PathVariable int userTaskStatus,
            @RequestBody Map<String, String> requestMap,
            @AuthenticationPrincipal User user) {

        UserTask savedUserTask = userTaskService.findOne(id);
        Assert.notNull(savedUserTask, "User task not found.");
        Assert.isTrue(userTaskStatus == UserTaskStatus.ACCEPTED.value() ||
                userTaskStatus == UserTaskStatus.REJECTED.value() ||
                userTaskStatus == UserTaskStatus.REDOING.value(), "User task status error.");

        Task task = taskService.findOne(savedUserTask.getTaskId());
        Assert.notNull(task, "Task not found.");

        Assert.isTrue(savedUserTask.getStatus() == UserTaskStatus.COMPLETED.value(), "User task status error.");
        long now = System.currentTimeMillis() / 1000;
        if (!Role.ROLE_ADMIN.name().equals(user.getRole())) {
            Assert.isTrue(user.getId() == savedUserTask.getReviewerUserId(), "Forbidden.");
            Assert.isTrue(now < savedUserTask.getReviewEndTime(), "Review time has expired.");
        }
        savedUserTask.setReviewedTime(now);
        savedUserTask.setStatus(userTaskStatus);
        savedUserTask.setModifiedTime(now);

        return userTaskService.reviewTask(user, savedUserTask, task, userTaskStatus, requestMap.get("remark"));
    }
}
