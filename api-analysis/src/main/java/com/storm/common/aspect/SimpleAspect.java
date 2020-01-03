package com.storm.common.aspect;

import java.lang.annotation.Annotation;

public class SimpleAspect implements Aspect {


    @Override
    public void before() {
        System.out.println("doing before");
    }

    @Override
    public void after() {
        System.out.println("doing after");
    }
}
