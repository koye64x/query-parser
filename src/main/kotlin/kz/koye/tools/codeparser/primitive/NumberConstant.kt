package kz.koye.tools.codeparser.primitive

/**
 * Шаблон для числовых значений
 */
class NumberConstant(
    val decimalDelimiter: Char
) : TemplateCharSet("NUM", ('0'..'9').toString() + decimalDelimiter) {
    override fun matchStart(s: String): Boolean {
        return s.all { ('0'..'9').contains(it) || it == '.' }
    }

    override fun matchFull(s: String): Boolean {
        return true
    }

}