package creational

/**
 * Provides an interface for creating objects in
 * a superclass, but allows subclasses to alter
 * the type of objects that will be created
 */

fun main() {
    val parserFactory = StandardFileParserFactory()
    val parser = parserFactory.createFromFileName("filename.json")
    parser.parse()
}

interface FileParser {
    fun parse()
}

class XmlFileParser : FileParser {
    override fun parse() {
        println("XmlFileParser")
    }
}

class JsonFileParser : FileParser {
    override fun parse() {
        println("JsonFileParser")
    }
}

interface FileParserFactory {
    fun createFromFileName(fileName: String): FileParser
}

class StandardFileParserFactory : FileParserFactory {

    override fun createFromFileName(fileName: String): FileParser =
        when (fileName.substringAfterLast('.')) {
            "xml" -> XmlFileParser()
            "json" -> JsonFileParser()
            else -> throw Exception("I don't know how to deal with $fileName.")
        }
}