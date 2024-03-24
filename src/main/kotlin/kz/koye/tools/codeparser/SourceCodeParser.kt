package kz.koye.tools.codeparser

import kz.koye.tools.codeparser.primitive.SourceCodeItem
import kz.koye.tools.codeparser.primitive.SourceCodeItemType

class SourceCodeParser(
    val sourceCodeItemTypes: List<SourceCodeItemType>
) {

    fun parse(reader: SourceCodeReader): List<SourceCodeItem> {
        val res = mutableListOf<SourceCodeItem>()
        var s = ""
        var currentTypes: List<SourceCodeItemType> = listOf()
        var sPosition = reader.getPosition()
        var currentTypesNext: List<SourceCodeItemType> = listOf()
        while (reader.hasNext()) {
            val currentSymbol = reader.next()
            currentTypesNext = currentTypes.filter { it.matchStart(s + currentSymbol) }
            //println("sPosition=$sPosition s='$s', currentSymbol='$currentSymbol', currentTypes=$currentTypes, currentTypesNext=$currentTypesNext")
            if (currentTypes.size == 0) {
                s = currentSymbol.toString()
                sPosition = reader.getPosition()
                currentTypes = sourceCodeItemTypes.filter { it.matchStart(s) }
            } else if (currentTypes.size == 1) {
                if (currentTypesNext.size == 0) {
                    res.add(SourceCodeItem(s, currentTypes[0], sPosition))
                    s = currentSymbol.toString()
                    sPosition = reader.getPosition()
                    currentTypes = sourceCodeItemTypes.filter { it.matchStart(s) }
                } else {
                    s += currentSymbol
                    currentTypes = currentTypesNext
                }
            } else {
                if (currentTypesNext.size == 0) {
                    throw IllegalStateException("Ошибка парсинга. Подстрока '${s + currentSymbol}' не соответствует ни одному шаблону (позиция $sPosition)")
                } else {
                    s = currentSymbol.toString()
                    currentTypes = currentTypesNext
                }
            }
        }
        if (currentTypesNext.size == 1 && currentTypesNext[0].matchFull(s)) {
            res.add(SourceCodeItem(s, currentTypes[0], sPosition))
        }
        return res
    }
}