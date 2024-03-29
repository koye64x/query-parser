package kz.koye.tools.codeparser

import java.io.Closeable

interface SourceCodeReader: Iterator<Char>, Closeable {
    fun getPosition(): Position
}