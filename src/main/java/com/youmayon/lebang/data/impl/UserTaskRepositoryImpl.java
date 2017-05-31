package com.youmayon.lebang.data.impl;

import com.youmayon.lebang.data.UserTaskDao;
import com.youmayon.lebang.enums.UserTaskStatus;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Jawinton on 16/12/21.
 */
public class UserTaskRepositoryImpl implements UserTaskDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Object[]> taskAppReceivedAmount(long beginTime, long endTime) {
        final String sql = "SELECT ut, COUNT(ut) FROM UserTask ut WHERE ut.createdTime >= '" + beginTime + "' AND ut.createdTime < '" + endTime + "' GROUP BY ut.taskId, ut.appId";
        return entityManager.createQuery(sql).getResultList();
    }

    @Override
    public List<Object[]> taskAppCompletedAmount(long beginTime, long endTime) {
        final String sql = "SELECT ut, COUNT(ut) FROM UserTask ut WHERE ut.completedTime >= '" + beginTime + "' AND ut.completedTime < '" + endTime + "' GROUP BY ut.taskId, ut.appId";
        return entityManager.createQuery(sql).getResultList();
    }

    @Override
    public List<Object[]> taskAppAcceptedAmountAndTotalFlow(long beginTime, long endTime) {
        final String sql = "SELECT ut, COUNT(ut), SUM(ut.price) FROM UserTask ut WHERE ut.reviewedTime >= '" + beginTime + "' AND ut.reviewedTime < '" + endTime + "' AND ut.status = '" + UserTaskStatus.ACCEPTED.value() + "' GROUP BY ut.taskId, ut.appId";
        return entityManager.createQuery(sql).getResultList();
    }

    @Override
    public List<Object[]> reviewerTaskAcceptedAmountAndTotalFlow(long beginTime, long endTime) {
        final String sql = "SELECT ut, COUNT(ut), SUM(ut.price) FROM UserTask ut WHERE ut.reviewedTime >= '" + beginTime + "' AND ut.reviewedTime < '" + endTime + "' AND ut.status = '" + UserTaskStatus.ACCEPTED.value() + "' GROUP BY ut.reviewerUserId";
        return entityManager.createQuery(sql).getResultList();
    }

    @Override
    public List<Object[]> reviewerTaskReviewedAmount(long beginTime, long endTime) {
        final String sql = "SELECT ut, COUNT(ut) FROM UserTask ut WHERE ut.reviewedTime >= '" + beginTime + "' AND ut.reviewedTime < '" + endTime + "' AND (ut.status = '" + UserTaskStatus.ACCEPTED.value() + "' OR ut.status = '" + UserTaskStatus.REJECTED.value() + "') GROUP BY ut.reviewerUserId";
        return entityManager.createQuery(sql).getResultList();
    }
}
