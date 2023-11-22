package creational

/**
 * Provides an interface for creating objects in
 * a superclass, but allows subclasses to alter
 * the type of objects that will be created
 *
 * Advantages:
 *
 *     Encapsulation of Object Creation: The Factory pattern encapsulates the object creation logic within a single place,
 *     promoting cleaner and more maintainable code.
 *
 *     Abstraction and Flexibility: It allows clients to create objects without knowing the specific implementation details,
 *     providing flexibility to the codebase.
 *
 *     Centralized Control: Centralizes the creation of objects, making it easier to manage and modify the creation
 *     process.
 *
 *     Promotes Code Reusability: Facilitates the reuse of common object creation logic, reducing duplication
 *     and improving maintainability.
 *
 *     Supports Dependency Injection: Can be used to implement dependency injection, enabling easier unit testing
 *     and code maintenance.
 *
 * Disadvantages:
 *
 *     Complexity with Many Subclasses: As the number of subclasses increases, managing and extending factories might
 *     become complex and lead to code bloat.
 *
 *     Inflexibility in Object Creation: Once a factory is implemented, changing the types of objects it creates might
 *     require modifications to the factory, potentially impacting existing code.
 *
 *     Limited Flexibility with Product Variations: Factories might not handle variations of products well, especially
 *     if the variations require substantial changes to the creation process.
 *
 *     Creation of God Objects: Overuse of Factory patterns might lead to the creation of a single factory responsible
 *     for too many object types, violating the Single Responsibility Principle.
 *
 *     Coupling Between Client and Factory: Tight coupling between clients and specific factory implementations might
 *     reduce the flexibility of the system.
 *
 * While the Factory pattern provides advantages such as encapsulation, abstraction, and centralized object creation,
 * it's crucial to consider its limitations, especially in scenarios with numerous subclasses or complex variations
 * in object creation. Evaluating the trade-offs between flexibility and potential complexities can help determine
 * whether the Factory pattern is suitable for a particular use case.
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