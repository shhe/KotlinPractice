package com.kotlin.practice.other

/**
 *
 * @author shhe
 * @Date 2020/10/29 下午5:18
 * @Description:
 */
class Reflection {

    val c = Reflection::class

    fun isOdd(x: Int) = x % 2 != 0

    /**
     * 我们可以简单地直接调用它（`isOdd(5)`），但是我们也可以将其作为一个值来传递（例如，传递给其它函数）。我们可以使用 `::` 操作符来做到这一点：
     */
    fun test() {
        val numbers = listOf(1, 2, 3)
        println(numbers.filter(::isOdd)) // prints [1, 3]
    }
    /**
     * 这里的 `::isOdd` 是函数类型 `(Int) -> Boolean` 的一个值。
     */

    /**
     * 如果根据上下文可以知道期望的类型，`::` 也能用于重载的函数。
     */
    fun test2() {

        fun isOdd(x: Int) = x % 2 != 0
        fun isOdd(s: String) = s == "brillig" || s == "slithy" || s == "tove"

        val numbers = listOf(1, 2, 3)
        println(numbers.filter(::isOdd)) // refers to isOdd(x: Int)

        /**
         * 或者还有一种方式，我们可以把方法的引用存储在一个变量中并且指明类型，这样就提供了必要的上下文：
         */
        val predicate: (String) -> Boolean = ::isOdd    // refers to isOdd(x: String)
    }

    /**
     * 如果我们需要使用类的成员，或者扩展函数，需要加以限定。例如，`String::toCharArray` 是一个类型为 `String: String.() -> CharArray` 的扩展函数。
     */


    /**
     * 函数组合
     *
     * 它的返回值是两个函数的组合：`compose(f, g) = f(g(*))`。然后就可以将其应用到可调用的引用上
     */
    fun <A, B, C> compose(f: (B) -> C, g: (A) -> B): (A) -> C {
        return { x -> f(g(x)) }
    }
    fun test3() {
        fun length(s: String) = s.length

        val oddLength = compose(::isOdd, ::length)
        val strings = listOf("a", "ab", "abc")

        println(strings.filter(oddLength)) // Prints "[a, abc]"
    }

    /**
     * 属性引用
     */
    val x = 1
    var y = 1
    fun test4() {
        println(::x.get())  // prints "1"
        println(::x.name)   // prints "x"

        ::y.set(3)
        println(::y.get())
        println(y)
    }

    /**
     * 在使用无参函数的地方也可以使用类型引用：
     */
    fun test5() {
        val strs = listOf("a", "bc", "def")
        println(strs.map(String::length)) // prints [1, 2, 3]
    }

    /**
     * 如果要访问作为类成员的属性，我们需要加以限定：
     */
    class A(val p: Int)
    fun test6() {
        val prop = A::p
        println(prop.get(A(1))) // prints "1"
    }

    /**
     * 扩展属性
     */
//    val String.lastChar: Char
//        get() = this[length - 1]

    fun test7() {
        println(String::lastChar.get("abc")) // prints "c"
    }

    fun test8() {
//        println(A::p.javaGetter)  // prints "public final int A.getP()"
//        println(A::p.javaField) // prints "private final int A.p"
    }
}

/**
 * 扩展属性
 */
val String.lastChar: Char
    get() = this[length - 1]