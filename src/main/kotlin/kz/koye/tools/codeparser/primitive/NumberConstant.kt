package kz.koye.tools.codeparser.primitive

class NumberConstant: SourceCodeItemType() {
    override fun matchStart(s: String): Boolean {
        return s.all { ('0'..'9').contains(it) || it == '.' }
    }

    override fun matchFull(s: String): Boolean {
        return true
    }

    override fun toString(): String {
        return "NUM"
    }
}