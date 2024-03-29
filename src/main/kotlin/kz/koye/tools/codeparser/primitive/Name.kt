package kz.koye.tools.codeparser.primitive

/**
 * Шаблон для имен переменных
 */
object Name: TemplateFirstChar(
    "NAME",
    firstCharSymbols = ('a'..'z').toString() + ('A'..'Z').toString() + "_",
    otherCharSymbols = ('a'..'z').toString() + ('A'..'Z').toString() + "_" + ('0'..'9').toString()
)

