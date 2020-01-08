package com.storm.common.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Component
public @interface ApiMonitor {
    String apiName() default "";

    String time() default "";

}
