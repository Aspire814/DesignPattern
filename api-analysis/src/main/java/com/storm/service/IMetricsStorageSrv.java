package com.storm.service;

import java.util.List;
import java.util.Map;

import com.storm.common.bo.RequestInfo;

public interface IMetricsStorageSrv {
    void saveRequestInfo(RequestInfo requestInfo);

    List<RequestInfo> getRequestInfos(String apiName, long startTimeInMillis, long endTimeInMillis);

    Map<String, List<RequestInfo>> getRequestInfos(long startTimeInMillis, long endTimeInMillis);
}
