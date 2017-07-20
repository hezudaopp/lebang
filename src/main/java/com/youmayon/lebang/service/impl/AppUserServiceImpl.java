package com.youmayon.lebang.service.impl;

import com.youmayon.lebang.data.AppUserRepository;
import com.youmayon.lebang.domain.AppUser;
import com.youmayon.lebang.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Jawinton on 17/05/03.
 */
@Service
public class AppUserServiceImpl implements AppUserService {
    @Autowired
    AppUserRepository appUserRepository;

    @Override
    public AppUser findByAppUserId(String appUserId) {
        return appUserRepository.findFirstByAppUserId(appUserId);
    }

    @Override
    public AppUser save(AppUser appUser) {
        return appUserRepository.save(appUser);
    }
}
