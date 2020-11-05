package com.kotlin.practice.basic

import android.util.Log
import java.lang.Integer.parseInt

/**
 *
 * @author shhe
 * @Date 2020/10/20 下午5:43
 * @Description:
 */
class ControlFlow {

    val TAG = "ControlFlow"

    fun if_exp() {
//        Kotlin 的 if 是一个表达式，也就是说，它可以返回一个值。因此三元操作符（condition ? then : else）
//        就失去用武之地了，因为普通 if 就可以担当此任。

        // Traditional usage
        var a = 1
        var b = 3
        var max1 = a
        if (a < b) max1 = b

        // With else
        var max2: Int
        if (a > b) {
            max2 = a
        } else {
            max2 = b
        }

        // As expression
        val max3 = if (a > b) a else b

    }

    fun when_exp() {
        var x = 2
        when (x) {
            1 -> print("x == 1")
            2 -> print("x == 2")
            else -> { // Note the block
                print("x is neither 1 nor 2")
            }
        }

        // 如果多个 case 的处理方式一样，可以用逗号把分支条件联合起来：
        when (x) {
            0, 1 -> print("x == 0 or x == 1")
            else -> print("otherwise")
        }


        var s = "3"
        // 不只是常量，任何表达式都可以作为分支条件：
        when (x) {
            parseInt(s) -> println("s encodes x")
            else -> println("s does not encode x")
        }


        var validNumbers = arrayOf(22)
        // 也可以用 in 或者 !in 来判断一个值是否在 range 或 collection 内：
        when (x) {
            in 1..10 -> print("x is in the range")
            in validNumbers -> print("x is valid")
            !in 10..20 -> print("x is outside the range")
            else -> print("none of the above")
        }
    }

    // 还可以用 is 或者 !is 来做类型判断。因为有智能类型转换（smart cast），所以无需显示的类型转换：
    fun hasPrefix(x: Any) = when(x) {
        is String -> x.startsWith("prefix")
        else -> false
    }

    fun when_exp2(number: Int) : String {
        when {
            number % 2 == 0 -> {
                Log.i(TAG, "$number is even")
                return "$number is even"
            }
            number % 2 != 0 ->  {
                Log.i(TAG,"$number is odd")
                return "$number is odd"
            }
        }
        return "null"
    }

    fun for_exp1() {
        var arrayInt = arrayListOf<Int>(1, 2)
        for (item in arrayInt) {
            Log.i(TAG, "$item")
        }

        for (i in arrayInt.indices) {
            Log.i(TAG, "$arrayInt[i]")
            Log.i(TAG, arrayInt[i].toString())
        }

        //注意，“区域的迭代（iteration through a range）”会做最佳优化，并不会产生额外对象。
        //另外，withIndex 是一个库函数：
        for ((index, value) in arrayInt.withIndex()) {
            Log.i(TAG, "the element at $index is $value")
        }
    }

    fun while_or_doWhile(a : Int) {
        var temp = a
        while (temp > 0) {
            --temp;
            Log.i(TAG, "while $temp")
        }

        do {
            ++temp
            Log.i(TAG, "doWhile $temp")
        } while (temp < a)
    }
}