package beahavioural

/**
 * The Visitor design pattern allows you to define a new operation without changing the classes of the elements on which
 * it operates. It's especially useful when you have a structure of different classes and want to perform various
 * operations on these classes without modifying their structure.
 *
 * This example demonstrates how the Visitor pattern allows you to define new operations (such as drawing in this case)
 * without modifying the class hierarchy of the visited elements (circles, squares).
 *
 * Advantages:
 *
 *     Separation of Concerns: It allows you to separate operations from the object structure, enabling you to add new
 *     operations without modifying the existing classes.
 *     This helps in maintaining the Single Responsibility Principle and keeps the codebase more maintainable.
 *
 *     Extensibility: Adding new operations (visitors) becomes easier. You can create new visitor implementations
 *     without changing the classes of the elements being visited.
 *
 *     Cleaner Code: The Visitor pattern helps in keeping the code cleaner by centralizing operations that would
 *     otherwise be scattered throughout different classes. This improves code readability and maintenance.
 *
 *     Double Dispatch: It enables performing different operations based on both the type of the Visitor
 *     and the type of the element being visited. This flexibility allows for more dynamic behavior.
 *
 * Disadvantages:
 *
 *     Increased Complexity: Implementing the Visitor pattern might introduce additional complexity,
 *     especially in scenarios where the object structure (element classes) frequently changes.
 *     This can make the code harder to understand for developers unfamiliar with the pattern.
 *
 *     Violation of Encapsulation: In some cases, the Visitor pattern requires exposing internal details of elements
 *     being visited, breaking encapsulation.
 *     This could potentially lead to issues if the structure of the elements changes frequently.
 *
 *     Difficulty in Adding New Elements: While it's easy to add new operations (visitors),
 *     adding new elements (classes to be visited) might require modifications to all existing visitor implementations,
 *     which could lead to maintenance overhead.
 *
 *     Potential Performance Overhead: The Visitor pattern might not be suitable for certain
 *     performance-critical scenarios, especially when dealing with a large number of element types and visitors,
 *     as it may lead to increased runtime overhead due to double dispatch mechanisms.
 *
 *
 * Overall, the Visitor pattern is a powerful way to add new operations to an existing structure without
 * modifying its classes, but it should be used judiciously considering the trade-offs and specific
 * requirements of the application.
 *
 */

fun main() {
    val shapes: List<Shape> = listOf(Circle(), Square())
    val visitor = DrawVisitor()

    shapes.forEach { shape ->
        shape.accept(visitor)
    }
}

// Interface for the visitor
interface Visitor {
    fun visit(circle: Circle)
    fun visit(square: Square)
}

// Abstract Element class
interface Shape {
    fun accept(visitor: Visitor)
}

// Concrete Element classes
class Circle : Shape {
    override fun accept(visitor: Visitor) {
        visitor.visit(this)
    }

    fun draw() {
        println("Drawing Circle")
    }
}

class Square : Shape {
    override fun accept(visitor: Visitor) {
        visitor.visit(this)
    }

    fun draw() {
        println("Drawing Square")
    }
}

// Concrete Visitor implementing operations on elements
class DrawVisitor : Visitor {
    override fun visit(circle: Circle) {
        circle.draw()
    }

    override fun visit(square: Square) {
        square.draw()
    }
}
