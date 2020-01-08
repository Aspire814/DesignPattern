package com.storm.common.annotation;

import org.apache.ibatis.session.SqlSessionFactory;

@Component
public @interface Transactional {

    String value() default "";

}
