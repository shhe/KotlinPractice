package com.kotlin.practice.classandobject

import kotlin.properties.Delegates
import kotlin.reflect.KProperty

/**
 *
 * @author shhe
 * @Date 2020/10/28 下午1:56
 * @Description: 代理属性
 *
 * 有一些常见类型的属性，虽然我们可以在每次需要时去手动实现，但是更好的方式是只实现一次并对所有可用，然后封装成库。
 * 具体的例子有：
 * - 懒属性：他们的值只在第一次访问时才被计算；
 * - 可被观察的属性：监听器会接收到属性变化的通知；
 * - 把属性存储在 map 中，无需每个属性都有一个单独的字段。
 * 为了覆盖这些（以及其他的）case，Kotlin 支持*代理属性*：
 */
class DelegatedProperties {
    class Example {
        var p: String by Delegate()
    }

    class Delegate {
        operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
            return "$thisRef, thank you for delegating '${property.name}' to me!"
        }

        operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
            println("$value has been assigned to '${property.name}' in $thisRef.")
        }
    }

    fun test() {
        val e = Example()
        println(e.p)

        e.p = "new"
    }

    /**
     * `lazy()` 是一个函数，参数是一个 lambda ，返回值是一个 `Lazy<T>` 实例，这个实例可以作为实现懒属性的
     * 代理：`get()` 的第一次调用会执行传给 `lazy()` 的 lambda 并且记录执行结果，后续的 `get()` 调用仅仅
     * 返回第一次记录的结果。
     */
    val lazyValue: String by lazy {
        println("computed!")
        "Hello"
    }

    fun main(args: Array<String>) {
        println(lazyValue)
        println(lazyValue)
    }

    /**
     * 可观察的
     * `Delegates.observable()` 有两个参数：初始值和变化改动的处理器。
     * 处理器的调用发生在每次给属性赋值时（在赋值执行之后）。它有三个参数：被赋值的属性，旧值和新值：
     */
    class User {
        var name: String by Delegates.observable("<no name>") {
            prop, old, new ->
            println("$old -> $new")
        }
    }

    fun main1(args: Array<String>) {
        val user = User()
        user.name = "first"
        user.name = "second"
    }

    /**
     * 如果想能够拦截一个赋值并且“否决”它的话，可以用 `vetoable()` 来代替 `observable()`。
     * 传入 `vetoable` 的处理器的调用发生在在新属性的赋值操作执行之前。
     */
    /*class User2 {
        var name: String by Delegates.vetoable("<no name>") {
            prop, old, new ->
            println("$old -> $new")
        }
    }*/

    /**
     * 在 Map 中保存属性
     *
     * 一个常见使用场景是把属性的值保存在一个 map 中。经常会应用在一些像解析 JSON 或者做其他“动态”事情的应用中。
     * 这种情况下，可以使用 map 实例本身作为代理属性的代理。
     */
    class User3(val map: Map<String, Any?>) {
        val name: String by map
        val age: Int     by map
    }

    val user = User3(mapOf(
            "name" to "John Doe",
            "age" to 25
    ))

    fun printUser() {
        println(user.name)
        println(user.age)
    }

    /**
     * 如果把只读的 `Map` 替换成 `MutableMap`，`var` 属性也可以这么用：
     */
    class MutableUser(val map: MutableMap<String, Any?>) {
        var name: String    by map
        var age: Int        by map
    }

    /*fun example(computeFoo: () -> Foo) {
        val memoizedFoo by lazy(computeFoo)

        if (someCondition && meoizedFoo.isValid()) {
            memoizedFoo.doSomething()
        }
    }*/
}