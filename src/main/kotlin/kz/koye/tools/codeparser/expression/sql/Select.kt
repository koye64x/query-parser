package kz.koye.tools.codeparser.expression.sql

import kz.koye.tools.codeparser.expression.Expression
import kz.koye.tools.codeparser.expression.ExpressionWriter

class Select: Expression {

    val queryColumns: List<QueryColumn> = mutableListOf()

    val tableItems: List<QueryTable> = mutableListOf()

    var whereCondition: Expression? = null

    override fun write(writer: ExpressionWriter) {
        writer.writeLine("select")
        writer.indentInc()
        queryColumns.forEachIndexed { index, queryColumn ->
            queryColumn.write(writer)
            if (index < queryColumns.lastIndex) {
                writer.write(", ")
            }
        }
        writer.indentDec()
        writer.write("from ")
        tableItems.forEach { it.write(writer) }
        whereCondition?.let {
            writer.writeLine("where")
            writer.indentInc()
            whereCondition!!.write(writer)
            writer.indentDec()
        }
        if (getGroupColumns().isNotEmpty()) {
            writer.write("group by ")
            getGroupColumns().forEachIndexed { index, queryColumn ->
                queryColumn.columnExpression.write(writer)
                if (index < getGroupColumns().lastIndex) {
                    writer.write(", ")
                }
            }
        }

    }

    fun getGroupColumns(): List<QueryColumn> {
        return listOf()
    }

}