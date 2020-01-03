package com.storm.service.impl;

import com.storm.service.ISingleDemoSrv;

public class SingleDemoSrv implements ISingleDemoSrv {
    @Override
    public void doSomething() {
        System.out.println("doing something");
    }
}
