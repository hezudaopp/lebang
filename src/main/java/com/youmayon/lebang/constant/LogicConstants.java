package com.youmayon.lebang.constant;

/**
 * 业务逻辑需要用到的常量
 * Created by Jawinton on 16/12/24.
 */
public class LogicConstants {
    // query constants
    public static final String EMPTY_STRING = "";
    public static final String NEGATIVE_ONE = "-1";
    public static final String DEFAULT_PAGE = "0";
    public static final String DEFAULT_SIZE = "10";

    // 设备最大掩码值
    public static final int ALL_DEVICE_TYPES_MASK = 127;

    // 任务最大步骤数
    public static final int TASK_MAX_PROCEDURE_NUM = 5;

    // app
    public static final int APP_SECRET_LEN = 32;

    // statistic
    public static final String DEFAULT_STATISTICS_DAYS = "7";
    public static final String DEFAULT_STATISTICS_MONTHS = "3";
    public static final int MAX_STATISTICS_DAYS = 30;
    public static final int MAX_STATISTICS_MONTHS = 12;

    // true/false string
    public static final String FALSE = "false";
    public static final String TRUE = "true";
}
