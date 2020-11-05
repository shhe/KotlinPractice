package com.kotlin.practice.function_lambdas

import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantReadWriteLock

/**
 *
 * @author shhe
 * @Date 2020/10/29 下午4:05
 * @Description: 内联函数
 *
 * 使用高阶函数需要付出运行时代价：每个函数都是一个对象，并且拥有一个闭包，即函数体内可访问的那些变量。
 * 内存分配（包括函数对象还有类）以及虚调用（virtual call）都会带来运行时开销。

但是，貌似很多情况下，内联 lambda 表达式可以消除这类开销。下面的函数很好地展示了这种情况。`lock` 函数可以简单地内联到调用处。
 */
class inline_function {

    fun test() {
//        lock(l) { foo() }
    }

    fun foo() {

    }

    inline fun <T> lock(lock: Lock, body: () -> T)/*: T*/ {
        // ...
//        return T()
    }

    /**
     * 非内联
    如果只想让传入到内联函数的某些 lambda 成为内联，可以用 `noinline` 修饰符来标记函数参数：
     */
    inline fun foo(inlined: () -> Unit, noinline notInlined: () -> Unit) {
        // ...
    }

    /*fun foo2() {
        ordinaryFunction {
            return // ERROR: can not make `foo` return here
        }
    }*/

    fun hasZeros(ints: List<Int>): Boolean {
        ints.forEach {
            if (it == 0) return true // returns from hasZeros
        }
        return false
    }

    /**
     * 注意，有的内联函数不会直接在函数体内调用传入的 lambda，而是在其他的执行环境中（例如局部对象或者嵌套函数）
     * 发起调用。这种情况下，非局部的控制流也是不允许的。为了指明这个限制，可以使用 `crossinline` 修饰符来标记 lambda 参数：
     */
    inline fun f(crossinline body: () -> Unit) {
        val f = object: Runnable {
            override fun run() = body()
        }
    }

/*    fun <T> TreeNode.findParentOfType(clazz: Class<T>): T? {
        var p = parent
        while (p != null && !clazz.isInstance(p)) {
            p = p.parent
        }
        @Suppress("UNCHCKED_CAST")
        return p as T?
    }*/

    /*inline fun <reified T> TreeNode.findParentOfType():
            T? {
        var p = parent
        while (p != null && p !is T) {
            p = p.parent
        }
        return p as T?
    }*/
}