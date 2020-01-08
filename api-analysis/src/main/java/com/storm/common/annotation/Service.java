package com.storm.common.annotation;

@Component
public @interface Service {

    String value() default "";

}
