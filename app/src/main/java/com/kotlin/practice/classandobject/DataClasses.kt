package com.kotlin.practice.classandobject

/**
 *
 * @author shhe
 * @Date 2020/10/22 上午10:56
 * @Description: 数据类
 */
class DataClasses {
    fun copyTest() {
        val jack = User(name = "Jack", age = 1)
        val olderJack = jack.copy(age = 2)
        println(olderJack)

        // 数据类生成的组件函数在结构声明中会发挥作用：
        val jane = User("Jane", 35)
        val (name, age) = jane
        println("$name, $age years of age") // prints "Jane, 35 years of age"
    }
}

/**
 * 编译器会自动根据首要构造函数中声明的属性生成如下代码：
 * equals() / hashCode() 对
 * toString()（格式："User(name=John, age=42)"）
 * componentN()函数，对应于属性的声明顺序copy() 函数（下有说明）
 */
data class User(val name: String, val age: Int) {
}


// 在 JVM 之上，如果类需要无参构造器，那么需要给属性指定默认值。
data class User1(val name: String = "", val age: Int = 0)


/**
 * 类体中声明的属性
 * 编译器只会使用首要构造函数中定义的属性来自动生成函数。如果要从生成的实现中排除一个属性，可以把它声明在类体中：
 */
data class Person(val name: String) {
    var age: Int = 0
}

// 拷贝
// 很多时候，我们需要拷贝一个对象，修改某些属性，但是其他保持不变。这就是 copy() 函数的作用。例如，上例的 User 类，copy() 实现如下：
fun copyTest() {
    val jack = User(name = "Jack", age = 1)
    val olderJack = jack.copy(age = 2)
    println(olderJack)
}




