package kz.koye.tools.codeparser.primitive

open class SourceSpace: SourceCodeItemType() {

    override fun matchStart(s: String): Boolean {
        return s.all { ch -> setOf(' ', '\n', '\r').contains(ch) }
    }

    override fun matchFull(s: String): Boolean {
        return true
    }

    override fun toString(): String {
        return "SPACE"
    }
}