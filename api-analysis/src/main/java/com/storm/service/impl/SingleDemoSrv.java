package com.storm.service.impl;

import com.storm.common.annotation.ApiMonitor;
import com.storm.common.annotation.Service;
import com.storm.service.ISingleDemoSrv;

@Service("singleDemoSrv")
public class SingleDemoSrv implements ISingleDemoSrv {

    @Override
    @ApiMonitor
    public void doSomething() {
        try {
            System.out.println("doSomething执行中。。。");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
