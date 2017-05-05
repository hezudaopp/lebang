package com.youmayon.lebang.service.impl;

import com.youmayon.lebang.data.TaskRepository;
import com.youmayon.lebang.domain.Task;
import com.youmayon.lebang.domain.TaskProcedure;
import com.youmayon.lebang.service.TaskProcedureService;
import com.youmayon.lebang.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jawinton on 17/05/03.
 */
@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    TaskRepository taskRepository;

    @Autowired
    TaskProcedureService taskProcedureService;

    @Override
    @Transactional
    public Task save(Task task, boolean withProcedure) {
        Task savedTask = save(task);
        if (!withProcedure) {
            return savedTask;
        }
        if (task.getTaskProcedures() == null || task.getTaskProcedures().isEmpty()) {
            return savedTask;
        }
        for (TaskProcedure taskProcedure : task.getTaskProcedures()) {
            Assert.notNull(taskProcedure.getDescription(), "Task procedure description cannot be empty.");
            Assert.notNull(taskProcedure.getImages(), "Task procedure images cannot be empty.");
            Assert.notNull(taskProcedure.getProcedureOrder(), "Task procedure order cannot be empty.");
            taskProcedure.setTaskId(savedTask.getId());
            taskProcedure.setCreatedTime(task.getModifiedTime());
            taskProcedure.setModifiedTime(task.getModifiedTime());
        }
        taskProcedureService.save(task.getTaskProcedures());
        return savedTask;
    }

    @Override
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Page<Task> list(long beginTime, long endTime, long taskTypeId, int enabled, int page, int size) {
        Pageable pageable = new PageRequest(page, size);
        TaskFilter taskFilter = new TaskFilter();
        taskFilter.setBeginTime(beginTime);
        taskFilter.setEndTime(endTime);
        taskFilter.setTaskTypeId(taskTypeId);
        taskFilter.setEnabled(enabled);
        Specification<Task> specification = this.getWhereClause(taskFilter);
        return  taskRepository.findAll(specification, pageable);
    }

    @Override
    public Task findOne(long id) {
        Task savedTask = taskRepository.findOne(id);
        if (savedTask == null) {
            return null;
        }
        savedTask.setTaskProcedures(taskProcedureService.list(id));
        return savedTask;
    }

    @Override
    public Task findOne(long id, boolean enabled) {
        Task savedTask = findOne(id);
        if (savedTask == null) {
            return null;
        }
        return savedTask.getEnabled() == enabled ? savedTask : null;
    }

    private class TaskFilter {
        private long beginTime;
        private long endTime;
        private long taskTypeId;
        private int enabled;

        public TaskFilter() {}

        public long getBeginTime() {
            return beginTime;
        }

        public void setBeginTime(long beginTime) {
            this.beginTime = beginTime;
        }

        public long getEndTime() {
            return endTime;
        }

        public void setEndTime(long endTime) {
            this.endTime = endTime;
        }

        public long getTaskTypeId() {
            return taskTypeId;
        }

        public void setTaskTypeId(long taskTypeId) {
            this.taskTypeId = taskTypeId;
        }

        public int getEnabled() {
            return enabled;
        }

        public void setEnabled(int enabled) {
            this.enabled = enabled;
        }
    }

    /**
     * generate where clause dynamically.
     * @param taskFilter
     * @return
     */
    private Specification<Task> getWhereClause(final TaskFilter taskFilter){
        return new Specification<Task>() {
            @Override
            public Predicate toPredicate(Root<Task> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                if (taskFilter.getBeginTime() >= 0) {
                    predicate.add(cb.ge(root.get("beginTime").as(Long.class), taskFilter.getBeginTime()));
                }
                if (taskFilter.getEndTime() >= 0) {
                    predicate.add(cb.le(root.get("endTime").as(Long.class), taskFilter.getEndTime()));
                }
                if (taskFilter.getTaskTypeId() >= 0) {
                    predicate.add(cb.equal(root.get("taskTypeId").as(Long.class), taskFilter.getTaskTypeId()));
                }
                if (taskFilter.getEnabled() >= 0) {
                    predicate.add(cb.equal(root.get("enabled").as(Integer.class), taskFilter.getEnabled()));
                }
                Predicate[] pre = new Predicate[predicate.size()];
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
    }
}
