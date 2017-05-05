package com.youmayon.lebang.data.impl;

import com.youmayon.lebang.data.UserDao;
import com.youmayon.lebang.domain.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 * Created by Jawinton on 16/12/21.
 */
public class UserRepositoryImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User findOneRandomUser(String role, int status) {
        final String sql = "SELECT u FROM User u WHERE u.id >= ((SELECT MAX(u.id) FROM User u)-(SELECT MIN(u.id) FROM User u)) * RAND() + (SELECT MIN(u.id) FROM User u) AND u.role = '" + role + "' AND u.status = '" + status + "'";
        try {
            return (User) entityManager.createQuery(sql).setMaxResults(1).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
