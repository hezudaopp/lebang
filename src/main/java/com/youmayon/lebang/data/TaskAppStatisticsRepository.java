package com.youmayon.lebang.data;

import com.youmayon.lebang.domain.TaskAppStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created by Jawinton on 17/05/03.
 */
@Repository
public interface TaskAppStatisticsRepository extends JpaRepository<TaskAppStatistics, Long>, JpaSpecificationExecutor<TaskAppStatistics> {
}
