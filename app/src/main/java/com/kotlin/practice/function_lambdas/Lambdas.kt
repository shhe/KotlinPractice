package com.kotlin.practice.function_lambdas

import android.net.wifi.WifiConfiguration.AuthAlgorithm.strings
import android.os.Build
import androidx.annotation.RequiresApi
import java.util.Collections.max
import java.util.concurrent.locks.Lock
import kotlin.math.max

/**
 *
 * @author shhe
 * @Date 2020/10/29 上午11:28
 * @Description:
 */
class Lambdas {
    /**
     * 高阶函数
    高阶函数可以接收函数类型的参数，也可以返回一个函数。`lock()` 是一个很好的例子，
    它接收一个 lock 对象和一个函数，获取锁之后执行函数，然后释放锁：
     */
    fun <T> lock(lock: Lock, body: () -> T): T {
        lock.lock()
        try {
            return body()
        }
        finally {
            lock.unlock()
        }
    }
    /**
     * 上面的代码中，`body` 是一个函数类型：`() -> T`，无参并且返回值类型是 `T` 。
     * 在 try 块中发起调用，被 `lock` 保护，由 `lock()` 提供返回值。

    `lock()` 可接收另一个函数作为它的参数：
     */
    /*fun toBeSynchronized() = sharedResource.operation()
    val result = lock(lock, ::toBeSynchronized)*/

    /**
     * 传入一个 lambda 表达式会更简单：
     */
//    lock(lock, { sharedResource.operation() })


    /**
     * lambda 表达式更详细的描
     *
     * - lambda 表达式由花括号包裹
    - 参数在 `->` 之前声明（参数类型可省略）
    - 函数体在 `->` 之后（如果有的话）

    如果一个函数的最后一个参数是一个函数，我们可以在函数括号之外传入一个 lambda 表达式：
     */
    /*lock (lock) {
        sharedResource.operation()
    }*/

    /**
     * 另一个高阶函数的例子是 `map()`：
     */
    fun <T, R> List<T>.map(transform: (T) -> R): List<R> {
        val result = arrayListOf<R>()
        for (item in this)
            result.add(transform(item))
        return result
    }
    /*fun testMap() {
        val doubled = inis.map { value -> value * 2 }
    } */

    /**
     * 如果 lambda 是函数唯一的参数，函数的括号可以省略。
     */

    /**
     * it: 单个参数的隐式名字

    另外一个比较有用的约定是：如果一个函数只有一个参数，那么参数的声明连带 `->` 可以省略，参数名用 `it` 指代：
     */
//    ints.map { it * 2 }

    fun test(/*strings: String*/) {
        var strings = arrayListOf<String>()
        strings.add("aa")
        strings.add("aaa")
        strings.add("aaaa")
        strings.add("ddddd")
        strings.add("aaaaa")
        strings.add("a")
        strings.add("bbbbb")
        var tempList = strings.filter { it.length == 5 }.sortedBy { it }.map { it.toUpperCase() }
        println(strings.toArray().contentDeepToString())
        println(tempList.toTypedArray().contentDeepToString())
    }

    /**
     * 下划线指代无用变量（1.1开始）

    如果 lambda 的参数无用，可以用下划线代替：
     */
    @RequiresApi(Build.VERSION_CODES.N)
    fun test2() {
        var map = emptyMap<String, String>()
        map.forEach { _, value -> println("$value!") }
    }

    /**
     * 一个 lambda 表达式或匿名函数是一个 “函数字面量（function literal）”，例如，一个函数没有声明，但是直接作为表达式传递。如下：
     */
    /**
     * `max` 是高阶函数，它的第二个参数是一个函数。
     * 第二个参数是一个表达式，这个表达式本身是一个函数（函数字面量）。作为函数，它等价于：
     * fun compare(a: String, b: String): Boolean = a.length < b.length
     */
    fun test3() {
        var strings = arrayListOf<String>()
        strings.add("aa")
        strings.add("aaa")
        strings.add("aaaa")
        strings.add("aaaaaa")
        strings.add("a")
        println(max(strings, { a, b -> a.length < b.length }))
    }

    /**
     * 函数类型
    对于接收函数作为参数的函数，我们需要为那个参数指定函数类型。例如，上述提到的 `max`，其定义如下：
     */
    fun <T> max(collection: Collection<T>, less: (T, T) -> Boolean): T? {
        var max: T? = null
        for (it in collection)
            if (max == null || less(max, it))
                max = it
        return max
    }
    /**
     * 参数 less 的类型是 `(T, T) -> Boolean`，两个 T 类型参数，返回值是 `Boolean`：如果第一个参数小于第二个，返回 true。

    函数体的第四行，`less` 是一个函数调用，传入了两个类型为 `T` 的参数。

    函数类型的写法如上，也可以用命名参数来增加可读性：
     */
//    val compare: (x: T, y: T) -> Int = ...

    /**
     * 如果要声明一个可空函数类型的变量，可以用括号包裹整个函数类型，然后在后面加一个问号：
     */
    var sum: ((Int, Int) -> Int)? = null




