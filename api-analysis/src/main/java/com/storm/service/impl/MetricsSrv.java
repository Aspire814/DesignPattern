package com.storm.service.impl;

import com.storm.common.annotation.Service;
import com.storm.common.aspect.CustomAspectAnnotation;
import com.storm.service.IMetricsSrv;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 接口调用统计分析服务
 */
@Service("metricsSrv")
public class MetricsSrv implements IMetricsSrv {
    // Map的key是接口名称，value对应接口请求的响应时间或时间戳；
    private Map<String, List<Double>> timestamps = new HashMap<>();
    private Map<String, List<Double>> responseTimes = new HashMap<>();
    private ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

    @CustomAspectAnnotation
    public void recordResponseTime(String apiName, double responseTime) {
        System.out.println("recordResponseTime:" + apiName + responseTime);
    }

    @CustomAspectAnnotation
    public void recordTimestamp(String apiName, double timestamp) {
        System.out.println("recordTimestamp:" + apiName + timestamp);
    }

    public void startRepeatedReport(long period, TimeUnit unit) {
        executor.execute(() -> {
            // TODO
        });
    }
}