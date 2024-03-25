package kz.koye.tools.codeparser.primitive

abstract class Bracket(private val bracketSymbols: String) : SourceCodeItemType() {

    init {
        if (bracketSymbols.length != 2) {
            throw IllegalArgumentException("Ошибка инициализации. Символа инициализации должно быть 2")
        }
    }
    override fun matchStart(s: String): Boolean {
        return s.length == 1 && bracketSymbols.contains(s[0])
    }

    override fun matchFull(s: String): Boolean {
        return matchStart(s)
    }

    override fun toString(): String {
        return "BRACKET"
    }

    fun isLeft(symbol: Char): Boolean {
        return bracketSymbols[0] == symbol
    }
}