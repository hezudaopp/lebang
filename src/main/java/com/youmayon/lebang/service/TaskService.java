package com.youmayon.lebang.service;

import com.youmayon.lebang.domain.Task;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 任务service类
 * Created by Jawinton on 17/05/03.
 */
public interface TaskService {
    /**
     * 保存任务类型
     * @param task
     * @return
     */
    Task save(Task task, boolean withProcedure);

    /**
     * 保存任务类型
     * @param task
     * @return
     */
    Task save(Task task);

    /**
     * 分页获取任务类型列表
     * @param beginTime
     * @param endTime
     * @param taskTypeId
     * @param enabled
     * @param page
     * @param size
     * @return
     */
    Page<Task> list(long beginTime, long endTime, long taskTypeId, int enabled, int page, int size);

    /**
     * 任务类型详情
     * @param id
     * @return
     */
    Task findOne(long id);

    Task findOne(long id, boolean enabled);
}
