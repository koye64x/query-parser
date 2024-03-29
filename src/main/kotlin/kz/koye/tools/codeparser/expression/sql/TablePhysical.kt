package kz.koye.tools.codeparser.expression.sql

import kz.koye.tools.codeparser.expression.Expression

class TablePhysical(
    val schemaName: String?,
    val tableName: String
): Expression {
    override fun toString(): String {
        return (schemaName?.let { "$it." } ?: kotlin.run { "" }) + tableName
    }
}