package com.youmayon.lebang.data;

import com.youmayon.lebang.domain.TaskProcedure;
import com.youmayon.lebang.domain.TaskType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Jawinton on 17/05/05.
 */
@Repository
public interface TaskProcedureRepository extends JpaRepository<TaskProcedure, Long> {
    @Query("from TaskProcedure tp where tp.taskProcedureId.taskId=:taskId order by tp.taskProcedureId.procedureOrder")
    List<TaskProcedure> findByTaskIdOrderByProcedureOrderAsc(@Param("taskId") long taskId);
}
