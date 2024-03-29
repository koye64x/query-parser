package kz.koye.tools.codeparser.primitive

/**
 * Шаблок текста заключенного в кавычки
 */
abstract class TextConstant(
    val quoteSymbol: Char,
    val escapeSymbol: Char
) : SourceCodeItemTemplate("TXT") {
    override fun matchStart(s: String): Boolean {
        if (s[0] != quoteSymbol) {
            return false
        }
        if (s.length > 1) {
            val escapeIndexes = mutableSetOf<Int>()
            for (i in 1..s.lastIndex) {
                if (s[i] == escapeSymbol && !escapeIndexes.contains(i-1)) {
                    escapeIndexes.add(i)
                }
                if (s[i] == quoteSymbol && !escapeIndexes.contains(i-1)) {
                    return i == s.lastIndex
                }
            }
            return true
        } else {
            return true
        }
    }

    override fun matchFull(s: String): Boolean {
        return s.length > 1
                && matchStart(s.substring(0, s.lastIndex-1))
                && s.last() == quoteSymbol
    }

}