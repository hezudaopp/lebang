package com.youmayon.lebang.data.impl;

import com.youmayon.lebang.data.TaskDao;
import com.youmayon.lebang.data.UserTaskDao;
import com.youmayon.lebang.enums.UserTaskStatus;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Jawinton on 16/12/21.
 */
public class TaskRepositoryImpl implements TaskDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Object[]> totalAmountObjectArray() {
        final String sql = "SELECT SUM(t.amount), SUM(t.leftAmount), SUM(t.completedAmount), SUM(t.acceptedAmount), SUM(t.rejectedAmount) FROM Task t";
        return entityManager.createQuery(sql).getResultList();
    }
}
