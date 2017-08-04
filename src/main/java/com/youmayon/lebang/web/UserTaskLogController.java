package com.youmayon.lebang.web;

import com.youmayon.lebang.domain.UserTaskLog;
import com.youmayon.lebang.service.UserTaskLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Jawinton on 17/08/03.
 * 用户任务日志控制器
 */
@RestController
@RequestMapping("/user_task_logs")
public class UserTaskLogController extends BaseController {
    @Autowired
    UserTaskLogService userTaskLogService;

    /**
     * 获取用户任务日志详情
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public UserTaskLog get(@PathVariable long id) {
        UserTaskLog userTaskLog = userTaskLogService.get(id);
        Assert.notNull(userTaskLog, "User task log not found.");

        return userTaskLog;
    }

    /**
     * 根据用户任务id获取任务日志列表
     * @return
     */
    @RequestMapping(value = "/user_tasks/{userTaskId}", method = RequestMethod.GET)
    public List<UserTaskLog> list(@PathVariable long userTaskId) {
        return userTaskLogService.list(userTaskId);
    }
}
