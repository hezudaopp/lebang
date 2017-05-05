package com.youmayon.lebang.web;

import com.youmayon.lebang.domain.Task;
import com.youmayon.lebang.domain.TaskProcedure;
import com.youmayon.lebang.service.TaskProcedureService;
import com.youmayon.lebang.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

/**
 * Created by Jawinton on 17/05/03.
 */
@RestController
@RequestMapping("/task_procedures")
public class TaskProcedureController extends BaseController {
    @Autowired
    TaskProcedureService taskProcedureService;

    @Autowired
    TaskService taskService;

    /**
     * 添加任务步骤
     * @param taskProcedure
     * @param errors
     * @param ucb
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<TaskProcedure> save(
            @Valid @RequestBody TaskProcedure taskProcedure,
            Errors errors,
            UriComponentsBuilder ucb) {
        assertFieldError(errors);

        Task task = taskService.findOne(taskProcedure.getTaskId(), true);
        Assert.notNull(task, "Task not found.");

        taskProcedure.setCreatedTime(System.currentTimeMillis() / 1000);
        taskProcedure.setModifiedTime(taskProcedure.getCreatedTime());
        TaskProcedure savedTaskProcedure = taskProcedureService.save(taskProcedure);

        HttpHeaders httpHeaders = new HttpHeaders();
        URI locationUri = ucb.path("/task_procedures/")
                .path(String.valueOf(savedTaskProcedure.getId()))
                .build()
                .toUri();
        httpHeaders.setLocation(locationUri);

        return new ResponseEntity<>(savedTaskProcedure, httpHeaders, HttpStatus.CREATED);
    }

    /**
     * 获取任务步骤详情
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public TaskProcedure get(@PathVariable long id) {
        TaskProcedure taskProcedure = taskProcedureService.findOne(id);
        Assert.notNull(taskProcedure, "Task procedure not found.");

        return taskProcedure;
    }

    /**
     * 删除任务步骤
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable long id) {
        taskProcedureService.delete(id);
    }

    /**
     * 获取任务的任务步骤列表
     * @return
     */
    @RequestMapping(value = "/task/{taskId}", method = RequestMethod.GET)
    public List<TaskProcedure> list(@PathVariable long taskId) {
        return taskProcedureService.list(taskId);
    }

    /**
     * 修改任务步骤
     * @param id
     * @param unsavedTaskProcedure
     * @param errors
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json")
    public TaskProcedure update(
            @PathVariable long id,
            @Valid @RequestBody TaskProcedure unsavedTaskProcedure,
            Errors errors) {
        assertFieldError(errors);

        TaskProcedure savedTask = taskProcedureService.findOne(id);
        Assert.notNull(savedTask, "Task procedure not found.");

        unsavedTaskProcedure.setId(id);
        unsavedTaskProcedure.setTaskId(savedTask.getTaskId());
        unsavedTaskProcedure.setCreatedTime(savedTask.getCreatedTime());
        unsavedTaskProcedure.setModifiedTime(System.currentTimeMillis() / 1000);
        return taskProcedureService.save(unsavedTaskProcedure);
    }
}
