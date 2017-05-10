package com.youmayon.lebang.util;

import org.springframework.util.Assert;

/**
 * 时间工具类
 * Created by Jawinton on 16/12/19.
 */
public class TimeUtil {
    private TimeUtil() {}

    /**
     * 两个时间戳相差的天数
     * @param firstTimestamp
     * @param secondTimestamp
     * @return
     */
    public static final long daysBetween(long firstTimestamp, long secondTimestamp, int timeZone) {
        Assert.isTrue(timeZone >= -12);
        Assert.isTrue(timeZone <= 12);
        long firstDays = (firstTimestamp + timeZone * 3600) / 86400;
        long secondDays = (secondTimestamp + timeZone * 3600)  / 86400;
        return secondDays - firstDays;
    }

    /**
     * 两个时间戳相差的天数，东8区
     * @param firstTimestamp
     * @param secondTimestamp
     * @return
     */
    public static final long daysBetween(long firstTimestamp, long secondTimestamp) {
        return daysBetween(firstTimestamp, secondTimestamp, 8);
    }

    /**
     * 获取天开始时间戳
     * @return
     */
    public static final long dayBeginTimestamp() {
        return dayBeginTimestamp(System.currentTimeMillis(), 8);
    }

    /**
     * 获取天开始时间戳
     * @param timestamp
     * @return
     */
    public static final long dayBeginTimestamp(long timestamp) {
        return dayBeginTimestamp(timestamp, 8);
    }

    /**
     * 获取天结束时间戳
     * @return
     */
    public static final long dayEndTimestamp() {
        return dayBeginTimestamp(System.currentTimeMillis(), 8) + 86400;
    }

    /**
     * 获取天结束时间戳
     * @param timestamp
     * @return
     */
    public static final long dayEndTimestamp(long timestamp) {
        return dayBeginTimestamp(timestamp, 8) + 86400;
    }

    /**
     * 获取天开始时间戳
     * @param timestamp
     * @param timeZone
     * @return
     */
    public static final long dayBeginTimestamp(long timestamp, int timeZone) {
        timestamp += timeZone * 3600;
        return (timestamp - timestamp % 86400) - timeZone * 3600;
    }
}
