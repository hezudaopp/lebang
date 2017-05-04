package com.youmayon.lebang.service;

import com.youmayon.lebang.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * 用户service类
 * Created by Jawinton on 17/05/02.
 */
public interface UserService extends UserDetailsService {
    /**
     * 获取用户详情
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * 获取用户详情
     * @param id
     * @return
     */
    User findOne(long id);

    /**
     * 保存用户
     * @param user
     * @return
     */
    User save(User user);

    /**
     * 分页获取用户列表
     * @param role 角色
     * @param status
     * @param page
     * @param size
     * @return
     */
    Page<User> list(String role, int status, int page, int size);

    /**
     * 判断用户是否存在
     * @param username
     * @return
     */
    boolean isUsernameExists(String username);
}
