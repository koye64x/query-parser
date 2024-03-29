package kz.koye.tools.codeparser.primitive

abstract class SourceCodeItemTemplate(
    val sign: String
) {

    init {
        if (sign.isEmpty()) {
            throw IllegalStateException("Constructor parameter 'sign' must not be empty")
        }
    }

    abstract fun matchStart(s: String): Boolean

    abstract fun matchFull(s: String): Boolean

    override fun toString(): String {
        return sign
    }

}