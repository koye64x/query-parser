package kz.koye.mdm.queryparser.parser.spark

import kz.koye.mdm.queryparser.parser.QueryParser
import kz.koye.mdm.queryparser.parser.Relationship
import kz.koye.mdm.queryparser.parser.Term
import java.util.*

class SparkParser: QueryParser {

    override fun extractRelationShips(sourceCode: String): List<Relationship> {
        val res = listOf<Relationship>()
        val variables = extractLocalVariables(sourceCode)
        return res
    }

    fun extractLocalVariables(sourceCode: String): List<Term> {
        var subSourceCode = sourceCode
        /*while (val variable = extractNextVariable(sourceCode, subSourceCode)) {

        }*/
        return listOf()
    }

    fun extractNextVariable(sourceCode: String, subSourceCode: String): Optional<Pair<Term, Int>> {
        val signVal = "val "
        val signVar = "var "
        val searchItems = listOf(signVal, signVar).map { it to findTermPosBySignEnd(sourceCode, signVal) }.filter { it.second != -1 }
        if (searchItems.isEmpty()) {
            return Optional.empty()
        }
        val firstSign = searchItems.minBy { it.second }
        val optionalVariableNameAndOffset = extractVariableName(sourceCode.substring(firstSign.second + firstSign.first.length))
        if (optionalVariableNameAndOffset.isEmpty) {
            throw IllegalStateException("Не найдено имя переменной после позиции ${firstSign.second} '${sourceCode.part()}'")
        }
        val variableName = optionalVariableNameAndOffset.get().first
        val variableExpression = extractVariableExpression(sourceCode.substring(optionalVariableNameAndOffset.get().second))
        return Optional.empty()
    }

    companion object {

        val parenthesesLeft = '('
        val parenthesesRight = ')'
        val curlyBracketsLeft = '{'
        val curlyBracketsRight = '}'
        val brackets = mapOf(
            parenthesesLeft to parenthesesRight,
            curlyBracketsLeft to curlyBracketsRight
        )

        val doubleQuotes = '"'

        val methodDelimiter = '.'

        fun findTermPosBySignEnd(sourceCode: String, signEnd: String): Int {
            val p = sourceCode.indexOf(signEnd)
            if (p == -1) {
                return -1
            }
            if (isStartOfTerm(sourceCode, p)) {
                return p
            }
            return findTermPosBySignEnd(sourceCode.substring(p), signEnd)
        }

        fun isStartOfTerm(sourceCode: String, position: Int): Boolean {
            if (position == 0) {
                return true
            }
            val prevChar = sourceCode[position]
            return !isTermSymbol(prevChar)
        }

        fun isTermSymbol(c: Char): Boolean {
            return c in 'A'..'Z'
                    || c in 'a'..'z'
                    || c in '0'..'9'
        }

        fun isExpressionItemSymbol(c: Char): Boolean {
            return isTermSymbol(c) || c == parenthesesLeft || c == parenthesesRight
        }

        fun extractVariableName(sourceCode: String): Optional<Pair<String, Int>> {
            val pStart = findVariableStart(sourceCode)
            if (pStart == -1) {
                return Optional.empty()
            }
            var pEnd = findVariableEnd(sourceCode.substring(pStart))
            if (pEnd == -1) {
                return Optional.empty()
            }
            return Optional.of(Pair(sourceCode.substring(pStart, pEnd), pEnd))
        }

        fun findVariableStart(sourceCode: String): Int {
            for (i in sourceCode.indices) {
                if (isTermSymbol(sourceCode[i])) {
                    return i
                }
            }
            return -1
        }

        fun findVariableEnd(sourceCode: String): Int {
            for (i in sourceCode.indices) {
                if (!isTermSymbol(sourceCode[i])) {
                    return i
                }
            }
            return -1
        }

        fun String.part(): String {
            return this.substring(0, 100.coerceAtMost(this.length)) + "..."
        }

        fun extractVariableExpression(sourceCode: String): Pair<String, Int> {
            val pAssigment = sourceCode.indexOf("=")
            if (pAssigment == -1) {
                throw IllegalStateException("Не найден оператор присваивания '=' в исходном коде '${sourceCode.part()}'")
            }
            var bracketsStack = ""
            var doubleQuoteOn = false
            var methodDelimiterOn = false
            var expressionItemOn = false
            for (i in sourceCode.indices) {
                val c = sourceCode[i]
                if (i > pAssigment) {
                    if (!doubleQuoteOn) {
                        if (isBracketStart(c)) {
                            bracketsStack = bracketsStack + c
                        } else if (isBracketEnd(c)) {
                            bracketsStack = closeBracket(bracketsStack, c)
                        } else if (isExpressionItemSymbol(c)) {
                            if (!expressionItemOn) {
                                expressionItemOn = true
                            }
                        } else {

                        }
                    }
                    if (c == doubleQuotes) {
                        doubleQuoteOn = !doubleQuoteOn
                    }

                }


            }
            return Pair("too", 0)
        }

        fun isBracketStart(c: Char): Boolean {
            return brackets.containsKey(c)
        }

        fun isBracketEnd(c: Char): Boolean {
            return brackets.containsValue(c)
        }

        fun closeBracket(brackets: String, c: Char): String {
            if (brackets.isEmpty()) {
                throw IllegalStateException("Ошибка закрытия скобки. Стек скобок пустой")
            }
            if (brackets.last() != c) {
                throw IllegalStateException("Ошибка закрытия скобки. Символ закрытия скобки не сообветствует скобки открытия")
            }
            return brackets.substring(0, brackets.length - 1)
        }

    }

}