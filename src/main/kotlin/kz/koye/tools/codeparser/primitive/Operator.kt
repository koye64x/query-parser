package kz.koye.tools.codeparser.primitive

abstract class Operator(private val operatorSymbols: String): SourceCodeItemType() {
    override fun matchStart(s: String): Boolean {
        return operatorSymbols.startsWith(s)
    }

    override fun matchFull(s: String): Boolean {
        return s == operatorSymbols
    }

    override fun toString(): String {
        return "OPR$operatorSymbols"
    }
}