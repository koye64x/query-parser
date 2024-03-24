package kz.koye.tools.codeparser

class SourceCodeReaderString(val s: String): SourceCodeReader {
    var p = -1
    override fun getPosition(): Position {
        return Position(p, 0, p)
    }

    override fun hasNext(): Boolean {
        return p < s.length -1
    }

    override fun next(): Char {
        p += 1
        return s[p]
    }
}