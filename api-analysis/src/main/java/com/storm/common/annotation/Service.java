package com.storm.common.annotation;

import java.lang.annotation.*;

@Target({ElementType.TYPE})//用于类上的注解
@Retention(RetentionPolicy.RUNTIME)//注解不仅被保存到class文件中，jvm加载class文件之后，仍然存在
@Documented
public @interface Service {

    String value() default "";

}
