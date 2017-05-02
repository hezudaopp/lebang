package com.youmayon.lebang.enums;

/**
 * 用户状态
 * Created by Jawinton on 17/1/3.
 */
public enum UserStatus {
    DISABLED(0, "禁用"),
    NORMAL(1, "正常");

    private final Integer value;
    private final String desc;

    UserStatus(Integer value, String desc) {
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
        UserStatus userStatuses[] = UserStatus.values();
        int values[] = new int[userStatuses.length];
        for (int i = 0; i < userStatuses.length; i++) {
            values[i] = userStatuses[i].value();
        }
        return values;
    }

    public static boolean contains(int value) {
        return UserStatus.valueOf(value) != null;
    }
    
    public static UserStatus valueOf(int value) {
        UserStatus userStatuses[] = UserStatus.values();
        for (UserStatus userStatus : userStatuses) {
            if (userStatus.value() == value)
                return userStatus;
        }
        return null;
    }
}
