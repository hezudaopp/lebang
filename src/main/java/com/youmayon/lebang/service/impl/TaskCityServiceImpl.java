package com.youmayon.lebang.service.impl;

import com.youmayon.lebang.data.TaskCityRepository;
import com.youmayon.lebang.data.TaskTypeRepository;
import com.youmayon.lebang.domain.TaskCity;
import com.youmayon.lebang.domain.TaskType;
import com.youmayon.lebang.domain.UserTask;
import com.youmayon.lebang.service.TaskCityService;
import com.youmayon.lebang.service.TaskTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * Created by Jawinton on 17/05/04.
 */
@Service
public class TaskCityServiceImpl implements TaskCityService {
    @Autowired
    TaskCityRepository taskCityRepository;

    @Override
    public List<TaskCity> list(long taskId) {
        return taskCityRepository.findByTaskId(taskId);
    }

    @Override
    public boolean containsCity(UserTask userTask) {
        List<TaskCity> taskCityList = list(userTask.getTaskId());
        for (TaskCity taskCity : taskCityList) {
            if (taskCity.getCityId() == null) {
                continue;
            }
            if (userTask.getCityId() == null) {
                continue;
            }
            if (taskCity.getCityId() == userTask.getCityId()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<TaskCity> save(Collection<TaskCity> taskCities) {
        return taskCityRepository.save(taskCities);
    }

    @Override
    public void delete(Collection<TaskCity> taskCities) {
        taskCityRepository.delete(taskCities);
    }
}
