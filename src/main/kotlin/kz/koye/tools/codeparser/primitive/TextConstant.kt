package kz.koye.tools.codeparser.primitive

class TextConstant: SourceCodeItemType() {
    override fun matchStart(s: String): Boolean {
        if (s[0] != '"') {
            return false
        }
        if (s.length > 1) {
            val escapeIndexes = mutableSetOf<Int>()
            for (i in 1..s.lastIndex) {
                if (s[i] == getEscapeSymbol() && !escapeIndexes.contains(i-1)) {
                    escapeIndexes.add(i)
                }
                if (s[i] == '"' && !escapeIndexes.contains(i-1)) {
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
                && s.last() == '"'
    }

    fun getEscapeSymbol(): Char {
        return '"'
    }

    override fun toString(): String {
        return "TXT"
    }

}