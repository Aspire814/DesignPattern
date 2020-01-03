package com.storm.common.interceptor;

import com.storm.common.aspect.Aspect;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class SimpleAspectCglibInterceptor implements MethodInterceptor {
    private Object target;//目标代理对象
    private Aspect aspect;
    private Class classz;

    public SimpleAspectCglibInterceptor(Object target, Class classz, Aspect aspect) {
        this.target = target;
        this.classz = classz;
        this.aspect = aspect;
    }

    public Object getProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(classz);
        //通过回调指定代理类。
        enhancer.setCallback(SimpleAspectCglibInterceptor.this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        aspect.before();
        Object ret = method.invoke(target, objects);
        aspect.after();
        return ret;
    }
}
