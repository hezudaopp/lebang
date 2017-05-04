package com.youmayon.lebang.service;

import com.youmayon.lebang.domain.TaskCity;
import com.youmayon.lebang.domain.UserTask;

import java.util.List;

/**
 * 任务城市service类
 * Created by Jawinton on 17/05/04.
 */
public interface TaskCityService {
    /**
     * 任务城市列表
     * @param taskId
     * @return
     */
    List<TaskCity> list(long taskId);

    /**
     * 任务城市列表
     * @param taskId
     * @param enabled
     * @return
     */
    List<TaskCity> list(long taskId, boolean enabled);

    /**
     * 检测城市是否一致
     * @param userTask
     * @return
     */
    boolean containsCity(UserTask userTask);
}
