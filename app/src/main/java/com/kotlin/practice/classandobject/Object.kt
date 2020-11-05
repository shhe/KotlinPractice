package com.kotlin.practice.classandobject

/**
 *
 * @author shhe
 * @Date 2020/10/27 下午8:34
 * @Description:
 */
class Object {


}

/*
window.addMouseListener(object : MouseAdapter() {
    override fun mouseClicked(e: MouseEvent) {
        // ...
    }

    override fun mouseEntered(e: MouseEvent) {
        // ...
    }
})*/

open class A(x: Int) {
    public open val y: Int = x
}

interface B {
//    ...
}

val ab: A = object : A(1), B {
    override val y = 15
}

// 如果万一我们需要的“只是一个对象”，没有无关紧要的父类，可以简写：
fun foo1() {
    val adHoc = object {
        var x: Int = 0
        var y: Int = 0
    }

    print(adHoc.x + adHoc.y)
}


/**
 * 注意，匿名对象只能在局部和私有声明中用作类型。
 * 如果把匿名对象作为公共函数的返回值或者作为公共属性的类型，
 * 函数或者属性的实际类型会变成匿名对象的父类，如果没有父类的话是 Any。
 * 匿名对象中添加的成员是不可访问的。
 */
class C {
    // Private function, so the return type is the annoymouse object type
    private fun foo() = object {
        val x: String = "x"
    }

    // Public function, so the return type is Any
    fun publicFoo() = object {
        val x: String = "X"
    }

    fun bar() {
        val x1 = foo().x        // works
//        val x2 = publicFoo().x  // ERROR: Unresolved reference 'x'
    }
}

/*fun  coutClicks（window ： JComponent）{
    var clickCount =  0
    var enterCount =  0

    window.addMouseListener（object  ： MouseAdapter（）{
        重写fun mouseClicked（e ： MouseEvent）{
            clickCount ++
        }

        重写fun mouseEntered（e ： MouseEvent）{
            enterCount ++
        }
    }）
    // ...
}*/

/**
 * 单例模式是一个非常实用的设计模式，Kotlin 继 Scala 之后让声明一个单例变得很容易
 *
 * 这种写法称为*对象声明*，它永远会有一个位于 `object` 关键字之后的名字。
 * 就像变量声明一样，对象声明不是一个表达式，所以不能用于赋值声明的右侧。
 */
object DataProviderManager {
    class DataProvider {

    }
    fun registerDataProvider(provider: DataProvider) {
        // ...
    }

    val allDataProviders: Collection<DataProvider>
        get() = emptyList()// ...
}

/**
 * 这种对象可以有父类
 */
/*object DefaultListener : MouseAdapter() {
    override fun mouseClicked(e: MouseEvent) {
        // ...
    }

    override fun mouseEntered(e: MouseEvent) {
        // ...
    }
}*/


/**
 * 伴生对象
 *
 * 位于类内部的对象声明可以用 `companion` 关键字标记：
 */
class MyClass {
    companion object Factory {
        fun create(): MyClass = MyClass()
    }
}

class MyClass2 {
    /**
     * 伴生对象的名字可以省略，这种情况下可以使用名字 `Companion`
     */
    companion object {
        fun create1(): MyClass = MyClass()
    }
}

fun test2() {
    MyClass.create()
    MyClass2.create1()
    val x = MyClass2.Companion
}

