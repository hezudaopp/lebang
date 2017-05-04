package com.youmayon.lebang.service.impl;

import com.youmayon.lebang.data.UserTaskLogRepository;
import com.youmayon.lebang.domain.UserTaskLog;
import com.youmayon.lebang.service.UserTaskLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Jawinton on 17/05/04.
 */
@Service
public class UserTaskLogServiceImpl implements UserTaskLogService {
    @Autowired
    UserTaskLogRepository userTaskLogRepository;

    @Override
    public UserTaskLog save(UserTaskLog userTaskLog) {
        return userTaskLogRepository.save(userTaskLog);
    }
}
