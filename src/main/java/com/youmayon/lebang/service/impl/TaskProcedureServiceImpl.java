package com.youmayon.lebang.service.impl;

import com.youmayon.lebang.data.TaskProcedureRepository;
import com.youmayon.lebang.domain.TaskProcedure;
import com.youmayon.lebang.exceptions.InvalidArgumentException;
import com.youmayon.lebang.service.TaskProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * Created by Jawinton on 17/05/05.
 */
@Service
public class TaskProcedureServiceImpl implements TaskProcedureService {
    @Autowired
    TaskProcedureRepository taskProcedureRepository;

    @Override
    public TaskProcedure findOne(long id) {
        return taskProcedureRepository.findOne(id);
    }

    @Override
    public TaskProcedure save(TaskProcedure taskProcedure) {
        try {
            return taskProcedureRepository.save(taskProcedure);
        } catch (DataIntegrityViolationException e) {
            throw new InvalidArgumentException("任务步骤顺序重复");
        }
    }

    @Override
    public List<TaskProcedure> save(long taskId, Collection<TaskProcedure> taskProcedures) {
        taskProcedureRepository.deleteByTaskIdAndProcedureOrderGreaterEqual(taskId, taskProcedures.size());
        try {
            return taskProcedureRepository.save(taskProcedures);
        } catch (DataIntegrityViolationException e) {
            throw new InvalidArgumentException("任务步骤顺序重复");
        }
    }

    @Override
    public List<TaskProcedure> list(long taskId) {
        return taskProcedureRepository.findByTaskIdOrderByProcedureOrderAsc(taskId);
    }

    @Override
    public void delete(long id) {
        taskProcedureRepository.delete(id);
    }
}
