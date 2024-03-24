package kz.koye.tools.codeparser.primitive

abstract class SourceCodeItemType {

    abstract fun matchStart(s: String): Boolean

    abstract fun matchFull(s: String): Boolean

}