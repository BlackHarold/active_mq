package ru.opi.active_mq.constants;

import org.springframework.stereotype.Component;

@Component
public final class Headers {
    public static final String COOKIE = "Cookie";
    public static final String TARGET_SYSTEM_CODE = "targetSystemCode";
    public static final String TARGET_PROJECT_CODE = "targetProjectCode";
    public static final String ADAPTER_NAME = "adapterName";
    public static final String ADAPTER_VERSION = "adapterVersion";
    public static final String TRANSACTION_ID = "transactionId";
    public static final String AUTHORIZATION = "Authorization";
    public static final String SCENARIO_NAME = "scenarioName";
}