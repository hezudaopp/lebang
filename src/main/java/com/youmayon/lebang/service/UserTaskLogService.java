package com.youmayon.lebang.service;

import com.youmayon.lebang.domain.UserTaskLog;

import java.util.List;

/**
 * 任务service类
 * Created by Jawinton on 17/05/03.
 */
public interface UserTaskLogService {
    /**
     * 保存任务用户任务日志
     * @param userTaskLog
     * @return
     */
    UserTaskLog save(UserTaskLog userTaskLog);

    /**
     * 获取用户任务日志列表
     * @param userTaskId
     * @return
     */
    List<UserTaskLog> list(long userTaskId);

    /**
     * 获取用户任务日志详情
     * @param id
     * @return
     */
    UserTaskLog get(long id);
}
