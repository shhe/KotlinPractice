package com.kotlin.practice

/**
 *
 * @author shhe
 * @Date 2020/10/21 上午9:21
 * @Description:
 */
class Node {
    var parent: String? = null
    var name: String? = null

    /*companion object {
        fun getParent(node: Node): String? {
            return node.parent
        }

        *//*fun getName(node: Node): String? {
            return node.name
        }*//*
    }*/

    /*val parent: String?
        get() {
            return parent
        }*/

}

fun Node.getParent(): String? {
    return parent
}

fun Node.getName(): String? {
    return name
}