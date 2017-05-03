package com.youmayon.lebang.service;

import com.youmayon.lebang.domain.TaskType;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 任务类型service类
 * Created by Jawinton on 17/05/03.
 */
public interface TaskTypeService {
    /**
     * 保存任务类型
     * @param taskType
     * @return
     */
    TaskType save(TaskType taskType);

    /**
     * 根据enabled字段过滤任务类型列表
     * @param enabled
     * @return
     */
    List<TaskType> list(boolean enabled);

    /**
     * 分页获取任务类型列表
     * @return
     */
    Page<TaskType> list(int page, int size);

    /**
     * 任务类型详情
     * @param id
     * @return
     */
    TaskType findOne(long id);

    /**
     * 任务类型详情
     * @param id
     * @param enabled
     * @return
     */
    TaskType findOne(long id, boolean enabled);
}
