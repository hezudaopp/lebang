package com.youmayon.lebang.data.impl;

import com.youmayon.lebang.data.UserTaskDao;
import com.youmayon.lebang.domain.TaskAppStatistics;
import com.youmayon.lebang.enums.UserTaskStatus;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jawinton on 16/12/21.
 */
public class UserTaskRepositoryImpl implements UserTaskDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<TaskAppStatistics> taskAppStatistics(long beginTime, long endTime) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<TaskAppStatistics> criteriaQuery = criteriaBuilder.createQuery(TaskAppStatistics.class);
        Root<TaskAppStatistics> root = criteriaQuery.from(TaskAppStatistics.class);
        criteriaQuery.select(criteriaBuilder.construct(TaskAppStatistics.class,
                root.get("taskId"),
                root.get("appId"),
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
        List<Expression<?>> groupList = new ArrayList<>();
        if (isDistinctApp) {
            groupList.add(root.get("appId"));
        }
        if (isDistinctTask) {
            groupList.add(root.get("taskId"));
        }
        if (beginTime != endTime) {
            groupList.add(root.get("beginTime"));
            groupList.add(root.get("endTime"));
        }
        criteriaQuery.groupBy(groupList);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<Object[]> taskAppAcceptedAmountAndTotalFlow(long beginTime, long endTime) {
        final String sql = "SELECT ut, COUNT(ut), SUM(ut.price) FROM UserTask ut WHERE ut.reviewedTime >= '" + beginTime + "' AND ut.reviewedTime < '" + endTime + "' AND ut.status = '" + UserTaskStatus.ACCEPTED.value() + "' GROUP BY ut.taskId, ut.appId";
        return entityManager.createQuery(sql).getResultList();
    }
}
