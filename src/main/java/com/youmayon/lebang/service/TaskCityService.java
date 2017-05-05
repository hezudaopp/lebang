package com.youmayon.lebang.service;

import com.youmayon.lebang.domain.TaskCity;
import com.youmayon.lebang.domain.UserTask;

import java.util.Collection;
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
     * 检测城市是否一致
     * @param userTask
     * @return
     */
    boolean containsCity(UserTask userTask);

    /**
     * 批量保存任务城市
     * @param taskCities
     * @return
     */
    List<TaskCity> save(Collection<TaskCity> taskCities);

    /**
     * 批量删除任务城市
     * @param taskCities
     */
    void delete(Collection<TaskCity> taskCities);
}
