package com.kotlin.practice.classandobject

/**
 *
 * @author shhe
 * @Date 2020/10/28 上午10:28
 * @Description: 属性代理
 */
class Delegation {

    interface Base {
        fun print()
    }

    /**
     * `Derived` 父类列表中的 `by` 子句表示 `b` 会被存储在所有的 `Derived` 对象的
     * 内部，编译器会生成 `Base` 的所有方法，这些方法会跳向 `b`。
     */
    class BaseImpl(val x: Int) : Base {
        override fun print() { println(x) }
    }

    class Derived(b: Base) : Base by b

    /*fun main(args: Array<String>) {
        val b = BaseImpl(10)
        Deribed(b).print() // prints 10
    }*/



    interface Base1 {
        fun printMessage()
        fun printMessageLine()
    }

    class BaseImpl1(val x: Int) : Base1 {
        override fun printMessage() { println(x) }
        override fun printMessageLine() { println(x) }
    }

    /**
     * 覆写用代理实现的接口成员
     */
    class Derived1(b: Base1) : Base1 by b {
        /**
         * 覆写的工作方式与我们预期的一致：编译器会使用我们 `override` 的实现而不是代理对象中的实现。如果我们给
         * `Derived` 增加覆写的函数 `override fun printMessage() { print("abc") }`，程序执行后会打印出 “abc”
         * 而不是 “10”。
         */
        override fun printMessage() { println("abc") }
    }
}