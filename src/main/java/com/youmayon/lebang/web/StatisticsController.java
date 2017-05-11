package com.youmayon.lebang.web;

import com.youmayon.lebang.constant.LogicConstants;
import com.youmayon.lebang.domain.TaskAppStatistics;
import com.youmayon.lebang.service.TaskAppStatisticsService;
import com.youmayon.lebang.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jawinton on 17/05/03.
 */
@RestController
@RequestMapping("/statistics")
public class StatisticsController extends BaseController {
    @Autowired
    TaskAppStatisticsService taskAppStatisticsService;

    private long beginTime;

    private long endTime;

    @RequestMapping(value = "/task_app_statistics", method = RequestMethod.PUT)
    public List<List<TaskAppStatistics>> put(@RequestParam(value = "days", defaultValue = LogicConstants.DEFAULT_STATISTICS_DAYS) int days) throws IllegalAccessException {
        this.setBeginTimeAndEndTime(days);
        List<List<TaskAppStatistics>> response = new ArrayList<>();
        for (long curTime = this.endTime; curTime >= this.beginTime; curTime -= 86400L) {
            long endTime = curTime;
            long beginTime = endTime - 86400L;
            response.add(taskAppStatisticsService.generateTaskAppStatistics(beginTime, endTime));
        }
        return response;
    }

    /**
     * 按天获取总统计信息
     * @param days
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<TaskAppStatistics> get(
            @RequestParam(value = "days", defaultValue = LogicConstants.DEFAULT_STATISTICS_DAYS) int days,
            @RequestParam(value = "isDistinctTask", defaultValue = LogicConstants.FALSE) boolean isDistinctTask,
            @RequestParam(value = "isDistinctApp", defaultValue = LogicConstants.FALSE) boolean isDistinctApp) {
        this.setBeginTimeAndEndTime(days);
        return taskAppStatisticsService.list(this.beginTime, this.endTime, isDistinctTask, isDistinctApp);
    }

    /**
     * 按天获取任务统计信息
     * @param taskId
     * @param days
     * @return
     */
    @RequestMapping(value = "/tasks/{taskId}", method = RequestMethod.GET)
    public List<TaskAppStatistics> getTaskStatistics(
            @PathVariable long taskId,
            @RequestParam(value = "days", defaultValue = LogicConstants.DEFAULT_STATISTICS_DAYS) int days,
            @RequestParam(value = "isDistinctApp", defaultValue = LogicConstants.FALSE) boolean isDistinctApp) {
        this.setBeginTimeAndEndTime(days);
        return taskAppStatisticsService.listTaskStatistics(taskId, this.beginTime, this.endTime, false, isDistinctApp);
    }

    /**
     * 按天获取渠道统计信息
     * @param appId
     * @param days
     * @return
     */
    @RequestMapping(value = "/apps/{appId}", method = RequestMethod.GET)
    public List<TaskAppStatistics> getAppStatistics(
            @PathVariable long appId,
            @RequestParam(value = "days", defaultValue = LogicConstants.DEFAULT_STATISTICS_DAYS) int days,
            @RequestParam(value = "isDistinctTask", defaultValue = LogicConstants.FALSE) boolean isDistinctTask) {
        this.setBeginTimeAndEndTime(days);
        return taskAppStatisticsService.listAppStatistics(appId, this.beginTime, this.endTime, isDistinctTask, false);
    }

    /**
     * 按天获取任务渠道统计信息
     * @param taskId
     * @param appId
     * @param days
     * @return
     */
    @RequestMapping(value = "/tasks/{taskId}/apps/{appId}", method = RequestMethod.GET)
    public List<TaskAppStatistics> getAppStatistics(
            @PathVariable long taskId,
            @PathVariable long appId,
            @RequestParam(value = "days", defaultValue = LogicConstants.DEFAULT_STATISTICS_DAYS) int days) {
        this.setBeginTimeAndEndTime(days);
        return taskAppStatisticsService.listTaskAppStatistics(taskId, appId, this.beginTime, this.endTime, false, false);
    }

    private void setBeginTimeAndEndTime(int days) {
        Assert.isTrue(days <= LogicConstants.MAX_STATISTICS_DAYS, "Days too large.");
        this.endTime = TimeUtil.dayBeginTimestamp();
        this.beginTime = this.endTime - days * 86400;
    }
}
