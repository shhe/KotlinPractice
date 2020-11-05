package com.kotlin.practice.other

import androidx.appcompat.widget.DecorContentParent

/**
 *
 * @author shhe
 * @Date 2020/10/29 下午8:03
 * @Description:
 */
class NullSafety {

    /**
     * 如果 `b` 不为空，返回 `b.length`，否则是 `null`。这个表达式的类型是 `Int?`。
     */
    fun test() {
        var b: String? = "123"
        b = null
        println(b?.length)

        /**
         * 需要注意，只有当左侧的表达式不为空时，右侧才会被计算。
         */
        val l = b?.length ?: -1
        /**
         * 等价于
         */
//        val l: Int = if (b != null) b.length else -1


        /**
         * 第三种选项是给 NPE 爱好者准备的：这个非空断言操作符（`!!`）会把任何值转变成一个非空类型，
         * 如果值为空，则会抛出异常。我们可以写成 `b!!`，这样就会返回一个值不为空的 `b`（例如，例子中的 `String`），
         * 或者如果 `b` 是 null 的话抛出一个 NPE。
         */
//        val l1 = b!!.length

    }

    /**
     * 如果只有在变量不为空时才执行某个操作，那么可以用安全调用操作符配合 `let` 来使用：
     */
    fun test2() {
        val listWithNulls: List<String?> = listOf("A", null)
        for (item in listWithNulls) {
            item?.let { println(it) } // prints A and ignores null
        }

        /**
         * 安全调用可位于赋值操作的左侧。然后，只要是安全调用链上任何一个接收者为空，赋值就会被跳过，右侧的表达式也就不会被计算。
         */
        // If either `person` or `person.department` is null, the function is not called:
//        person?.department?.head = managerPool.getManager();
    }

    /**
     * 注意，因为 `throw` 和 `return` 都是表达式，所以可以位于猫王操作符的右侧。这个用法非常方便，例如，检查函数参数。
     */
    class Node1/*(*//*val parent: String, val name: String?*//*)*/ {
        var parent: String? = null
        var name: String? = null
    }
    /*fun foo(node: Node1): String? {
        val parent = node.parent ?: return null
        val name = node.name ?: throw IllegalArgumentException("name expected")
        // ...
    }*/
}