package kz.koye.tools.codeparser

import kz.koye.tools.codeparser.primitive.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

//@SpringBootTest
class SourceCodeParserTests {

    @Test
    fun parseText() {
        val parser = SourceCodeParser(
            listOf(
                Name(),
                NumberConstant(),
                SourceSpace(),
                TextConstant(),
                Bracket()
            )
        )
        val sourceCode = "foo name2 (3456) \"world\""
        val reader = SourceCodeReaderString(sourceCode)
        val res = parser.parse(reader)
        assertEquals(res.size, 7)
        assertTrue(res[0].sourceCodeItemType is Name)
        assertEquals(res[0].value, "foo")
        assertEquals(res[0].position, Position(0, 0, 0))

        assertTrue(res[1].sourceCodeItemType is SourceSpace)
        assertEquals(res[1].value, " ")
        assertEquals(res[1].position, Position(3, 0, 3))
    }
}