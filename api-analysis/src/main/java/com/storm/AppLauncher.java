package com.storm;

import com.storm.common.aspect.SimpleAspect;
import com.storm.common.interceptor.SimpleAspectCglibInterceptor;
import com.storm.service.IMetricsSrv;
import com.storm.service.ISingleDemoSrv;
import com.storm.service.impl.MetricsSrv;

public class AppLauncher {
    public static void main(String[] args) {
        IMetricsSrv metricsSrv = new MetricsSrv();
        SimpleAspect aspect = new SimpleAspect();
        SimpleAspectCglibInterceptor interceptor = new SimpleAspectCglibInterceptor(metricsSrv, IMetricsSrv.class,aspect);
        IMetricsSrv proxy = (IMetricsSrv) interceptor.getProxy();
        proxy.recordResponseTime("/test", 1);
        proxy.recordTimestamp("/test",100);
    }
}
