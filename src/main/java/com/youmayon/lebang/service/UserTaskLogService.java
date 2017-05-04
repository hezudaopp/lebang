package com.youmayon.lebang.service;

import com.youmayon.lebang.domain.Task;
import com.youmayon.lebang.domain.UserTask;
import com.youmayon.lebang.domain.UserTaskLog;

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
}
