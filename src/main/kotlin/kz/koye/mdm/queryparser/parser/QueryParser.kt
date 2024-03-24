package kz.koye.mdm.queryparser.parser

interface QueryParser {

    fun extractRelationShips(sourceCode: String): List<Relationship>

}