package com.kotlin.practice.other

/**
 *
 * @author shhe
 * @Date 2020/10/29 下午4:40
 * @Description:
 */
class Collections {
    fun test() {
        val numbers: MutableList<Int> = mutableListOf(1, 2, 3)
        val readOnlyView: List<Int> = numbers
        println(numbers)        // prints "[1, 2, 3]"
        numbers.add(4)
        println(readOnlyView)   // prints "[1, 2, 3, 4]"
//        readOnlyView.clear()    // -> does not compile

        val strings = hashSetOf("a", "b", "c", "c")
        assert(strings.size == 3)
    }

    fun testList() {
        val items = listOf(1, 2, 3, 4)
        items.first() == 1
        items.last() == 4
        items.filter { it % 2 == 0} // returns [2, 4]
    }

    /**
     * `toList` 这个扩展方法只是拷贝了一份 list 的元素，如此一来，返回的 list 绝对不会改变。
     */
    class Controller {
        private val _items = mutableListOf<String>()
        val items: List<String> get() = _items.toList()
    }

    fun test2() {
        val rwList = mutableListOf(1, 2, 3)
        rwList.requireNoNulls()     // returns [1, 2, 3]
        if (rwList.none { it > 6 }) println("No items above 6") // prints "No items above 6"
        val item = rwList.firstOrNull()
    }

    fun testMap() {
        val readWriteMap = hashMapOf("foo" to 1, "bar" to 2)
        println(readWriteMap["foo"])
        val snapshot: Map<String, Int> = HashMap(readWriteMap)
        println(snapshot)
    }
}