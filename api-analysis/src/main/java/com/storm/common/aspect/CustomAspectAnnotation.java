package com.storm.common.aspect;

import java.lang.annotation.*;


@Target({ElementType.FIELD, ElementType.METHOD}) //声明自定义的注解使用在方法上
@Retention(RetentionPolicy.RUNTIME)//注解不仅被保存到class文件中，jvm加载class文件之后，仍然存在
@Documented
public @interface CustomAspectAnnotation {
    String before() default "";

    String after() default "";
}
