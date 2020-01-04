package com.storm.common.interceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import com.storm.common.aspect.Aspect;

public class SimpleAspectJDKInterceptor implements InvocationHandler {
    private Object target;//目标代理对象
    private Aspect aspect;
    private Class classz;

    public SimpleAspectJDKInterceptor() {
    }

    public SimpleAspectJDKInterceptor(Object target, Aspect aspect, Class classz) {
        this.target = target;
        this.aspect = aspect;
        this.classz = classz;
    }

    public Object getTarget() {
        return target;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        final Object target = this.target;
        final Aspect aspect = this.aspect;
        aspect.before(target, method, args);
        Object ret = method.invoke(Modifier.isStatic(method.getModifiers()) ? null : target, args);
        aspect.after(target, method, args);
        return ret;
    }

}
