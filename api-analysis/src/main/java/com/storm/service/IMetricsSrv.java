package com.storm.service;

import java.util.concurrent.TimeUnit;

public interface IMetricsSrv {
    void recordResponseTime(String apiName, double responseTime);

    void recordTimestamp(String apiName, double timestamp);

    void startRepeatedReport(long period, TimeUnit unit);
}
