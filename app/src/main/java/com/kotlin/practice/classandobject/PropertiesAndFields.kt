package com.kotlin.practice.classandobject

import com.kotlin.practice.annotation.Inject

/**
 *
 * @author shhe
 * @Date 2020/10/21 下午3:51
 * @Description: 属性和字段
 */
class PropertiesAndFields {

    class Address {
        var name: String = "..."
        var street: String = "..."
        var city: String = "..."
        var state: String = "..."
        var zip: String = "..."
    }

    /**
     * 可以用名字指代一个属性，就好像 java 的字段：
     */
    fun copyAddress(address: Address): Address {
        val result = Address()  // there's no 'new' keyword in Kotlin
        result.name = address.name // accessors are called
        result.street = address.street
        // ...
        return result
    }

    /**
     * Getter 和 Setter
    声明一个属性的完整语法如下：

    var <propertyName>[: <PropertyType>] [= <property_initializer>]
    [<getter>]
    [<setter>]

    initializer、getter 和 setter 是可选的。属性类型如果能从 initializer 或者 getter 的返回值中推导得出，也可以省略。

     */

//    var allByDefault: Int?  // error: explicit initializer required, default getter and setter implied
    var initialized = 1 // has type Int, default getter and setter

//    val simple: Int? // has type Int, default getter, must be initialized in constructor
    val inferredType = 1 // has type Int and a default getter


    var size: Int = 1;
    // 我们可以在属性的声明中自定义访问器，跟普通函数非常类似。自定义 getter 的方式如下：
    val isEmpty: Boolean
        get() = this.size == 0

    // 自定义 setter 的方式如下：
    var stringRepresentation: String
        get() = this.toString()
        set(value) {
            setDataFromString(value) // parses the string and assigns values to other properties
        }
    fun setDataFromString(value: String) {
        this.stringRepresentation = value
    }


    // 如果需要改变访问器的可见性或者添加注解，但是并不需要改变它的实现，可以直接定义访问器，并不需要具体实现。
    var setterVisiblity: String = "abc"
        private set // the setter is private and has the default implementation

    var setterWithAnnotation: Any? = null
        @Inject set // annotate the setter with Inject


    /**
     * 幕后字段
     *
     * Kotlin 类中无法直接声明字段。但是，当属性需要幕后字段时，Koltin 会自动提供。在访问器中可以通过 field 标识符来引用幕后字段：
     *
     * field 标识符只能用在属性的访问器中。
     *
     */
    var counter = 0 // the initializer value is written directly to the backing field
        set(value) {
            if (value >= 0) field = value
        }

    /**
     * 幕后属性
     *
     * 如果你不想落入“隐式幕后字段”的窠臼，可以退而求其次，使用幕后属性：
     */
    private var _table: Map<String, Int>? = null
    public val table: Map<String, Int>
        get() {
            if (_table == null) {
                _table = HashMap() // Type parameters are inferred
            }
            return _table ?: throw AssertionError("Set to null by another thread")
        }


    /**
     * 编译时常量
    如果一个属性在编译时可以确定它的取值，那么可以使用 const 修饰符标记为 编译时常量。这种属性需要满足如下条件：

    顶层或者一个 object 的成员
    初始化为一个 String 或者基本类型的值
    没有自定义 getter
     */
    @Deprecated(SUBSYSTEM_DEPRECATED) fun foo() {
        //
    }

    companion object {
        const val SUBSYSTEM_DEPRECATED: String = "This subsystem is deprecated"
    }


    // 延迟初始化属性和变量
    class TestSubject {

    }
    public class MyTest {
        lateinit var subject: TestSubject

        /*@SetUp fun setup() {
            subject = TestSubject()
        }

        @Test fun test() {
            subject.method()    // dereference directly
        }*/
    }
}