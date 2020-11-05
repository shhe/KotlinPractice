package com.kotlin.practice.other

/**
 *
 * @author shhe
 * @Date 2020/10/29 下午4:53
 * @Description:
 */
class Ranges {
    fun test() {
        for (i in 4 downTo 1) println(i) // prints "4321"
    }

    /**
     * 能是用 1 之外的任意步幅来遍历数字吗？当然，使用 `step()` 函数即可：
     */
    fun test2() {
        for (i in 1..4 step 2) print(i) // prints "13"

        for (i in 4 downTo 1 step 2) print(i) // prints "42"
    }
}