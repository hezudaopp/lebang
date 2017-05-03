package com.youmayon.lebang.service.impl;

import com.youmayon.lebang.data.TaskTypeRepository;
import com.youmayon.lebang.domain.TaskType;
import com.youmayon.lebang.service.TaskTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Jawinton on 17/05/03.
 */
@Service
public class TaskTypeServiceImpl implements TaskTypeService {
    @Autowired
    TaskTypeRepository taskTypeRepository;

    @Override
    public TaskType save(TaskType taskType) {
        return taskTypeRepository.save(taskType);
    }

    @Override
    public List<TaskType> list(boolean enabled) {
        return taskTypeRepository.findByEnabled(enabled);
    }

    @Override
    public Page<TaskType> list(int page, int size) {
        return taskTypeRepository.findAll(new PageRequest(page, size));
    }

    @Override
    public TaskType findOne(long id) {
        return taskTypeRepository.findOne(id);
    }

    @Override
    public TaskType findOne(long id, boolean enabled) {
        TaskType taskType = findOne(id);
        if (taskType == null) {
            return null;
        }
        return taskType.getEnabled() == enabled ? taskType : null;
    }
}
