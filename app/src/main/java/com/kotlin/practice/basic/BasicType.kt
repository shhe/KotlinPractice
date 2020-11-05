package com.kotlin.practice.basic

import android.util.Log

/**
 *
 * @author shhe
 * @Date 2020/10/19 下午5:00
 * @Description:
 */
class BasicType {

    private val TAG : String = "BasicType"

    private val oneMillion = 1_000_000
    private val creditCardNumber = 1234_5678_9012_3456L
    private val socialSecurityNumber = 999_99_9999L
    private val hexBytes = 0xFF_EC_D5_5E
    private val bytes = 0b11010010_01101001_10010100_10010010

    fun base() {

        Log.i(TAG, "$oneMillion $creditCardNumber  $socialSecurityNumber $hexBytes $bytes")
    }

    fun test() {
        val a: Int = 10000
        print(a === a) // Prints 'true'
        Log.i(TAG, (a === a).toString());
        val boxedA: Int? = a
        val anotherBoxedA: Int? = a
        print(boxedA === anotherBoxedA) // !!!Prints 'false`!!!
        Log.i(TAG, ((boxedA === anotherBoxedA).toString()))
    }

    fun test2() {
        val a: Int = 10000
        print(a == a) // Prints 'true'
        val boxedA: Int? = a
        val anotherBoxedA: Int? = a
        print(boxedA == anotherBoxedA) // Prints 'true'

        Log.i(TAG, ((boxedA === anotherBoxedA).toString()))
    }

    fun transform() {
        // Hypothetical code, does not actually compile:
        val a: Int? = 1 // A boxed Int (java.lang.Integer)
        val b: Long? = a?.toLong() // implicit conversion yields a boxed Long (java.long.Long)
        print(a?.toLong() == b) // Surprise! This prints "false" as Long's equals() check for other part to be Long as well

        Log.i(TAG, (a?.toLong() == b).toString())

        Log.i(TAG, "左移动： "+(1 shl 2).toString())
    }

    fun compare() {
        val i = 3
        val j = 6
        val temp = 3;
        Log.i(TAG, "temp in : "+(temp in i..j))
    }

    fun decimalDigitValue(c: Char): Int {
        if (c !in '0'..'9')
            throw IllegalArgumentException("Out of range")
        return c.toInt() - '0'.toInt() // Explicit conversions to numbers
    }

    fun array() {
        val array1 = arrayOf(1,2,3)
        val array2 = arrayOfNulls<Int>(5)
        val array3 = IntArray(5)
        // Creates an Array<String> with values ["0", "1", "4", "9", "16"]
        val asc = Array(5, { i -> (i * i).toString() })
        Log.i(TAG, asc.contentDeepToString())
        Log.i(TAG, array1.contentDeepToString())
        Log.i(TAG, array2.contentDeepToString())
    }

    fun string() {
        val str = "string"
        for (c in str) {
            Log.i(TAG, c.toString())
        }

        val i = 10
        // 字符串模版 模板表达式以美元符号（$）开始，由一个简单的名称组成：
        val s = "i = $i"
        Log.i(TAG, s)

        val string = "String"
        // 或者是大括号内的任意表达式：
        val str1 = "$string.length is ${string.length}"
        Log.i(TAG, str1)

        // 模板可用于纯字符串和转义后的字符串内。如果要在纯字符串（不支持转义）中展示 $ 符号，可以使用如下语法：
        var price  = "\""
        Log.i(TAG, price)
        price = "${'\$'}9.99"
//        price = "\$9.99"
        Log.i(TAG, price)
    }

    fun nullableList_test() {
        val nullableList: List<Int?> = listOf(1, 2, null, 4)
        val intList: List<Int> = nullableList.filterNotNull()
    }

}