    /**
     * lambda 表达式的语法
    lambda 表达式，即函数类型的字面量，其完整的语法形式如下：
     */
    val sum1 = { x: Int, y: Int -> x + y }
    /**
     * lambda 表达式由大括号包裹，在完整的语法格式下，参数声明在括号内，并且可以带有可选的类型标记，
     * 函数体在 `->` 符号之后。如果编译器推测出来返回值类型不是 `Unit`，函数 体内的最后一个表达式（可能只有一个表达式）会被当做返回值。

    如果去掉所有的可选标记，剩下的代码如下所示：
     */
    val sum2: (Int, Int) -> Int = { x, y -> x + y }
    /**
     * lambda 只有一个参数是很常见的。如果 Kotlin 本身能够识别出 lambda 的函数签名，我们就不需要手动去声明这个
     * 唯一的参数，因为 Kotlin 会隐式地帮我们把这个参数声明为 `it`：
     */
    fun test4() {
        var ints = arrayListOf<Int>(1, 2, 4, 5, -1)
        var tempList = ints.filter { it > 0 }
        println(tempList.toTypedArray().contentDeepToString())
    }

    /**
     * 我们可以用限定的 return 语法从 lambda 中显示地返回一个值。否则，最后一个表达式的值会被隐式返回。
     * 因此，下面两个代码片段是等价的：
     */
    fun test5() {
        var ints = arrayListOf<Int>(1, 2, 4, 5, -1)
        ints.filter {
            val shouldFilter = it > 0
            shouldFilter
        }

        ints.filter {
            val shouldFilter = it > 0
            return@filter shouldFilter
        }
    }
    /**
     * 上面所示的 lambda 表达式的语法缺少了一项能力：无法指定返回值类型。多数情况下，类型可以自动推导出，
     * 所以也没有必要指定。但是，如果一定要指定类型的话，可以利用另一种语法：*匿名函数*。
     */
//    fun(x: Int, y: Int): Int = x + y

    /*fun(x: Int, y: Int): Int {
        return x + y
    }*/

    /**
     * 参数和返回值类型的指定方式与常规函数一样，唯一不同的是，能够根据上下文推导出的参数类型可以直接省略掉。
     */
    fun test6() {
        var ints = arrayListOf<Int>()
        ints.filter(fun(item) = item > 0)
    }

    /**
     * lambda 表达式或者匿名函数（以及局部函数和对象表达式）可以访问它的闭包，即外围区域定义的变量。
     * 与 Java 不同的是，在闭包内捕获（captured）的变量可以被修改：
     */
    fun test7(ints: List<Int>): Int {
        var sum = 0
        ints.filter { it > 0 }.forEach {
            sum += it
        }
        return sum
    }

    /**
     * lambda 变量
     */
//    val sum: Int.(other: Int) -> Int


    /**
     * 匿名函数的语法允许我们直接指定函数字面量的接收者类型。因此，我们可以事先定义一个带接收者的函数变量，以备后用。
     */
    fun test8() {
        val sum1: Int.(other: Int) -> Int
        sum1 = fun Int.(other: Int): Int = this + other
        println(3.sum1(2))

        val sum = fun Int.(other: Int): Int = this + other
        println(1.sum(2))
    }

    /**
     * 带接收者函数的非字面值能够和普通函数兼容，前提是普通函数的第一个参数需要是接收者的类型，
     * 它们可以互相赋值，也可以作为参数来传递。例如，`String.(Int) -> Boolean` 和 `(String, Int) -> Boolean` 是兼容的。
     */
    fun test9() {
        val represents: String.(Int) -> Boolean = { other -> toIntOrNull() == other }
        println("123".represents(123)) // true

        fun testOperation(op: (String, Int) -> Boolean, a: String, b: Int, c: Boolean) =
                assert(op(a, b) == c)

        testOperation(represents, "100", 100, true) // OK
    }
    /**
     * > 上例中，`represents` 是一个类型为 `String.(Int) -> Boolean` 的 lambda 变量。

    > 它的值是 `{ other -> toIntOrNull() == other }`：
    >   * 大括号表示 lambda 的定义
    >   * lambda 的 receiver object 的类型是 `String`
    >   * toIntOrNull() 是 `String` 的方法，调用对象是 receiver object。
     */


    /**
     * lambda 表达式可用作带接收者的函数字面量，前提是接收者的类型可以通过上下文推导出。
     */
    class HTML {
        fun body() { //...
            println("HTML.body")
            }
    }
    fun html(init: HTML.() -> Unit): HTML {
        val html = HTML()   // Create the receiver object
        html.init()         // pass the receiver object to the lambda
        return html
    }
    fun test10() {
        html {      // lambda with receiver begins here
            body()  // calling a method on the receiver object
        }
    }

    /**
     * **关于 function literal 的解释**

    把“function literal”翻译成“函数字面量”，我也不知道准不准确，这里稍微解释一下。

    如果我们直接把一个函数写在那，就叫 function literal；反之，如果我们用一个变量来表示函数，那么这个变量就不是 function literal，而是一个 non-literal value。

     */
}

