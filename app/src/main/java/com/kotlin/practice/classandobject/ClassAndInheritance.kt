package com.kotlin.practice.classandobject

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.kotlin.practice.annotation.Inject

/**
 *
 * @author shhe
 * @Date 2020/10/21 上午10:47
 * @Description:
 */
class ClassAndInheritance {

    class Person constructor(firstName: String) {

    }

    /**
     * 如果首要构造器没有任何标注或者可视化修饰符，constructor 关键字可以省略。
     */
    class Person1(firstName: String) {

    }

    /**
     * 首要构造器不能包含任何代码。初始化代码可位于用 init 关键字作为前缀的**初始化区块（initializer block）**中。
     * 一个实例在初始化的过程中，初始化区块会按照他们声明的顺序执行，中间可以穿插属性的初始化。
     */
    class InitOrderDemo(name: String) {

        val firstProperty = "First property: $name".also(::println)

        init {
            println("First initializer block that prints ${name}")
        }

        val secondProperty = "Second property: ${name.length}".also(::println)

        init {
            println("Second initializer block that prints ${name.length}")
        }
    }

    /**
     * 注意：首要构造器的参数可以直接用于初始化区块。当然也可以用于属性初始化。
     */
    class Customer(name: String) {
        val customer = name.toUpperCase();
    }

    /**
     * 和其他常规的属性一样，首要构造器中的属性可以被声明为可变（var）或者只读（val）
     */
    class Person2(val firstName: String, val lastName: String, val age: Int) {
        // ...
    }

    /**
     * 如果构造器中有注解或者可见性修饰符，必须要有 constructor 关键字，修饰符位于它前面：
     */
    class Customer2 public @Inject constructor(name: String) {
        //...
        //
        }

    /**
     * 一个类也可以用 constructor 作为前缀来声明次要构造器（secondary constructor）：
     */
    class Person3 {
        var children: List<Person3> = ArrayList<Person3>()

        constructor(parent: Person3) {
//            parent.childre = (this);
        }
    }

    /**
     * 如果一个类有首要构造器，那么每个次要构造器都要代理到首要构造器，可以直接或者通过其他次要构造器间接实现。
     * 使用 this 关键字可以把调用代理到同一个类的其他构造器
     */
    class Person4(val name: String) {
        constructor(name: String, parent: Person4) : this(name) {
//            parent.children.add(this)
        }
    }

    /**
     * 注意：初始化区块里的代码会成为首要构造器的一部分。次要构造器的第一条语句会代理到首要构造器，
     * 因此初始化区块中的代码会优先执行。即使没有首要构造器，代理也会静默发生，所以初始化区块仍然会被执行到。
     */
    class Constructors {
        init {
            println("Init block")
        }

        constructor(i: Int) {
            println("Constructor")
        }
    }

    /**
     * 如果一个非抽象的类没有声明任何构造器（首要或次要），那么它会自动生成一个 public 的无参首要构造器。
     * 如果不想要 public 构造器，可以声明一个非默认可见性并且空的首要构造器。
     */
    class DontCreateMe private constructor () {
    }

    /**
     * 注意：在 JVM 上，如果首要构造器的所有参数都有默认值，编译器额外会生成一个使用默认值的无参构造器。
     * 这样可以方便 Jackson 或者 JPA 这样的库通过无参构造器来创建类的实例。
     */
    class Customer3(val customerName: String = "")

    /************************     继承    ********************************/
    class temp : Activity() {
    }

    open class Base(p: Int)

    class Derived(p: Int) : Base(p)

    class MyView : View {
        constructor(ctx: Context) : super(ctx)

        constructor(ctx: Context, attrs: AttributeSet) : super(ctx, attrs)
    }


    /**
     * 方法覆写
     * 就像前面提到的，Kotlin 倾向于让事物变得明确。与 Java 不同的是，对于可覆写的成员（可被称之为 open）
     * 和覆写本身，Kotlin 需要用注解明确地标记
     */
    open class Base1 {
        open fun v() {}
        fun nv() {}
    }

    /**
     * override 必须标记在 Derived.v() 上，否则会导致编译出错。如果函数没有 open 注解，
     * 像 Base.nv()，在子类中声明一个同样签名的方法是非法的，不管有没有用 override。final 类
     * （没有 open 注解的类）不允许有 open 成员。
     */
    class Derived1() : Base1() {
        override fun v() {}
    }

