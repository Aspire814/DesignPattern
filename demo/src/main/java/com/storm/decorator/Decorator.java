package com.storm.decorator;

public class Decorator extends DecoratorBase {

	private DecoratorBase decoratorBase;

	@Override
	public void fun() {
		decoratorBase.fun();
		fun2();
	}

	public void fun2() {
		System.out.println("decorator.fun2");

	}

	public DecoratorBase getDecoratorBase() {
		return decoratorBase;
	}

	public void setDecoratorBase(DecoratorBase decoratorBase) {
		this.decoratorBase = decoratorBase;
	}

	public Decorator() {
		super();
	}

	public Decorator(DecoratorBase decoratorBase) {
		super();
		this.decoratorBase = decoratorBase;
	}

}
