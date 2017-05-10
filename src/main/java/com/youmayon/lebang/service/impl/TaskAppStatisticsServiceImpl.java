package com.youmayon.lebang.service.impl;

import com.youmayon.lebang.data.TaskAppStatisticsRepository;
import com.youmayon.lebang.data.TaskRepository;
import com.youmayon.lebang.domain.Task;
import com.youmayon.lebang.domain.TaskAppStatistics;
import com.youmayon.lebang.domain.TaskCity;
import com.youmayon.lebang.domain.TaskProcedure;
import com.youmayon.lebang.service.TaskAppStatisticsService;
import com.youmayon.lebang.service.TaskCityService;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Jawinton on 17/05/03.
 */
@Service
public class TaskAppStatisticsServiceImpl implements TaskAppStatisticsService {
    @Autowired
    TaskAppStatisticsRepository taskAppStatisticsRepository;

    @Override
    public List<TaskAppStatistics> list(long beginTime, long endTime) {
        return listTaskAppStatistics(0, 0, beginTime, endTime);
    }

    @Override
    public List<TaskAppStatistics> listAppStatistics(long appId, long beginTime, long endTime) {
        return listTaskAppStatistics(0, appId, beginTime, endTime);
    }

    @Override
    public List<TaskAppStatistics> listTaskStatistics(long taskId, long beginTime, long endTime) {
        return listTaskAppStatistics(taskId, 0, beginTime, endTime);
    }

    @Override
    public List<TaskAppStatistics> listTaskAppStatistics(long taskId, long appId, long beginTime, long endTime) {
        TaskAppStatisticsFilter taskAppStatisticsFilter = new TaskAppStatisticsFilter();
        taskAppStatisticsFilter.setTaskId(taskId);
        taskAppStatisticsFilter.setAppId(appId);
        taskAppStatisticsFilter.setBeginTime(beginTime);
        taskAppStatisticsFilter.setEndTime(endTime);
        Specification<TaskAppStatistics> specification = this.getWhereClause(taskAppStatisticsFilter);
        return taskAppStatisticsRepository.findAll(specification);
    }

    private class TaskAppStatisticsFilter {
        private long taskId;
        private long appId;
        private long beginTime;
        private long endTime;

        public TaskAppStatisticsFilter() {}

        public long getTaskId() {
            return taskId;
        }

        public void setTaskId(long taskId) {
            this.taskId = taskId;
        }

        public long getAppId() {
            return appId;
        }

        public void setAppId(long appId) {
            this.appId = appId;
        }

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
    }

    /**
     * generate where clause dynamically.
     * @param taskAppStatisticsFilter
     * @return
     */
    private Specification<TaskAppStatistics> getWhereClause(final TaskAppStatisticsFilter taskAppStatisticsFilter){
        return new Specification<TaskAppStatistics>() {
            @Override
            public Predicate toPredicate(Root<TaskAppStatistics> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                if (taskAppStatisticsFilter.getTaskId() > 0) {
                    predicate.add(cb.equal(root.get("taskId").as(Long.class), taskAppStatisticsFilter.getTaskId()));
                }
                if (taskAppStatisticsFilter.getAppId() > 0) {
                    predicate.add(cb.equal(root.get("appId").as(Long.class), taskAppStatisticsFilter.getAppId()));
                }
                if (taskAppStatisticsFilter.getBeginTime() >= 0) {
                    predicate.add(cb.ge(root.get("beginTime").as(Long.class), taskAppStatisticsFilter.getBeginTime()));
                }
                if (taskAppStatisticsFilter.getEndTime() >= 0) {
                    predicate.add(cb.le(root.get("endTime").as(Long.class), taskAppStatisticsFilter.getEndTime()));
                }
                Predicate[] pre = new Predicate[predicate.size()];
                return query.groupBy(root.get("beginTime"), root.get("endTime")).having(predicate.toArray(pre)).getRestriction();
            }
        };
    }
}