    open class AnotherDerived() : Base1() {
        /**
         * 用 override 标注的成员自身是 open，也就是说，它可以在子类中被覆写。如果想禁止再被重载，可以使用 final：
         */
        final override fun v() {}
    }

    /**
     * 属性覆写
     * 属性覆写的工作方式和方法覆写一样，在父类中声明的属性，如果想要在子类中重新声明，必须使用 override 标注，并且类
     * 型必须兼容。每个声明的属性都可以被覆写为自带初始化或者带有 getter 方法的属性。
     */
    open class Foo {
        open val x: Int get() {
            return x
        }
    }

    class Bar1 : Foo() {

//        override val x: Int = 3

        /**
         * 也可以用 var 属性来覆写 val 属性，但是反过来不可以。原因是：val 属性本质上声明了一个 getter 方法，
         * 用 var 覆写就等价于在继承的类中额外定义了一个 setter 方法。
         */
        override var x: Int = 3
    }

    /**
     * 注意，override 可以用在首要构造器的属性声明中。
     */
    interface Foo2 {
        val count: Int
    }
    class Bar2(override val count: Int) : Foo2
    class Bar3 : Foo2 {
        override var count: Int = 0
    }


    /**
     * 继承类的初始化顺序在构造一个继承类的实例时，完成基类的初始化是第一步（只在基类构造器参数的评估之后），
     * 因此要早于继承类执行它的初始化逻辑。
     *
     * 这就意味着，基类构造器在执行时，继承类所声明或覆写的属性还没有被初始化。如果这些属性被用到了基类的初始化逻辑中
     * （无论是直接还是间接地通过另一个覆写的 open 成员），可能会导致不正确的行为或者运行时失败。所以设计基类时，
     * 在构造器、属性初始化以及 init 块中要避免使用 open 成员。
     *
     */
    open class Base3(val name: String) {
        init { println("Initializing Base") }

        open val size: Int =
                name.length.also { println("Initializing size in Base: $it") }
    }
    class Derived3(
            name: String,
            val lastName: String
    ) : Base3(name.capitalize().also { println("Argument for Base: $it") }) {
        init { println("Initializing Derived") }

        override val size: Int =
                (super.size + lastName.length).also { println("Initializing size in Derived: $it") }
    }


    /**
     * 调用父类实现
     * 使用 super 关键字，子类中的代码可以调用父类的函数和属性访问器。
     */
    open class Foo4 {
        open fun f() { println("Foo4.f()") }
        open val x: Int get() = 1
    }

    class Bar4 : Foo4() {
        override fun f() {
            super.f();
            println("Bar4.f() $x")
        }

        override val x: Int get() = super.x + 1
    }


    /**
     * 内部类如果要访问外部类的父类，访问方式是 super 再加上外部类类名：
     */
    class Bar5 : Foo4() {
        override fun f() {
            println("Bar5.f() $x")
        }
        override val x: Int get() = 0

        inner class Baz {
            fun g() {
                super@Bar5.f()  // Calls Foo4's implementation of f()
                println(super@Bar5.x)
            }
        }
    }

    open class A {
        open fun f() { print("A.f()") }
        fun a() { println("A.a()") }
    }

    interface B {
        fun f() { println("B.f()") } // interface members are 'open' by default
        fun b() { println("B.b()") }
    }

    class C() : A(), B {
        // the compile requires f() to be overridden:
        override fun f() {
            super<A>.f() // call to A.f()
            super<B>.f() // call to B.f()
        }
    }

    /**
     * 抽象类
     * 类和它的某些成员可以声明为 abstract。类的抽象成员没有实现。我们没必要用 open 来标注一个抽象的类或函数，
     * 因此这是显而易见（It goes without saying）。
     * 我们把非抽象的 open 成员覆写为抽象的：
     */
    open class Base5 {
        open fun f() {}
    }
    abstract class Derived5 : Base5() {
        override abstract fun f()
    }
    class imp: Derived5() {
        override fun f() {
            TODO("Not yet implemented")
        }
    }


    /************************     继承    ********************************/
}