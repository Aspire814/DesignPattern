package com.storm.common.aspect;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 接口调用统计分析服务
 */
public class ApiMonitorAspect implements Aspect {
    // Map的key是接口名称，value对应接口请求的响应时间或时间戳；
    private long startTime = 0;
    private Map<String, List<Double>> timestamps = new HashMap<>();
    private Map<String, List<Double>> responseTimes = new HashMap<>();
    private ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

    public void recordResponseTime(String apiName, long responseTime) {
        System.out.println("/" + apiName + "接口调用结束  耗时：" + responseTime);
    }

    public void recordTimestamp(String apiName, long timestamp) {
        this.startTime = timestamp;
        System.out.println("/" + apiName + " 接口被调用 当前时间戳为：" + timestamp);
    }

    public void startRepeatedReport(long period, TimeUnit unit) {
        executor.execute(() -> {
            // TODO 这里是统计相关逻辑

        });
    }

    @Override
    public void before(Object target, Method method, Object[] args) {
        recordTimestamp(method.getName(), System.currentTimeMillis());
    }

    @Override
    public void after(Object target, Method method, Object[] args) {
        long responseTime = System.currentTimeMillis() - startTime;
        recordResponseTime(method.getName(), responseTime);
    }
}