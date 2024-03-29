package kz.koye.tools.codeparser.primitive

/**
 * Шаблон строгой последовательности символов
 */
abstract class TemplateStrictCharSeq(
    sign: String,
    val charSeq: String
): SourceCodeItemTemplate(sign) {
    override fun matchStart(s: String): Boolean {
        return s.length < charSeq.length && charSeq.startsWith(s)
    }

    override fun matchFull(s: String): Boolean {
        return charSeq == s
    }
}