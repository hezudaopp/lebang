package com.youmayon.lebang.web;

import com.youmayon.lebang.constant.LogicConstants;
import com.youmayon.lebang.domain.ReviewerTaskStatistics;
import com.youmayon.lebang.domain.TaskAppStatistics;
import com.youmayon.lebang.service.ReviewerTaskStatisticsService;
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

    @Autowired
    ReviewerTaskStatisticsService reviewerTaskStatisticsService;

    private long dayBeginTime;

    private long dayEndTime;

    private long monthBeginTime;

    private long monthEndTime;

    @RequestMapping(value = "/reviewer_task_statistics", method = RequestMethod.PUT)
    public List<List<ReviewerTaskStatistics>> generateReviewerTaskMonthlyStatistics(@RequestParam(value = "months", defaultValue = LogicConstants.DEFAULT_STATISTICS_MONTHS) int months) throws IllegalAccessException {
        long beginTime = TimeUtil.monthBeginTimestamp(0);
        List<List<ReviewerTaskStatistics>> response = new ArrayList<>();
        for (int i = 0; i < months; i++) {
            long endTime = beginTime;
            beginTime = TimeUtil.monthBeginTimestamp(-i-1);
            response.add(reviewerTaskStatisticsService.generateReviewerTaskStatistics(beginTime, endTime));
        }
        return response;
    }

    @RequestMapping(value = "/task_app_statistics", method = RequestMethod.PUT)
    public List<List<TaskAppStatistics>> generateTaskAppDailyStatistics(@RequestParam(value = "days", defaultValue = LogicConstants.DEFAULT_STATISTICS_DAYS) int days) throws IllegalAccessException {
        long beginTime = TimeUtil.dayEndTimestamp();
        List<List<TaskAppStatistics>> response = new ArrayList<>();
        for (int i = 0; i < days; i++) {
            long endTime = beginTime;
            beginTime = endTime - days * 86400L;
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
        this.setDayBeginTimeAndEndTime(days);
        return taskAppStatisticsService.list(this.dayBeginTime, this.dayEndTime, isDistinctTask, isDistinctApp);
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
        this.setDayBeginTimeAndEndTime(days);
        return taskAppStatisticsService.listTaskStatistics(taskId, this.dayBeginTime, this.dayEndTime, false, isDistinctApp);
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
        this.setDayBeginTimeAndEndTime(days);
        return taskAppStatisticsService.listAppStatistics(appId, this.dayBeginTime, this.dayEndTime, isDistinctTask, false);
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
        this.setDayBeginTimeAndEndTime(days);
        return taskAppStatisticsService.listTaskAppStatistics(taskId, appId, this.dayBeginTime, this.dayEndTime, false, false);
    }

    private void setDayBeginTimeAndEndTime(int days) {
        Assert.isTrue(days <= LogicConstants.MAX_STATISTICS_DAYS, "Days too large.");
        Assert.isTrue(days >= 0, "Days cannot be negative.");
        this.dayEndTime = TimeUtil.dayBeginTimestamp();
        this.dayBeginTime = this.dayEndTime - days * 86400L;
        if (this.dayBeginTime == this.dayEndTime) {
            this.dayBeginTime = -1L;
            this.dayEndTime = -1L;
        }
    }

    private void setMonthBeginTimeAndEndTime(int months) {
        Assert.isTrue(months <= LogicConstants.MAX_STATISTICS_MONTHS, "Months too large.");
        Assert.isTrue(months > 0, "Months cannot be negative.");
        this.monthEndTime = TimeUtil.monthBeginTimestamp(0);
        this.monthBeginTime = TimeUtil.monthBeginTimestamp(-months);
        if (this.monthBeginTime == this.monthEndTime) {
            this.monthBeginTime = -1L;
            this.monthEndTime = -1L;
        }
    }
}
