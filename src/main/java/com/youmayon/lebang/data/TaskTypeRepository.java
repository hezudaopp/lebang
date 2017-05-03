package com.youmayon.lebang.data;

import com.youmayon.lebang.domain.TaskType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Jawinton on 17/05/03.
 */
@Repository
public interface TaskTypeRepository extends JpaRepository<TaskType, Long> {
    List<TaskType> findByEnabled(boolean enabled);
}
