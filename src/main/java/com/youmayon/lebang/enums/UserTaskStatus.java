package com.youmayon.lebang.enums;

/**
 * 用户任务状态
 * Created by Jawinton on 17/1/3.
 */
public enum UserTaskStatus {
    ONGOING(0, "做任务中"),
    COMPLETED(1, "任务完成，待审核"),
    ACCEPTED(2, "任务通过"),
    REJECTED(3, "任务不通过"),
    REDOING(4, "任务重做中"),
    REWARDED(5, "任务奖励派发完成");

    private final Integer value;
    private final String desc;

    UserTaskStatus(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public Integer value() {
        return this.value;
    }

    public String getDesc() {
        return this.desc;
    }

    public static int[] getAllValues() {
        UserTaskStatus userStatuses[] = UserTaskStatus.values();
        int values[] = new int[userStatuses.length];
        for (int i = 0; i < userStatuses.length; i++) {
            values[i] = userStatuses[i].value();
        }
        return values;
    }

    public static boolean contains(int value) {
        return UserTaskStatus.valueOf(value) != null;
    }
    
    public static UserTaskStatus valueOf(int value) {
        UserTaskStatus userStatuses[] = UserTaskStatus.values();
        for (UserTaskStatus userStatus : userStatuses) {
            if (userStatus.value() == value)
                return userStatus;
        }
        return null;
    }
}
