package kz.koye.tools.codeparser

class Node<T>(owner: Node<T>?) {

    val members: List<Node<T>> = mutableListOf()

}