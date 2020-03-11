package com.storm.struct_model.decorator;

public class AnotherDecorator extends DecoratorBase {
    private DecoratorBase decoratorBase;

    @Override
    public void fun() {
        decoratorBase.fun();
        fun2();
    }

    public void fun2() {
        System.out.println("anotherDecorator.fun2");
    }

    public DecoratorBase getDecoratorBase() {
        return decoratorBase;
    }

    public void setDecoratorBase(DecoratorBase decoratorBase) {
        this.decoratorBase = decoratorBase;
    }

    public AnotherDecorator(DecoratorBase decoratorBase) {
        super();
        this.decoratorBase = decoratorBase;
    }

    public AnotherDecorator() {
        super();
    }

}
