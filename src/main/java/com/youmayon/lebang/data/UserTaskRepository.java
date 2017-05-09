package com.youmayon.lebang.data;

import com.youmayon.lebang.domain.UserTask;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Jawinton on 17/05/04.
 */
@Repository
public interface UserTaskRepository extends JpaRepository<UserTask, Long> {
    UserTask findFirstByAppIdAndAppUserIdAndTaskIdOrderByCreatedTimeDesc(String appId, String appUserId, long taskId);
    int countByAppIdAndAppUserIdAndTaskId(String appId, String appUserId, long taskId);
}
