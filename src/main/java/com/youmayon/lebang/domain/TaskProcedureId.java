package com.youmayon.lebang.domain;

import com.youmayon.lebang.constant.LogicConstants;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Max;
import java.io.Serializable;

/**
 * Created by jawinton on 03/08/2017.
 * task procedure primary key.
 */
@Embeddable
public class TaskProcedureId implements Serializable {
    @Column(columnDefinition = "BIGINT(20) UNSIGNED COMMENT '任务id'")
    private Long taskId;

    @Max(LogicConstants.TASK_MAX_PROCEDURE_NUM)
    @Column(columnDefinition = "TINYINT(1) UNSIGNED COMMENT '顺序'")
    private Integer procedureOrder;

    public TaskProcedureId() {}

    public TaskProcedureId(Long taskId, Integer procedureOrder) {
        this.taskId = taskId;
        this.procedureOrder = procedureOrder;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Integer getProcedureOrder() {
        return procedureOrder;
    }

    public void setProcedureOrder(Integer procedureOrder) {
        this.procedureOrder = procedureOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaskProcedureId that = (TaskProcedureId) o;

        if (taskId != null ? !taskId.equals(that.taskId) : that.taskId != null) return false;
        return procedureOrder != null ? procedureOrder.equals(that.procedureOrder) : that.procedureOrder == null;
    }

    @Override
    public int hashCode() {
        int result = taskId != null ? taskId.hashCode() : 0;
        result = 31 * result + (procedureOrder != null ? procedureOrder.hashCode() : 0);
        return result;
    }
}
