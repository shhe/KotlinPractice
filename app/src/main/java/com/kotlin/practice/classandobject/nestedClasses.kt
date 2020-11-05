package com.kotlin.practice.classandobject

/**
 *
 * @author shhe
 * @Date 2020/10/26 下午4:26
 * @Description: 类可以嵌套在其他类中
 */
class nestedClasses {
}

class Outer1 {
    private val bar: Int = 1
    class Nested {
        fun foo() = 2
    }
}

val demo = Outer1.Nested().foo() // == 2


/**
 * 一个类可以标记为 inner，这样它就可以访问外部类的成员。内部类持有外部类对象的引用：
 */
class Outer2 {
    private val bar: Int = 1
    inner class Inner {
        fun foo() = bar
    }
}
