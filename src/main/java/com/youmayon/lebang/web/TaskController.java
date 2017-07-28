package com.youmayon.lebang.web;

import com.youmayon.lebang.constant.LogicConstants;
import com.youmayon.lebang.domain.Task;
import com.youmayon.lebang.domain.TaskType;
import com.youmayon.lebang.service.TaskService;
import com.youmayon.lebang.service.TaskTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jawinton on 17/05/03.
 */
@RestController
@RequestMapping("/tasks")
public class TaskController extends BaseController {
    @Autowired
    TaskService taskService;

    @Autowired
    TaskTypeService taskTypeService;

    /**
     * 添加任务
     * @param task
     * @param errors
     * @param ucb
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Task> save(
            @Valid @RequestBody Task task,
            Errors errors,
            UriComponentsBuilder ucb) {
        assertFieldError(errors);

        Assert.isNull(taskService.findOne(task.getName()), "Task name conflict.");

        TaskType taskType = taskTypeService.findOne(task.getTaskTypeId(), true);
        Assert.notNull(taskType, "Task type not found.");
        Assert.isTrue(task.getEndTime() > task.getBeginTime(), "End time should be greater than begin time.");

        task.setCreatedTime(System.currentTimeMillis() / 1000);
        task.setModifiedTime(task.getCreatedTime());
        task.setTaskTypeName(taskType.getName());
        task.setLeftAmount(task.getAmount());
        task.setCompletedAmount(0L);
        task.setAcceptedAmount(0L);
        task.setRejectedAmount(0L);
        Task savedTask = taskService.save(task, true);

        HttpHeaders httpHeaders = new HttpHeaders();
        URI locationUri = ucb.path("/tasks/")
                .path(String.valueOf(savedTask.getId()))
                .build()
                .toUri();
        httpHeaders.setLocation(locationUri);

        return new ResponseEntity<>(savedTask, httpHeaders, HttpStatus.CREATED);
    }

    /**
     * 修改任务
     * @param id
     * @param unsavedTask
     * @param errors
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json")
    public Task update(
            @PathVariable long id,
            @Valid @RequestBody Task unsavedTask,
            Errors errors) {
        assertFieldError(errors);

        Task savedTask = taskService.findOne(id, true);
        Assert.notNull(savedTask, "Task not found.");

        TaskType taskType = taskTypeService.findOne(unsavedTask.getTaskTypeId(), true);
        Assert.notNull(taskType, "Task type not found.");
        Assert.isTrue(unsavedTask.getEndTime() > unsavedTask.getBeginTime(), "End time should be greater than begin time.");
        long leftAmount = unsavedTask.getAmount() - savedTask.getAmount() + savedTask.getLeftAmount();
        Assert.isTrue(leftAmount > 0, "Task amount error.");

        unsavedTask.setId(id);
        unsavedTask.setCreatedTime(savedTask.getCreatedTime());
        unsavedTask.setModifiedTime(System.currentTimeMillis() / 1000);
        unsavedTask.setTaskTypeName(taskType.getName());
        unsavedTask.setLeftAmount(leftAmount);
        unsavedTask.setCompletedAmount(savedTask.getCompletedAmount());
        unsavedTask.setAcceptedAmount(savedTask.getAcceptedAmount());
        unsavedTask.setRejectedAmount(savedTask.getRejectedAmount());
        unsavedTask.setEnabled(savedTask.getEnabled());
        return taskService.save(unsavedTask);
    }

    /**
     * 获取任务详情
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Task get(@PathVariable long id) {
        Task task = taskService.findOneWithProcedures(id);
        Assert.notNull(task, "Task not found.");

        return task;
    }

    /**
     * 分页获取任务列表
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Page<Task> list(
            @RequestParam(value = "beginTime", defaultValue = LogicConstants.NEGATIVE_ONE) long beginTime,
            @RequestParam(value = "endTime", defaultValue = LogicConstants.NEGATIVE_ONE) long endTime,
            @RequestParam(value = "taskTypeId", defaultValue = LogicConstants.NEGATIVE_ONE) long taskTypeId,
            @RequestParam(value = "enabled", defaultValue = LogicConstants.NEGATIVE_ONE) int enabled,
            @RequestParam(value = "page", defaultValue = LogicConstants.DEFAULT_PAGE) int page,
            @RequestParam(value = "size", defaultValue = LogicConstants.DEFAULT_SIZE) int size) {
        return taskService.list(beginTime, endTime, taskTypeId, enabled, page, size);
    }

    /**
     * 禁用启用任务
     * @param id
     * @param enabled
     * @return
     */
    @RequestMapping(value = "/{id}/enabled/{enabled}", method = RequestMethod.PATCH, consumes = "application/json")
    public Task patch(@PathVariable long id, @PathVariable boolean enabled) {
        Task savedTask = taskService.findOne(id);
        Assert.notNull(savedTask, "Task not found.");

        savedTask.setEnabled(enabled);
        savedTask.setModifiedTime(System.currentTimeMillis() / 1000);
        return taskService.save(savedTask);
    }

    @RequestMapping(value = "/app_users/{appUserId}", method = RequestMethod.GET, consumes = "application/json")
    public Map<String, List<Task>> appUserReceivableTasks(
            @PathVariable String appUserId,
            @RequestParam(value = "deviceType") int deviceType,
            OAuth2Authentication auth) {
        String appName = auth.getOAuth2Request().getClientId();
        List<Task> content = taskService.userReceivableTaskList(appName, appUserId, deviceType);
        Map<String, List<Task>> contentMap = new HashMap<>();
        contentMap.put("content", content);
        return contentMap;
    }
}
