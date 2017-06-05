package com.youmayon.lebang.service.impl;

import com.youmayon.lebang.data.AppRepository;
import com.youmayon.lebang.domain.App;
import com.youmayon.lebang.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Jawinton on 17/05/02.
 */
@Service
public class AppServiceImpl implements AppService {
    @Autowired
    AppRepository appRepository;

    @Override
    public App findOne(long id) {
        return appRepository.findOne(id);
    }

    @Override
    public App save(App app) {
        return appRepository.save(app);
    }

    @Override
    public Page<App> list(int page, int size) {
        return appRepository.findAll(new PageRequest(page, size));
    }

    @Override
    public List<App> list(boolean enabled) {
        return appRepository.findByEnabled(enabled);
    }

    @Override
    public App findOne(String name) {
        return appRepository.findFirstByName(name);
    }
}
