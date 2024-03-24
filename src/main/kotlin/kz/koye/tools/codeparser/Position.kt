package kz.koye.tools.codeparser

class Position(
    val absoluteIndex: Int,
    val rowIndex: Int,
    val symbolIndex: Int
) {
    override fun equals(other: Any?): Boolean {
        if (other == null) {
            return false
        }
        if (other !is Position) {
            return false
        }
        return (absoluteIndex == other.absoluteIndex)
            && (rowIndex == other.rowIndex)
            && (symbolIndex == other.symbolIndex)
    }

    override fun toString(): String {
        return "($rowIndex, $symbolIndex)"
    }
}