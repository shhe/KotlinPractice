package com.kotlin.practice.basic

import android.util.Log
import com.kotlin.practice.Node
import com.kotlin.practice.getName

/**
 *
 * @author shhe
 * @Date 2020/10/20 下午8:29
 * @Description:
 */
class ReturnAndJumps {

    val TAG = "ReturnAndJumps"

    /**
     * 如果 ?: 左侧表达式非空, 操作符就返回其左侧表达式，否则返回右侧表达式。
     * 请注意，当且仅当左侧为空时，才会对右侧表达式求值。
     */
    fun elvis_operators(b: String?) : Int {
        // if (b !=  null) return b.length else return -1 等价于
        return b?.length ?: -1
    }

    /**
     * 请注意，因为 throw 和 return 在 Kotlin 中都是表达式，
     * 所以它们也可以用在 操作符右侧。这可能会非常方便，例如，检测函数参数：
     */
    fun foo(node: Node): String? {
        val parent = node.parent ?: return null
        val name = node.getName() ?: throw IllegalArgumentException("name expected")
        // ……
        return null
    }

    fun foo() {
        val ints = arrayListOf<Int>(1, 0, 2, 3)
        ints.forEach {
            if (it == 0) return // non-local return directly to the caller of foo()
            Log.i(TAG, "foo $it")
        }
    }

    /**
     * return 表达式从就近的外围函数中返回 - foo。（注意：只有传入到内联函数的 lambda 表达式才支持非局部（non-local）返回。）。
     * 如果要从 lambda 表达式中返回，我们需要加标签并且限定 return。
     */
    fun foo2() {
        val ints = arrayListOf<Int>(1, 0, 2, 3)
        ints.forEach lit@ {
            if (it == 0) return@lit // local return to the caller of the lambda, i.e. the forEach loop
            Log.i(TAG, "foo2 $it")
        }
    }

    /**
     * 多数情况下，更便利的方式是使用隐式标签：标签与接收 lambda 表达式作为参数的函数名同名。
     */
    fun foo3() {
        val ints = arrayListOf<Int>(1, 0, 2, 3)
        ints.forEach {
            if (it == 0) return@forEach
            Log.i(TAG, "foo3 $it")
        }
    }

    /**
     * 除此之外，我们可以用一个匿名函数来代替 lambda。匿名函数中的 return 声明会从它自己当中返回。
     */
    fun foo4() {
        val ints = arrayListOf<Int>(1, 0, 2, 3)
        ints.forEach(fun(value: Int) {
            if (value == 0) return  // local return to the caller of the annoymous fun, ie.e. the forEach loop
            Log.i(TAG, "foo4 $value")
        })
    }

    /**
     * 注意，上面三个例子当中 local return 的用法和常规循环中 continue 的作用类似。没有与 break 直接等价的用法，
     * 但是我们可以采用增加一个嵌套 lambda 并从中局部返回的做法来模拟实现：
     */
    fun foo5() {
        run loop@{
            listOf(1, 2, 3, 4, 5).forEach {
                if (it == 3) return@loop // non-local return from the lambda passed to run
                Log.i(TAG, "foo5 $it")
            }
        }

        Log.i(TAG,"done with nested loop")
    }




}