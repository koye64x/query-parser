package kz.koye.tools.codeparser.primitive

/**
 * Шаблон последовательности символов, в которой первый символ отличается от остальных
 */
abstract class TemplateFirstChar(
    sign: String,
    val firstCharSymbols: String,
    val otherCharSymbols: String
): SourceCodeItemTemplate(sign) {

    init {
        if (firstCharSymbols.isEmpty()) {
            IllegalStateException("First char symbols string must not be empty")
        }
        if (otherCharSymbols.isEmpty()) {
            IllegalStateException("Other char symbols string must not be empty")
        }
        if (!firstCharSymbols.any { !otherCharSymbols.contains(it) }) {
            IllegalStateException("Characters 'firstCharSymbols' must not be completely repeated in 'otherCharSymbols'")
        }
    }

    override fun matchStart(s: String): Boolean {
        if (s.isEmpty()) {
            throw IllegalArgumentException("Parameter must not be empty")
        }
        return firstCharSymbols.contains(s[0])
                && s.length > 1 && s.substring(1).all { otherCharSymbols.contains(it) }
    }

    override fun matchFull(s: String): Boolean {
        return matchStart(s)
    }
}