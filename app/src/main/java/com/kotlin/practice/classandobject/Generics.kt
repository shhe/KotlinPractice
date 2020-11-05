package com.kotlin.practice.classandobject

/**
 *
 * @author shhe
 * @Date 2020/10/22 下午3:18
 * @Description: 范型
 */
class Generics {

    // 与 Java 一样，Kotlin 的类也可以拥有类型参数（parameter）：
    class Box<T>(t: T) {
        var value = t
    }

    val box: Box<Int> = Box<Int>(1)

    // 如果参数可以被推导出，例如，通过构造器的实参或者利用其他手段，那么类型实参就可以省略掉。
    val box1 = Box(1) // 1 has type Int, so the compiler figures out that we are talking about Box<Int>

    // 通配符类型实参（wildcard type argument）? extends E 表示这个方法接收 E 或者它的某个子类型的对象集合，并非只是 E 自身。


    /**
     * 在 Kotlin 中有一个方式可以向编译器解释这种事情。叫做声明点变型（declaration-site variance）：
     * 我们可以通过标注 Source 的类型参数 T 来确保它只会被 Source<T> 的成员返回（生产），绝不会被消费。
     * 为了做到这一点，Kotlin 提供了 out 修饰符：
     */
    interface Source<out T> {
        fun nextT(): T
    }

    fun demo(strs: Source<String>) {
        val objects: Source<Any> = strs // This is OK, since T is an out-parameter
        // ...
    }


    /**
     * 除了out，Kotlin 还提供了一个互补的变型注解：in。它可以使类型参数变为逆变的：只能被消费，不能被生产。
     * 关于逆变类型的一个好例子是 Comparable：
     */
    interface Comparable<in T> {
        operator fun compareTo(other: T): Int
    }

    fun demo(x: Comparable<Number>) {
        x.compareTo(1.0) // 1.0 has type Double, which is a subtype of Number
        // Thus, we can assign x to a variable of type Comparable<Double>
        val y: Comparable<Double> = x // OK!
    }
/*
    class Array<T>(val size: Int) {
        fun get(index: Int): T {
            *//* ... *//*
            return this.get(index)
        }
        fun set(index: Int, value: T) { *//* ... *//* }
    }*/

    fun copy(from: Array<Any>, to: Array<Any>) {
        assert(from.size == to.size)
        for (i in from.indices)
            to[i] = from[i]
    }
    fun test() {
//        val ints: Array<Any> = arrayOf(1, 2, 3)
        val ints: Array<Int> = arrayOf(1, 2, 3)
        val any = Array<Any>(3) { "" }
//        copy(ints, any) // Error: expects (Array<Any>, Array<Any>)
        println()

    }


    // 同样的，也可以用 in 来投影一个类型：
    fun fill(dest: Array<in String>, value: String) {
    }

    // 星投影（Star-projection）
    /**
     * 有时候我们可能对类型实参一无所知，但是仍然想以一种安全的方式使用它。这里的安全方式就是给泛型类型定义这么一个投影，然后泛型类型的每一个具体实例都是投影的子类型。

    Kotlin 为此提供了一个所谓的星投影语法：

    对于 Foo<out T : TUpper>，T 是一个协变的类型参数，上限是 TUpper，Foo<*> 就等价于 Foo<out TUpper>。它意味着，当 T 不可知时，你可以安全地从 Foo<*> 中读取 TUpper 类型的值。
    对于 Foo<in T>，T 是一个逆变的类型参数，Foo<*> 等价于 Foo<in Nothing>。它意味着，当 T 不可知时，没有任何东西可以写入 Foo<*>。
    对于 Foo<T : TUpper>，T 是一个可变类型参数，上限是 TUpper，Foo<*> 如果用于读值，其等价于 Foo<out TUpper>，如果写值，其等价于 Foo<in Nothing>。
     */





//    泛型函数
//    不只是类可以拥有类型参数。函数也可以。类型参数位于函数名之前：
    fun <T> singletonList(item: T): List<T> {
        return emptyList()
    }

    fun <T> T.basicToString(): String { // extension function
        return ""
    }

    // 如果要调用泛型函数，需要在调用处指明类型实参，位置在函数名之后。
    val l = singletonList<Int>(1)

//    如果能从上下文推导出的话，类型实参也可以省略，下例可以正常运行：
    val l1 = singletonList(1)


    /**
     * 泛型约束
    所有可能被指定类型参数替换掉的类型集合可以通过泛型约束加以限制。

    上限
    最普通的约束类型是上限，对应 Java 的 extends 关键字：

    冒号之后指定的类型就是上限：只能是 Comparable<T> 的子类，可以取代 T。例如：

     */
    fun <T: Comparable<T>> sort(list: List<T>) {
        // ...
    }
    /*fun test2() {
        sort(listOf(1, 2, 3)) // OK. Int is subtype of Comparable<Int>
        sort(listOf(HashMap<Int, String>())) // Error: HashMap<Int, String> is not a subtype of Comparable<HashMap<Int, String>>
    }*/


    /*fun <T> copyWhenGreater(list: List<T>, threshold: T):
            List<String>
            where T: CharSequence,
                  T: Comparable<T> {
        return list.filter( it > threshold }.map { it.toString() }*/

}