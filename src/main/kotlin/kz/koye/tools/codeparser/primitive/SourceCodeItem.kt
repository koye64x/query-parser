package kz.koye.tools.codeparser.primitive

import kz.koye.tools.codeparser.Position

class SourceCodeItem(
    val value: String,
    val sourceCodeItemType: SourceCodeItemType,
    val position: Position
) {
    override fun toString(): String {
        return "'$value'($sourceCodeItemType)"
    }
}