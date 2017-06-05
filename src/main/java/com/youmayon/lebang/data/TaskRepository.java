package com.youmayon.lebang.data;

import com.youmayon.lebang.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created by Jawinton on 17/05/03.
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Long>, JpaSpecificationExecutor<Task>, TaskDao {
    Task findFirstByName(String name);
}
