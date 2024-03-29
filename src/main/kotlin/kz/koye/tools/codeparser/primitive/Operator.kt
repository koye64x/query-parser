package kz.koye.tools.codeparser.primitive

/**
 * Шаблон арифметических операторов
 */
abstract class Operator(
    private val operatorSymbols: String
): TemplateStrictCharSeq("OPER", operatorSymbols) {
    override fun matchStart(s: String): Boolean {
        return operatorSymbols.startsWith(s)
    }

    override fun matchFull(s: String): Boolean {
        return s == operatorSymbols
    }

}