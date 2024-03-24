package kz.koye.tools.codeparser

interface SourceCodeReader: Iterator<Char> {
    fun getPosition(): Position
}