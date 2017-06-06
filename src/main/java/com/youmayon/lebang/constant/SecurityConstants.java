package com.youmayon.lebang.constant;

/**
 * 配置常量
 * Created by Jawinton on 16/12/24.
 */
public class SecurityConstants {
    // password secret
    public static final String PASSWORD_SECRET = "fbc73e20-8f1e-4746-9081-eb135bd5562f";

    // default password
    public static final String DEFAULT_PASSWORD = "968a03c5a93c4eb5f2a8a410f8594cc9";

    // security constants
    public static final String REALM = "com.youmayon.lebang";
    public static final String RESOURCE_ID = "lebang_rest_resource";

    // oauth client constants
    public static final String APP_AUTHORIZED_GRANT_TYPES = "client_credentials";
    public static final String APP_SCOPE = "trust";
    public static final int APP_ACCESS_TOKEN_VALIDITY = 7200;
}
