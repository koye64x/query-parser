package kz.koye.tools.codeparser.expression.sql

import kz.koye.tools.codeparser.expression.Expression
import kz.koye.tools.codeparser.expression.ExpressionWriter

class TableCTE(
    val tableName: String,
    val query: Select
): Expression {
    override fun write(writer: ExpressionWriter) {
        writer.write(tableName)
    }

}