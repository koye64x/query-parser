package kz.koye.tools.codeparser.primitive

/**
 * Шаблон для скобок
 */
abstract class Bracket(private val bracketSymbols: String) : SourceCodeItemTemplate("BRACKET") {

    init {
        if (bracketSymbols.length != 2) {
            throw IllegalStateException("Ошибка инициализации. Символов инициализации должно быть 2")
        }
        if (bracketSymbols[0] == bracketSymbols[1]) {
            throw IllegalStateException("Ошибка инициализации. Левый и правый символы инициализации должны быть разными")
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