package com.storm.create_model.factory;

/**
 * 工厂方法
 * 简单工厂模式，并没有直接解决if else 创建对象的扩展性问题，因为新增或者删除对象类型需要改动代码
 * 工厂方法模式，工厂基于接口扩展 新增类型的工厂直接实现接口而达到扩展，但是在上层调用工厂生产对象时仍然存在if else创建目标工厂的问题，此时可以引入超级工厂，隐蔽此逻辑来创建工厂
 * 或者直接使用缓存来达到目的
 */
public class FactoryMethod {
}
