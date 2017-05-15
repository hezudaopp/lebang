package com.youmayon.lebang.data.impl;

import com.youmayon.lebang.data.ReviewerTaskStatisticsDao;
import com.youmayon.lebang.data.ReviewerTaskStatisticsDao;
import com.youmayon.lebang.domain.ReviewerTaskStatistics;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Jawinton on 16/12/21.
 */
public class ReviewerTaskStatisticsRepositoryImpl implements ReviewerTaskStatisticsDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ReviewerTaskStatistics> findByBeginTimeGreaterThanAndEndTimeLessThanGroupByReviewerUserIdAndBeginTimeAndEndTime(long beginTime, long endTime, int page, int size) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ReviewerTaskStatistics> criteriaQuery = criteriaBuilder.createQuery(ReviewerTaskStatistics.class);
        Root<ReviewerTaskStatistics> root = criteriaQuery.from(ReviewerTaskStatistics.class);
        criteriaQuery.select(criteriaBuilder.construct(ReviewerTaskStatistics.class,
                root.get("reviewerUserId"),
                root.get("beginTime"),
                root.get("endTime"),
                criteriaBuilder.sum(root.get("reviewedAmount")),
                criteriaBuilder.sum(root.get("acceptedAmount"))));
        List<Predicate> predicateList = new ArrayList<>();
        if (beginTime >= 0) {
            predicateList.add(criteriaBuilder.ge(root.get("beginTime").as(Long.class), beginTime));
        }
        if (endTime >= 0) {
            predicateList.add(criteriaBuilder.le(root.get("endTime").as(Long.class), endTime));
        }
        Predicate[] predicates = new Predicate[predicateList.size()];
        criteriaQuery.where(predicateList.toArray(predicates));
        List<Expression<?>> groupList = new ArrayList<>();
        groupList.add(root.get("reviewerUserId"));
        if (beginTime != endTime) {
            groupList.add(root.get("beginTime"));
            groupList.add(root.get("endTime"));
        }
        criteriaQuery.groupBy(groupList);
        return entityManager.createQuery(criteriaQuery).setFirstResult(page * size).setMaxResults(size).getResultList();
    }

    @Override
    public List<ReviewerTaskStatistics> findByReviewerUserIdInAndBeginTimeAndEndTimeGroupByReviewerUserId(Collection<Long> reviewerUserIds, long beginTime, long endTime) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ReviewerTaskStatistics> criteriaQuery = criteriaBuilder.createQuery(ReviewerTaskStatistics.class);
        Root<ReviewerTaskStatistics> root = criteriaQuery.from(ReviewerTaskStatistics.class);
        criteriaQuery.select(criteriaBuilder.construct(ReviewerTaskStatistics.class,
                root.get("reviewerUserId"),
                root.get("beginTime"),
                root.get("endTime"),
                criteriaBuilder.sum(root.get("reviewedAmount")),
                criteriaBuilder.sum(root.get("acceptedAmount"))));
        List<Predicate> predicateList = new ArrayList<>();
        predicateList.add(root.get("reviewerUserId").in(reviewerUserIds));
        if (beginTime >= 0) {
            predicateList.add(criteriaBuilder.ge(root.get("beginTime").as(Long.class), beginTime));
        }
        if (endTime >= 0) {
            predicateList.add(criteriaBuilder.le(root.get("endTime").as(Long.class), endTime));
        }
        Predicate[] predicates = new Predicate[predicateList.size()];
        criteriaQuery.where(predicateList.toArray(predicates));
        List<Expression<?>> groupList = new ArrayList<>();
        groupList.add(root.get("reviewerUserId"));
        criteriaQuery.groupBy(groupList);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
