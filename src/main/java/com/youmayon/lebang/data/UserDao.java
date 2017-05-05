package com.youmayon.lebang.data;

import com.youmayon.lebang.domain.User;

/**
 * Created by Jawinton on 16/12/21.
 */
public interface UserDao {
    User findOneRandomUser(String role, int status);
}
