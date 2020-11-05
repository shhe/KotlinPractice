package com.kotlin.practice.other

import android.content.Context
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 *
 * @author shhe
 * @Date 2020/11/2 下午1:54
 * @Description:
 */
class StandardPractice {

    fun runDemo() {
        println("测试run方法")
    }

    /**
     * 但凡涉及到需要传递的代码块参数，都可以省略不传递，对于参数只是一个代码块的时候，
     * 可以直接用 ::fun【方法】 的形式传递到 () 中。
     *
     *
     * 代码块格式为block: ()这种的，用 () 设置的方法必须是不含有参数的，
     * 例如上面的 runDemo() 方法就没有参数。
     */
    fun runDemoTest() {
        run(::runDemo)
    }

    /**
     * 此处是执行一个 T 类型的 run 方法，传递的依然是一个代码块，
     * 只是内部执行的是 T 的内部一个变量 或 方法等，返回的是 一个 R 类型
     */
    fun runTest() {
        val str = "hello"
        val len = str.run {
//            plus("123")
            length
        }
        println("$str length is $len")
    }
    /**
     * 上面例子，一个字符串 str，我们执行 str 的 run 方法，此时在 run 方法中，
     * 我们可以调用 String 类中的一些方法，例如调用 length 返回的是一个 Int 类型结果。
     *
     *
     * 这种在执行一个类的中多个方法的时候，并且要求返回一个结果的时候，使用这个run方法能够节省很多代码量。
     *
     *
     * 同样的，对于方法传递的是一个代码块的函数而言，如果其传递的代码块格式是 block: T.() 这种，
     * 我们可以使用 ::fun 的形式传递到 () 中，只是这个传递的方法要求必须含有一个参数传递。
     */
    fun runTest2() {
        val str = "hello"
        str.run(::println)
    }
//    println 函数的源码如下
    /*@kotlin.internal.InlineOnly
    public actual inline fun println(message: Any?) {
        System.out.println(message)
    }*/


    /**
     * with() 方法接收一个类型为 T 的参数和一个代码块
     * 经过处理返回一个 R 类型的结果
     * 这个其实和上面的 T.run() 方法很类似，只是这里将 T 传递到了with() 方法当中
     */
    fun withTest() {
        val str = "hello"
        val ch = with(str) {
            get(0)
        }
        println(ch) //打印 h
    }

    fun withTest2() {
        val str = "hello"
        val ch = with(str, ::printWith)
        println(ch)
    }
    fun printWith(str: String): Char? {
        return if (str.isEmpty()) null else str[0]
    }


