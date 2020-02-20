package com.storm.Singleton;

/**
 * 懒汉单例模式双重保证线程安全  或者直接粗暴加synchronized到方法上也可
 * 饿汉模式是先内创建对象，直接返回这个单一对象
 */
public class SafeLazyModel {
    private static volatile Object target = null;//内存可见

    public static Object getSingleInstance() {
        if (target != null) {
            return target;
        }
        synchronized (SafeLazyModel.class) {
            if (target == null) {
                target = new Object();
            }
        }
        return target;
    }
}
