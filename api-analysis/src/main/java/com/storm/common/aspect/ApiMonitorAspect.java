package com.storm.common.aspect;

import java.lang.reflect.Method;
import java.util.ArrayList;
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
    private Map<String, List<Long>> timestamps = new HashMap<>();
    private Map<String, List<Long>> responseTimes = new HashMap<>();
    private ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

    public void recordResponseTime(String apiName, long responseTime) {
        System.out.println("/" + apiName + "接口调用结束  耗时：" + responseTime);
        responseTimes.putIfAbsent(apiName, new ArrayList<>());
        responseTimes.get(apiName).add(responseTime);
    }

    public void recordTimestamp(String apiName, long timestamp) {
        this.startTime = timestamp;
        System.out.println("/" + apiName + " 接口被调用 当前时间戳为：" + timestamp);
        timestamps.putIfAbsent(apiName, new ArrayList<>());
        timestamps.get(apiName).add(timestamp);
    }

    public void startRepeatedReport(long period, TimeUnit unit) {
        executor.scheduleAtFixedRate(() -> {
            // TODO 这里是统计相关逻辑
            Map<String, Map<String, Double>> stats = new HashMap<>();
            for (Map.Entry<String, List<Long>> entry : responseTimes.entrySet()) {
                String apiName = entry.getKey();
                List<Long> apiRespTimes = entry.getValue();
                stats.putIfAbsent(apiName, new HashMap<>());
                stats.get(apiName).put("max", max(apiRespTimes));
                stats.get(apiName).put("avg", avg(apiRespTimes));
            }

            for (Map.Entry<String, List<Long>> entry : timestamps.entrySet()) {
                String apiName = entry.getKey();
                List<Long> apiTimestamps = entry.getValue();
                stats.putIfAbsent(apiName, new HashMap<>());
                stats.get(apiName).put("count", (double) apiTimestamps.size());
            }
        }, 0, 1, TimeUnit.SECONDS);
    }

    private double max(List<Long> dataset) {
        double max = 0;
        for (Long num : dataset) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }

    private double avg(List<Long> dataset) {
        if (dataset.size() == 0) {
            return 0;
        }
        double sum = 0;
        for (Long num : dataset) {
            sum += sum;
        }
        return sum / dataset.size();
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