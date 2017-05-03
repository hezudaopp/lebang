package com.youmayon.lebang.web;

import com.youmayon.lebang.constant.LogicConstants;
import com.youmayon.lebang.domain.TaskType;
import com.youmayon.lebang.service.TaskTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
 * Created by Jawinton on 16/12/14.
 */
@RestController
@RequestMapping("/task_types")
public class TaskTypeController extends BaseController {
    @Autowired
    TaskTypeService taskTypeService;

    /**
     * 添加任务类型
     * @param taskType
     * @param errors
     * @param ucb
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<TaskType> save(
            @Valid @RequestBody TaskType taskType,
            Errors errors,
            UriComponentsBuilder ucb) {
        assertFieldError(errors);

        taskType.setCreatedTime(System.currentTimeMillis() / 1000);
        taskType.setModifiedTime(taskType.getCreatedTime());
        TaskType savedTaskType = taskTypeService.save(taskType);

        HttpHeaders httpHeaders = new HttpHeaders();
        URI locationUri = ucb.path("/task_types/")
                .path(String.valueOf(savedTaskType.getId()))
                .build()
                .toUri();
        httpHeaders.setLocation(locationUri);

        return new ResponseEntity<>(savedTaskType, httpHeaders, HttpStatus.CREATED);
    }

    /**
     * 获取任务类型详情
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public TaskType get(@PathVariable long id) {
        TaskType taskType = taskTypeService.findOne(id);
        Assert.notNull(taskType, "Task type not found.");

        return taskType;
    }

    /**
     * 获取启用的任务类型列表
     * @return
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<TaskType> list() {
        return taskTypeService.list(true);
    }

    /**
     * 分页获取任务类型列表
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Page<TaskType> list(@RequestParam(value = "page", defaultValue = LogicConstants.DEFAULT_PAGE) int page,
                               @RequestParam(value = "size", defaultValue = LogicConstants.DEFAULT_SIZE) int size) {
        return taskTypeService.list(page, size);
    }

    /**
     * 禁用启用任务类型
     * @param id
     * @param enabled
     * @return
     */
    @RequestMapping(value = "/{id}/enabled/{enabled}", method = RequestMethod.PATCH, consumes = "application/json")
    public TaskType patch(@PathVariable long id,
                          @PathVariable boolean enabled) {
        TaskType savedTaskType = taskTypeService.findOne(id);
        Assert.notNull(savedTaskType, "Task type not found.");

        savedTaskType.setEnabled(enabled);
        savedTaskType.setModifiedTime(System.currentTimeMillis() / 1000);
        return taskTypeService.save(savedTaskType);
    }
}
