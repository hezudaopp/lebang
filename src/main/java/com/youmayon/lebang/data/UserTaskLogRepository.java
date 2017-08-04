package com.youmayon.lebang.data;

import com.youmayon.lebang.domain.UserTaskLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Jawinton on 17/05/04.
 */
@Repository
public interface UserTaskLogRepository extends JpaRepository<UserTaskLog, Long> {
    List<UserTaskLog> findByUserTaskId(long userTaskId);
}
