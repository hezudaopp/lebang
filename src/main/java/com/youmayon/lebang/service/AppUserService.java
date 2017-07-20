package com.youmayon.lebang.service;

import com.youmayon.lebang.domain.AppUser;
import com.youmayon.lebang.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * 渠道用户service类
 * Created by Jawinton on 17/05/02.
 */
public interface AppUserService {
    /**
     * 获取渠道用户详情
     * @param appUserId
     * @return
     */
    AppUser findByAppUserId(String appUserId);

    /**
     * 保存渠道用户
     * @param appUser
     * @return
     */
    AppUser save(AppUser appUser);
}