    /**
     * with 用途
     */
    class Preference<T>(val context: Context, val name: String, val default: T, val prefName: String = "default") :
            ReadWriteProperty<Any?, T> {

        private val prefs by lazy {
            context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
        }

        //注解消除警告
        @Suppress("IMPLICIT_CAST_TO_ANY", "UNCHECKED_CAST")
        override fun getValue(thisRef: Any?, property: KProperty<*>): T {
            return when (default) {
                is String -> prefs.getString(name, default)
                is Int -> prefs.getInt(name, default)
                is Long -> prefs.getLong(name, default)
                is Float -> prefs.getFloat(name, default)
                else -> throw IllegalStateException("Unsupported data.")
            } as T
        }


        override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
            with(prefs.edit()) {
                when (value) {
                    is String -> putString(name, value)
                    is Int -> putInt(name, value)
                    is Long -> putLong(name, value)
                    is Float -> putFloat(name, value)
                    else -> throw IllegalStateException("Unsupported data.")
                }
            }.apply()
        }
    }


    /**
     * 执行一个 T 类型中的方法，变量等，然后返回自身 T
     * 注意参数 block: T.()，但凡看到 block: T.() -> 这种代码块，
     * 意味着在大括号 {} 中可以直接调用T内部的 API 而不需要在加上 T. 这种【实际上调用为 this. ，this. 通常省略】
     */
    fun applyTest() {
        val str = "hello"
        str.apply { length }  //可以省略 str.
        str.apply { this.length } //可以这样

        // block: T.()格式代码块，因此同样可以这么写:
        str.apply(::println)
    }

    fun applyTest2() {
        var str: String? = "hello"
        //一系列操作后。。。
        /**
         * 如果字符串 str 不为空直接打印出来，如果为空则打印 结果为空
         */
        str?.apply(::println) ?: println("结果为空")
    }

    /**
     * 执行一个 T 类型中的方法，变量等，然后返回自身 T
     * 这个方法与上面的 apply 方法类似，只是在大括号中执行 T 自身方法的时候，必须要加上 T.
     * 否则无法调用 T 中的 API,什么意思呢？看下面代码：
     */
    fun alsoTest() {
        val str = "hello"
        str.also { str.length }  //str.必须加上，否则编译报错
        str.also { it.length }   //或者用 it.

        /**
         * 上面代码中 {} 中使用了 it 来代替 str，其实我们还可以手动指定名称：
         */
        // {}中的s代表的就是str
        str.also { s -> s.length }
    }

    /**
     * 另外，需要注意的是also入参的代码块样式：block: (T)，这种样式跟 block: T.()一样，
     * 可以使用 ::fun 的形式传递到 () 中，只是这个传递的方法要求必须含有一个参数传递,因此我们可以这样操作：
     */
    fun alsoTest2() {
        val str = "hello"
        str.also(::println)
    }


    /**
     * 分析： let 方法与上面的 also 方法及其类似，只是 also 方法返回的结果是自身，
     * 而 let 方法是传递类型 T 返回另外一个类型 R 形式，因此在用法上也很类似
     */
    fun letTest() {
        var str:String? = "hello"

        //...一堆逻辑执行后
        val len = str?.let { it.length }

        str.let(::println)
    }


    /**
     * 根据传递的参数 T 做内部判断，根据判断结果返回 null 或者 T 自身
     * 传递的是【一元谓词】代码块，像极了 C++ 中的一元谓词：方法只含有一个参数，并且返回类型是Boolean类型
     * 源码中，通过传递的一元谓词代码块进行判断，如果是 true 则返回自身，否则返回 null
     */
    fun takeIfTest() {
        val str = "helloWorld"
        str.takeIf { str.contains("hello") }?.run(::println)
    }

    /**
     * 上面代码{}中判断字符串是否包含 "hello"，是则返回自己，不是则返回 null，因此可以使用?来判断，如果不为null，可以使用前面说的 run() 方法进行简单打印操作。
     * 同样的，因为接收的代码块是一个一元谓词形式，因此，如果想要使用 (::fun) 方式来替代 {}，则对应的函数方法必须满足两个条件：
     *
     * 1、返回值类型是 Boolean 类型
     * 2、方法必须含有一个参数
     *
     * 因此可以写成下面这种：
     */
    fun takeIfTest2() {
        val str = "helloWorld"
        str.takeIf(::printTakeIf)?.run(::println)
    }
    fun printTakeIf(str: String): Boolean {
        return str.contains("hello")
    }


    /**
     * 分析：repeat 方法包含两个参数：

    第一个参数int类型，重复次数，
    第二个参数,表示要重复执行的对象
    该方法每次执行的时候都将执行的次数传递给要被重复执行的模块，至于重复执行模块是否需要该值，需要根据业务实际需求考虑，例如：

     */
    fun repeatTest() {
        //打印从0 到 100 的值，次数用到了内部的index
        repeat(100) {
            print(it)
        }

        //有比如，单纯的打印helloworld 100 次，就没有用到index值
        repeat(100){
            println("helloworld")
        }
    }

    /**
     * 注意看传递的代码块格式：action: (Int)，这就说明了要想使用(::fun)形式简化{}部分，需要代码块满足一个条件：
     *
     * 方法传递的参数有且只有一个 Int 类型或者 Any 的参数
     */
    fun repeatTest2() {
        repeat(100, ::print)
        repeat(100, ::printRepeat)
    }
    fun printRepeat(int: Int) {
        print(int)
    }

    /**
     * 总结时刻
    不管是 Kotlin 中内置的高阶函数，还是我们自定义的，其传入的代码块样式，无非以下几种：

    block: () -> T 和 block: () -> 具体类型
    这种在使用 (::fun) 形式简化时，要求传入的方法必须是无参数的，返回值类型如果是T则可为任意类型，否则返回的类型必须要跟这个代码块返回类型一致
    block: T.() -> R 和  block: T.() -> 具体类型
    这种在使用 (::fun) 形式简化时，要求传入的方法必须包含一个T类型的参数，返回值类型如果是R则可为任意类型，否则返回的类型必须要跟这个代码块返回类型一致。例如 with 和 apply 这两个方法
    block: (T) -> R 和  block: (T) -> 具体类型
    这种在使用 (::fun) 形式简化时，要求传入的方法必须包含一个T类型的参数，返回值类型如果是R则可为任意类型，否则返回的类型必须要跟这个代码块返回类型一致。例如 let 和 takeIf 这两个方法

    只有搞清楚上面这三种代码块格式及其用法，对应的其他的一些例如 Strings.kt 中的 filter、takeWhile、flatMap 等一系列高阶函数，都能快速掌握。

     */
}