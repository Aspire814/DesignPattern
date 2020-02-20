package com.storm.Singleton;

/**
 * 内部类方式实现单例  也可以直接使用枚举
 */
public class InnerClassModel {
    private static class SingletonHolder {
        private static final InnerClassModel INSTANCE = new InnerClassModel();
    }

    private InnerClassModel() {
    }

    public static InnerClassModel getInstance() {
        return SingletonHolder.INSTANCE;
    }
}


