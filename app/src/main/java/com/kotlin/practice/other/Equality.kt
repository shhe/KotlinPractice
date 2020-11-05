package com.kotlin.practice.other

/**
 *
 * @author shhe
 * @Date 2020/10/29 下午7:56
 * @Description:
 */
class Equality {
    /**
     * 如果 `a` 不为 `null`，`equals(Any?)` 会被调用，否则（`a` 是 `null`）会检查 `b` 跟 `null` 是否是引用相等。
     */
    fun test(a: String?, b: String?) {
        var temp = a?.equals(b) ?: (b === null);
    }
}