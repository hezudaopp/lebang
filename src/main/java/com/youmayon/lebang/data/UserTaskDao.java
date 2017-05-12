package com.youmayon.lebang.data;

import com.youmayon.lebang.domain.TaskAppStatistics;

import java.util.List;

/**
 * Created by Jawinton on 17/05/04.
 */
public interface UserTaskDao {
    List<TaskAppStatistics> taskAppStatistics(long beginTime, long endTime);
}
