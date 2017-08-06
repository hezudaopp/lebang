package com.youmayon.lebang.service;

import com.youmayon.lebang.domain.TaskProcedure;

import java.util.Collection;
import java.util.List;

/**
 * 任务步骤service类
 * Created by Jawinton on 17/05/05.
 */
public interface TaskProcedureService {
    /**
     * 任务步骤详情
     * @param id
     * @return
     */
    TaskProcedure findOne(long id);

    /**
     * 保存任务步骤
     * @param taskProcedure
     * @return
     */
    TaskProcedure save(TaskProcedure taskProcedure);

    /**
     * 批量保存任务步骤
     * @param taskId
     * @param taskProcedures
     * @return
     */
    List<TaskProcedure> save(long taskId, Collection<TaskProcedure> taskProcedures);

    /**
     * 任务步骤列表
     * @param taskId
     * @return
     */
    List<TaskProcedure> list(long taskId);

    /**
     * 删除任务步骤
     * @param id
     * @return
     */
    void delete(long id);
}
