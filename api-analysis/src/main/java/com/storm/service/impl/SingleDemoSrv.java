package com.storm.service.impl;

import com.storm.common.annotation.Service;
import com.storm.common.aspect.CustomAspectAnnotation;
import com.storm.service.ISingleDemoSrv;

@Service("singleDemoSrv")
public class SingleDemoSrv implements ISingleDemoSrv {

    @Override
    @CustomAspectAnnotation
    public void doSomething() {
        System.out.println("doing something");
    }
}
