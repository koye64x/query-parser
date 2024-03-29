package kz.koye.tools.codeparser.expression.sql

import kz.koye.tools.codeparser.expression.Expression
import kz.koye.tools.codeparser.expression.ExpressionWriter

class QueryColumn (

    val columnName: String?,

    val columnExpression: Expression

): Expression {
    override fun write(writer: ExpressionWriter) {
        TODO("Not yet implemented")
    }
}