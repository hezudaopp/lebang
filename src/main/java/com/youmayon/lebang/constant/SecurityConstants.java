package com.youmayon.lebang.constant;

import com.youmayon.lebang.domain.OAuth2Client;

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

    // authenticated clients
    public static final OAuth2Client[] O_AUTH2_CLIENTS = {
        new OAuth2Client(
                "lebang_client",
                "lnpOeaUQrmQj7r9a6f94ltjCuzqY7jEvO",
                new String[]{"password", "refresh_token"},
                new String[]{"ROLE_CLIENT"},
                new String[]{"read", "write"},
                7200,
                86400 * 30)
    };
}
