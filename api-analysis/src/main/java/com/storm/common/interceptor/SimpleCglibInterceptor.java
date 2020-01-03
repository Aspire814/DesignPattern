package com.storm.common.interceptor;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

//cglib的方法执行拦截器
public class SimpleCglibInterceptor implements MethodInterceptor {
    private Object target;//目标代理对象
    private Class classz;//代理对象类

    public SimpleCglibInterceptor(Object target, Class classz) {
        this.target = target;
        this.classz = classz;
    }

    /**
     * 获取代理执行对象
     * @return 代理执行对象
     */
    public Object getProxy(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(classz);
        //通过回调指定代理类。
        enhancer.setCallback(SimpleCglibInterceptor.this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("前置通知");
        Object e = method.invoke(target, objects);
        System.out.println("后置通知");
        return e;
    }
}
