package com.storm.common.util;

import java.lang.annotation.ElementType;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.session.SqlSessionFactory;

import com.storm.common.annotation.Component;
import com.storm.common.aspect.Aspect;
import com.storm.common.aspect.RepositoryAspect;
import com.storm.common.interceptor.SimpleAspectCglibInterceptor;
import com.storm.common.interceptor.SimpleAspectJDKInterceptor;


public class BeanFactory{
    /**
     * Bean对象容器
     */
    private static final Map<String, Object> beanContainer = new HashMap<String, Object>();

    private static Class currentAspectType = null;

    private static SqlSessionFactory sqlSessionFactory;

    /**
     * 初始化指定包下的所有@Service注解标记的类
     *
     * @param packageName 初始化包路径
     */
    public static void init(String packageName, Class aspectType, SqlSessionFactory factory) throws Exception {
        sqlSessionFactory = factory;
        currentAspectType = aspectType;
        Set<Class<?>> typesAnnotatedSet = (Set<Class<?>>) BeanUtils.scanAnnotationByPackageName(packageName, ElementType.TYPE);
        initBean(typesAnnotatedSet);
        Set<Method> methodsAnnotatedSet = (Set<Method>) BeanUtils.scanAnnotationByPackageName(packageName, ElementType.METHOD);
        initProxy(methodsAnnotatedSet);
    }

    private static void initBean(Set<Class<?>> beanSet) throws IllegalAccessException, InstantiationException {
        for (Class<?> c : beanSet) {
            Component annotation = c.getAnnotation(Component.class);
            Object bean = c.newInstance(); //得从Spring容器中获取对象 因为服务中注入了mapper 由Spring管理 暂时没有想到好的方式剥离
            //注解可能没有设置value 抛出异常
            if (annotation.value().equals("")) {
                //取类名首字母小写 部分配置 部分不配置 可能出现冲突 这里先简单处理
                throw new Error("被Component注解修饰的服务，需配置名称 Class: " + c.getName());
            }
            beanContainer.put(annotation.value(), bean);
        }
    }

    private static void initProxy(Set<Method> methodsAnnotatedSet) {
        for (Method method : methodsAnnotatedSet) {
            Class<?> declaringClass = method.getDeclaringClass();
            String beanName = getBeanName(declaringClass.getName());
            //在初始化的bean中获取注解修饰的方法所属类对象作为目标代理对象
            Object target = beanContainer.get(beanName);
            Object proxy = target;
            Aspect aspect = null;

            if (currentAspectType == null || currentAspectType == RepositoryAspect.class) {
                aspect = new RepositoryAspect(sqlSessionFactory.openSession(false));
            } else {
                //其他切面代理实现
            }
            try {
                //默认使用cglib代理
                SimpleAspectCglibInterceptor interceptor = new SimpleAspectCglibInterceptor(target, declaringClass, aspect);
                proxy = interceptor.getProxy();
            } catch (NoClassDefFoundError e) {
                //没有依赖cglib时使用jdk动态代理
                SimpleAspectJDKInterceptor interceptor = new SimpleAspectJDKInterceptor(target, aspect, declaringClass);
                proxy = Proxy.newProxyInstance(declaringClass.getClassLoader(), declaringClass.getInterfaces(), new SimpleAspectJDKInterceptor(target, aspect, declaringClass));
            }
            beanContainer.put(beanName, proxy);
        }
    }

    private static String getBeanName(String classFullName) {
        if (classFullName == null || classFullName.isEmpty()) {
            return classFullName;
        }
        String className = classFullName.substring(classFullName.lastIndexOf(".") + 1);
        char prefix = className.charAt(0);
        String suffix = className.substring(1);
        return String.valueOf(prefix).toLowerCase() + suffix;
    }

    /**
     * 根据注解名获取实例
     *
     * @param beanName 注解的名称
     * @return 对应实例
     */
    public static Object getBeanFromContainer(String beanName) {
        return beanContainer.get(beanName);
    }

    /**
     * 根据注解名获取指定类型的实例
     *
     * @param beanName  bean名称，注解指定的value值
     * @param beanClass bean类型
     * @return 指定类型的实例
     */
    public static <T> T getBean(String beanName, Class<T> beanClass) {
        return beanClass.cast(getBeanFromContainer(beanName));
    }

}