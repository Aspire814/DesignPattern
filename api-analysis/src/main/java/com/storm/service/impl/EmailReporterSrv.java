package com.storm.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import com.storm.common.bo.RequestInfo;
import com.storm.common.bo.RequestStat;
import com.storm.common.util.Aggregator;
import com.storm.common.util.EmailSender;
import com.storm.service.IMetricsStorageSrv;

public class EmailReporterSrv {
    private static final Long DAY_HOURS_IN_SECONDS = 86400L;

    private IMetricsStorageSrv metricsStorageSrv;
    private EmailSender emailSender;
    private List<String> toAddresses = new ArrayList<>();

    public EmailReporterSrv(IMetricsStorageSrv metricsStorageSrv) {
        this(metricsStorageSrv, new EmailSender(/*省略参数*/));
    }

    public EmailReporterSrv(IMetricsStorageSrv metricsStorageSrv, EmailSender emailSender) {
        this.metricsStorageSrv = metricsStorageSrv;
        this.emailSender = emailSender;
    }

    public void addToAddress(String address) {
        toAddresses.add(address);
    }

    public void startDailyReport() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date firstTime = calendar.getTime();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                long durationInMillis = DAY_HOURS_IN_SECONDS * 1000;
                long endTimeInMillis = System.currentTimeMillis();
                long startTimeInMillis = endTimeInMillis - durationInMillis;
                Map<String, List<RequestInfo>> requestInfos =
                        metricsStorageSrv.getRequestInfos(startTimeInMillis, endTimeInMillis);
                Map<String, RequestStat> stats = new HashMap<>();
                for (Map.Entry<String, List<RequestInfo>> entry : requestInfos.entrySet()) {
                    String apiName = entry.getKey();
                    List<RequestInfo> requestInfosPerApi = entry.getValue();
                    RequestStat requestStat = Aggregator.aggregate(requestInfosPerApi, durationInMillis);
                    stats.put(apiName, requestStat);
                }
                // TODO: 格式化为html格式，并且发送邮件
            }
        }, firstTime, DAY_HOURS_IN_SECONDS * 1000);
    }
}