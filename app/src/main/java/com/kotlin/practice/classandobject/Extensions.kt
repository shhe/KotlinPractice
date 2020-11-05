package com.kotlin.practice.classandobject

/**
 *
 * @author shhe
 * @Date 2020/10/21 下午8:25
 * @Description: 扩展
 *
 * Kotlin 可以无需依靠继承或某种设计模式（例如装饰器）来扩展一个类的功能，这点类似 C# 和 Gosu。
 * 这个能力通过一个叫 extensions 的特殊声明来实现。
 * Kotlin 支持扩展函数（extension functions）和扩展属性（extension properties）。
 */
class Extensions {

    /**
     * 扩展函数
    扩展函数的名字需要把接收者类型（被扩展的类型）作为前缀。
    下面的代码展示了如何给 MutableList<Int> 增加一个 swap 函数：
     */
    fun MutableList<Int>.swap(index1: Int, index2: Int) {
        val tmp = this[index1]  // 'this' corresponds to the list
        this[index1] = this[index2]
        this[index2] = tmp
    }

    fun test_swap() {
        val l = mutableListOf(1, 2, 3)
        l.swap(0, 2) // 'this' inside 'swap()' will hold the value of 'l'
        println(l.get(0))

        MyClass.foo()
    }

    // 泛型参数的声明位于函数名之前，这样做的目的是为了让它在接收者类型表达式中可用。具体可见泛型函数。
    fun <T> MutableList<T>.swap2(index1: Int, index2: Int) {
        val tmp = this[index1] // 'this' corresponds to the list
        this[index1] = this[index2]
        this[index2] = tmp
    }



    open class C

    class D: C()

    fun C.foo() = "c"

    fun D.foo() = "d"

    /**
     * 需要强调的是，扩展函数的分发方式是静态的，也就是说，他们不是接收器类型的虚函数。这就意味着，
     * 被调用的扩展函数是由调用这个函数的表达式类型所决定，而不是由运行时得到的表达式的结果类型所决定。
     * 例如： printFoo(D())   这个例子会打印出“c”，因为被调用的扩展函数只依赖于参数c所声明的类型，即 C 这个类。
     */
    fun printFoo(c: C) {
        println(c.foo())
    }

    /**
     * 可空的接收者
     * 注意扩展定义在一个可空的接收者类型上。这种扩展可在对象变量上被调用，即使它的值为空，
     * 并且在函数体内可以检查 this == null。这也是为什么不做判空也可以调用 toString() 方法，
     * 因为检查已经在扩展函数中做掉了。

     */
    fun Any?.toString(): String {
        if (this == null) return "null"
        // after the null check, 'this' is autocast to a non-null type,
        // so the toString() below resolves to the member function of
        // the Any class
        return toString()
    }


    // 扩展属性
    val <T> List<T>.lastIndex: Int
        get() = size - 1

    // 注意，因为扩展并没有在类中插入成员，所以并没有一个高效的方式让一个扩展属性拥有幕后字段。
    // 这也是为什么扩展属性没有初始化器。它们的行为只能通过显示地提供 getter/setter 来定义。
//    val ClassAndInheritance.Foo.bar = 1 // error: initializers are not allowed for extension properties




    // 伴生对象扩展
    // 如果一个类有伴生对象，那么也可以给这个伴生对象定义扩展函数和扩展属性。
    class MyClass {
        companion object { } // will be called "Companion"
    }

    fun MyClass.Companion.foo() {
        // ...
    }

    val MyClass.Companion.temp: Int
    get() = 1




    class D2 {
        fun bar() { /* ... */ }
    }

    class C2 {
        fun baz() { /* ... */ }

        fun D2.foo() {
            bar()   // calls D.bar
            baz()   // calls C.baz
        }

        fun caller(d: D2) {
            d.foo() // call the extension function
        }

        /**
         * 当分发接收者（dispatch receiver）和扩展接收者（extension receiver）
         * 的成员名字有冲突时，扩展接收着（extension receiver）的优先级更高。
         * 可以利用限定 this 语法来引用分发接收者的成员。
         */
        fun D.foo() {
            toString()          // calls D.toString()
            this@C2.toString()   // calls C.toString()
        }
    }




    // 声明为成员的扩展可以 是 open，子类可以覆写。这就意味着，这种扩展函数对应于
    // 分发接收者（dispatch receiver）是虚函数，但是对于扩展接收者来说，它是静态的。
    open class D3 {
    }

    class D4 : D3() {
    }

    open class C3 {
        open fun D3.foo() {
            println("D3.foo in C3")
        }

        open fun D4.foo() {
            println("D4.foo in C3")
        }

        fun caller(d: D3) {
            d.foo()     // call the extension function
        }
    }

    class C4 : C3() {
        override fun D3.foo() {
            println("D3.foo in C4")
        }

        override fun D4.foo() {
            println("D4.foo in C4")
        }
    }

    fun test() {
        C3().caller(D3())     // prints "D3.foo in C3"
        C4().caller(D3())    // prints "D3.foo in C4" - dispatch receiver is resolved virtually
        C3().caller(D4())    // prints "D3.foo in C3" - extension receiver is resolved statically
    }
}