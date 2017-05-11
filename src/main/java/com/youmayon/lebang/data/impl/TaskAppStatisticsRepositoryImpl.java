package com.youmayon.lebang.data.impl;

import com.youmayon.lebang.data.TaskAppStatisticsDao;
import com.youmayon.lebang.domain.TaskAppStatistics;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jawinton on 16/12/21.
 */
public class TaskAppStatisticsRepositoryImpl implements TaskAppStatisticsDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<TaskAppStatistics> findByTaskIdAndAppIdAndBeginTimeGreaterThanAndEndTimeLessThanGroupByBeginTimeAndEndTime(long taskId, long appId, long beginTime, long endTime) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<TaskAppStatistics> criteriaQuery = criteriaBuilder.createQuery(TaskAppStatistics.class);
        Root<TaskAppStatistics> root = criteriaQuery.from(TaskAppStatistics.class);
        criteriaQuery.select(criteriaBuilder.construct(TaskAppStatistics.class,
                root.get("beginTime"),
                root.get("endTime"),
                criteriaBuilder.sum(root.get("totalFlow")),
                criteriaBuilder.sum(root.get("receivedAmount")),
                criteriaBuilder.sum(root.get("completedAmount")),
                criteriaBuilder.sum(root.get("acceptedAmount"))));
        List<Predicate> predicateList = new ArrayList<>();
        if (taskId > 0) {
            predicateList.add(criteriaBuilder.equal(root.get("taskId").as(Long.class), taskId));
        }
        if (appId > 0) {
            predicateList.add(criteriaBuilder.equal(root.get("appId").as(Long.class), appId));
        }
        if (beginTime >= 0) {
            predicateList.add(criteriaBuilder.ge(root.get("beginTime").as(Long.class), beginTime));
        }
        if (endTime >= 0) {
            predicateList.add(criteriaBuilder.le(root.get("endTime").as(Long.class), endTime));
        }
        Predicate[] predicates = new Predicate[predicateList.size()];
        criteriaQuery.where(predicateList.toArray(predicates));
        criteriaQuery.groupBy(root.get("beginTime"), root.get("endTime"));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
