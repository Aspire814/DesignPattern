package com.storm.service;

import java.util.List;
import java.util.Map;

import com.storm.common.bo.RequestInfo;


public class RedisMetricsStorageSrv implements IMetricsStorageSrv {
    //...省略属性和构造函数等...
    @Override
    public void saveRequestInfo(RequestInfo requestInfo) {
        //...
    }

    @Override
    public List<RequestInfo> getRequestInfos(String apiName, long startTimestamp, long endTimestamp) {

        return null;
    }

    @Override
    public Map<String, List<RequestInfo>> getRequestInfos(long startTimestamp, long endTimestamp) {
        //...
        return null;
    }
}
