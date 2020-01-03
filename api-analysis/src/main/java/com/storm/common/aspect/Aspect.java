package com.storm.common.aspect;

import java.lang.annotation.*;

/**
 * 自定义切面
 */

public interface Aspect {
    void before();

    void after();
}
