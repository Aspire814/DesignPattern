package com.storm.create_model.bulider;

/**
 * 如果一个类中有很多属性，为了避免构造函数的参数列表过长，影响代码的可读性和易用性，我们可以通过构造函数配合 set() 方法来解决。
 * 但是，如果存在下面情况中的任意一种，我们就要考虑使用建造者模式了。我们把类的必填属性放到构造函数中，强制创建对象的时候就设置。
 * 如果必填的属性有很多，把这些必填属性都放到构造函数中设置，那构造函数就又会出现参数列表很长的问题。
 * 如果我们把必填属性通过 set() 方法设置，那校验这些必填属性是否已经填写的逻辑就无处安放了。
 * 如果类的属性之间有一定的依赖关系或者约束条件，我们继续使用构造函数配合 set() 方法的设计思路，那这些依赖关系或约束条件的校验逻辑就无处安放了。
 * 如果我们希望创建不可变对象，也就是说，对象在创建好之后，就不能再修改内部的属性值，要实现这个功能，我们就不能在类中暴露 set() 方法。
 * 构造函数配合 set() 方法来设置属性值的方式就不适用了。除此之外，在今天的讲解中，我们还对比了工厂模式和建造者模式的区别。
 * 工厂模式是用来创建不同但是相关类型的对象（继承同一父类或者接口的一组子类），由给定的参数来决定创建哪种类型的对象。
 * 建造者模式是用来创建一种类型的复杂对象，可以通过设置不同的可选参数，“定制化”地创建不同的对象。
 */
public class ConstructorArg {
    private boolean isRef;
    private Class type;
    private Object arg;

    public boolean isRef() {
        return isRef;
    }

    public Class getType() {
        return type;
    }

    public Object getArg() {
        return arg;
    }

    private ConstructorArg(Builder builder) {
        this.isRef = builder.isRef;
        this.type = builder.type;
        this.arg = builder.arg;
    }

    public static class Builder {
        private boolean isRef;
        private Class type;
        private Object arg;

        public ConstructorArg build() {
            if (isRef && type != null) {
                throw new IllegalArgumentException("...");
            }

            if (!isRef && type == null) {
                throw new IllegalArgumentException("...");
            }

            if (this.isRef && (arg != null && arg.getClass() != String.class)) {
                throw new IllegalArgumentException("...");
            }

            if (!this.isRef && arg == null) {
                throw new IllegalArgumentException("...");
            }

            return new ConstructorArg(this);
        }

        public Builder setRef(boolean ref) {
            if (ref && this.type != null) {
                throw new IllegalArgumentException("...");
            }
            this.isRef = ref;
            return this;
        }

        public Builder setType(Class type) {
            if (this.isRef || type == null) {
                throw new IllegalArgumentException("...");
            }
            this.type = type;
            return this;
        }

        public Builder setArg(Object arg) {
            if (this.isRef && (arg != null && arg.getClass() != String.class)) {
                throw new IllegalArgumentException("...");
            }

            if (!this.isRef && arg == null) {
                throw new IllegalArgumentException("...");
            }
            this.arg = arg;
            return this;
        }
    }
}