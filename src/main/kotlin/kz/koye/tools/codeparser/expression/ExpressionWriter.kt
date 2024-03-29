package kz.koye.tools.codeparser.expression

interface ExpressionWriter {

    fun writeLine(s: String)

    fun write(s: String)

    fun indentInc()

    fun indentDec()

}