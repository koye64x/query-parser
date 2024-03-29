package kz.koye.tools.codeparser

import kz.koye.tools.codeparser.primitive.*
import kz.koye.tools.codeparser.primitive.sql.TextConstantSql
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

//@SpringBootTest
class SourceCodeParserTests {

    @Test
    fun parseText() {
        val parser = SourceCodeParser(
            listOf(
                Name,
                NumberConstant('.'),
                WhiteSpace,
                TextConstantSql(),
                RoundBracket,
                Comma
            ) + listOf("=", ">=", "<=", "<", ">", "<>", "+", "-", "*", "/", "||").map { Operator(it) }
        )
        val sourceCode = "foo name2 (3456) \"world\""
        val reader = SourceCodeReaderString(sourceCode)
        val res = parser.parse(reader)
        assertEquals(res.size, 7)
        assertTrue(res[0].sourceCodeItemTemplate is Name)
        assertEquals(res[0].value, "foo")
        assertEquals(res[0].position, Position(0, 0, 0))

        assertTrue(res[1].sourceCodeItemTemplate is WhiteSpace)
        assertEquals(res[1].value, " ")
        assertEquals(res[1].position, Position(3, 0, 3))
    }
}