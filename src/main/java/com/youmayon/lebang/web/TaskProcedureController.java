package com.youmayon.lebang.web;

import com.youmayon.lebang.domain.Task;
import com.youmayon.lebang.domain.TaskProcedure;
import com.youmayon.lebang.domain.TaskProcedureId;
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
     * 批量添加任务步骤
     * @param taskProcedureList
     * @param errors
     * @return
     */
    @RequestMapping(value = "/tasks/{taskId}", method = RequestMethod.PUT, consumes = "application/json")
    public List<TaskProcedure> put(
            @PathVariable long taskId,
            @Valid @RequestBody List<TaskProcedure> taskProcedureList,
            Errors errors) {
        assertFieldError(errors);

        Task task = taskService.findOne(taskId, true);
        Assert.notNull(task, "Task not found.");

        int i = 0;
        for (TaskProcedure taskProcedure : taskProcedureList) {
            taskProcedure.setTaskProcedureId(new TaskProcedureId(taskId, ++i));
            taskProcedure.setCreatedTime(System.currentTimeMillis() / 1000);
            taskProcedure.setModifiedTime(taskProcedure.getCreatedTime());
        }

        List<TaskProcedure> savedTaskProcedureList = taskProcedureService.save(taskId, taskProcedureList);

        return savedTaskProcedureList;
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
    @RequestMapping(value = "/tasks/{taskId}", method = RequestMethod.GET)
    public List<TaskProcedure> list(@PathVariable long taskId) {
        return taskProcedureService.list(taskId);
    }
}
