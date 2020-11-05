package com.kotlin.practice.classandobject

/**
 *
 * @author shhe
 * @Date 2020/10/21 下午8:04
 * @Description: Kotlin 中有四个可见性修饰符：private、protected、internal 和 public。
 * 默认的可见性（如果没有显示指明修饰符）是 public。
 */
class VisibilityAndModifiers {


}

// 函数、属性，类、对象以及接口可以声明在“最顶层”，也就是说，直接在包内。
fun baz() {}

class Bar(){}

//如果标记为 internal，只要是同一个模块，都可见。
//protected 对于顶层声明不可用。

private fun foo() {} // visible inside VisibilityAndModifiers.kt

public var bar: Int = 5 // property is visible everywhere
    private set         // setter is visible only in VisibilityAndModifiers.kt

internal val baz = 6    // visible inside the same module


open class Outer {
    private val a = 1
    protected open val b = 2
    internal val c = 3
    open val d = 4 // public by default

    protected class Nested {
        public val e: Int = 5
    }
}

class Subclass : Outer() {
    // a is not visible
    // b, c, and d are visible
    // Nested and e are visible

    override val d = 5  // 'b' is protected

    override val b: Int
        get() = super.b + 3
}

class Unrelated(o: Outer) {
    // o.a, o.b are not visible
    // o.c and o.d are visible (same module)
    // Outer.Nested is not visible, and Nested::e is not visible either
}

//局部声明
//局部变量、函数和类没有可见性修饰符。
