package com.kotlin.practice.classandobject

/**
 *
 * @author shhe
 * @Date 2020/10/21 下午7:55
 * @Description:
 */
class Interfaces {

    /**
     * Kotlin 的接口非常类似于 Java8。可以包含抽象方法的声明以及方法的实现。
     * 接口与抽象类的区别是不能存储状态。接口可以有属性，但是必须声明为抽象类型或者实现了访问器。
     */
    interface MyInterface {
        fun bar()
        fun foo() {
            // optional body
        }
    }

    class Child : MyInterface {
        override fun bar() {
            // body
        }
    }

    /**
     * 在接口中可以声明属性。属性可以是抽象的，或者提供了访问器的实现。
     * 接口的属性没有幕后字段，因此访问器不能引用到他们。
     */
    interface MyInterface2 {
        val prop: Int // abstract

        val propertyWithImplementation: String
            get() = "foo"

        fun foo() {
            print(prop)
        }
    }

    class Child2 : MyInterface2 {
        override val prop: Int = 29
    }


    /**
     * 解决覆写冲突
    如果父类的列表中有多个类型，我们可能会继承了同一个方法的多个实现。例如：

    bar() 在 A 中没有用 abstract 标记，这是因为接口的函数如果没有函数体，默认就是抽象的）
     */
    interface A {
        fun foo() { print("A") }
        fun bar()
    }

    interface B {
        fun foo() { print("B") }
        fun bar() { print("bar") }
    }

    class C : A {
        override fun bar() { print("bar") }
    }

    class D : A, B {
        override fun foo() {
            super<A>.foo()
            super<B>.foo()
        }

        override fun bar() {
            super<B>.bar()
        }
    }


}