package com.kotlin.practice.classandobject

/**
 *
 * @author shhe
 * @Date 2020/10/26 下午8:40
 * @Description:
 */
class EnumClasses {
    /**
     * 枚举类最基本的用法是实现类型安全的枚举：
     * 每一个枚举常量都是一个对象。
     */
    enum class Direction {
        NORTH, SOUTH, WEST, EAST
    }

    enum class Color(val rgb: Int) {
        RED(0xFF0000),
        GREEN(0x00FF00),
        BLUE(0x0000FF)
    }

    /**
     * 枚举常量也可以声明他们自己的匿名类：
     */
    enum class ProtocolState {
        WAITING {
            override fun signal() = TALKING
        },

        TALKING {
            override fun signal() = WAITING
        };

        abstract fun signal(): ProtocolState
    }

    enum class RGB { RED, GREEN, BLUE }

    inline fun <reified T : Enum<T>> printAllValues() {
        println("beging ...")
        println(enumValues<T>().joinToString { it.name })
    }

//    printAllValues<RGB>()   // prints RED, GREEN, BLUE

    fun test() {
        printAllValues<RGB>()
    }

}