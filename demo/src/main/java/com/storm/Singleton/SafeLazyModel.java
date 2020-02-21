package com.storm.Singleton;

/**
 * 懒汉单例模式双重保证线程安全  或者直接粗暴加synchronized到方法上也可
 * 饿汉模式是先内创建对象，直接返回这个单一对象
 */
public class SafeLazyModel {
    /*
        网上有人说，这种实现方式有些问题。因为指令重排序，可能会导致 IdGenerator 对象被 new 出来，并且赋值给 instance 之后，
        还没来得及初始化（执行构造函数中的代码逻辑），就被另一个线程使用了。
        要解决这个问题，我们需要给 instance 成员变量加上 volatile 关键字，禁止指令重排序才行。
        实际上，只有很低版本的 Java 才会有这个问题。
        我们现在用的高版本的 Java 已经在 JDK 内部实现中解决了这个问题（解决的方法很简单，只要把对象 new 操作和初始化操作设计为原子操作，就自然能禁止重排序）。
     */
    private static volatile Object target = null;

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
