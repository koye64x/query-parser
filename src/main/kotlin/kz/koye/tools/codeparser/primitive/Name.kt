package kz.koye.tools.codeparser.primitive

class Name: SourceCodeItemType() {

    override fun matchStart(s: String): Boolean {
        var res = matchFirstSymbol(s[0])
        if (s.length > 1) {
            res = !s.substring(1).any { !matchOtherSymbol(it) }
        }
        return res
    }

    override fun matchFull(s: String): Boolean {
        return true
    }

    companion object {
        val letters = 'a'..'z'
        val lettersUpper = 'A'..'Z'
        val numbers = '0'..'9'
        val otherSymbols = "_"

        fun matchFirstSymbol(c: Char): Boolean {
            return letters.contains(c) || lettersUpper.contains(c)
        }

        fun matchOtherSymbol(c: Char): Boolean {
            return matchFirstSymbol(c)
                    || numbers.contains(c)
                    || otherSymbols.contains(c)
        }
    }

    override fun toString(): String {
        return "NAME"
    }
}

