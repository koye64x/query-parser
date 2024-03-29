package kz.koye.tools.codeparser.primitive

/**
 * Шаблон для последовательности из набора заданных символов
 */
abstract class TemplateCharSet(
    sign: String,
    val charSet: String
): SourceCodeItemTemplate(sign) {

    init {
        if (charSet.isEmpty()) {
            throw IllegalStateException("Constructor parameter 'charSet' must not be empty")
        }
    }

    override fun matchStart(s: String): Boolean {
        return s.all { charSet.contains(it) }
    }

    override fun matchFull(s: String): Boolean {
        return matchStart(s)
    }

}