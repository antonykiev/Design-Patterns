package structual

/**
 * The Composite design pattern is a structural pattern that composes objects into tree structures
 * to represent part-whole hierarchies. Here are its advantages and disadvantages:
 * Advantages:
 *
 *     Uniformity of Structure: Allows clients to treat individual objects and compositions of objects uniformly,
 *     simplifying the client code.
 *
 *     Flexible Structure: Enables the creation of complex structures by composing objects into tree-like structures
 *     that can represent hierarchies.
 *
 *     Ease of Adding New Components: Facilitates the addition of new types of components without affecting
 *     the existing structure or client code.
 *
 *     Simplifies Client Code: Clients interact with a single interface, regardless of whether they are dealing
 *     with a single object or a composition of objects.
 *
 *     Supports Recursive Composition: Components can contain other components, allowing for recursive structures
 *     and simplifying complex relationships.
 *
 * Disadvantages:
 *
 *     Limited Type-Specific Functionality: Implementing common operations across different components might
 *     be challenging as they all adhere to a common interface, potentially limiting specialized functionality.
 *
 *     Complexity in Managing Leaf/Composite Differences: Distinguishing between leaf nodes (individual objects)
 *     and composite nodes (compositions) might add complexity to the codebase.
 *
 *     Performance Overhead: Depending on the implementation, traversing and manipulating composite structures
 *     might introduce a performance overhead due to recursive operations.
 *
 *     Maintaining Component Relationships: Managing relationships between components within a composite structure
 *     can become complex, especially in large hierarchies.
 *
 *     Potential for Over-Generalization: Overusing the Composite pattern might lead to over-generalization,
 *     making the codebase harder to understand and maintain.
 *
 * While the Composite pattern provides advantages in terms of creating flexible and hierarchical structures,
 * it's important to consider potential drawbacks, such as limited type-specific functionality and managing
 * complexities in composite structures. Evaluating the trade-offs and carefully applying the pattern to appropriate
 * scenarios can help harness its benefits effectively without introducing unnecessary complexity.
 */

fun main(args: Array<String>) {
    //Initialize four ellipses
    val ellipse1 = Ellipse()
    val ellipse2 = Ellipse()
    val ellipse3 = Ellipse()
    val ellipse4 = Ellipse()
    //Initialize four squares
    val square1 = Square()
    val square2 = Square()
    val square3 = Square()
    val square4 = Square()
    //Initialize three composite graphics
    val graphic = CompositeGraphic()
    val graphic1 = CompositeGraphic()
    val graphic2 = CompositeGraphic()
    //Composes the graphics
    graphic1.add(ellipse1)
    graphic1.add(ellipse2)
    graphic1.add(square1)
    graphic1.add(ellipse3)
    graphic2.add(ellipse4)
    graphic2.add(square2)
    graphic2.add(square3)
    graphic2.add(square4)
    graphic.add(graphic1)
    graphic.add(graphic2)
    //Prints the complete graphic
    graphic.print()
}

/** "Component" */
interface Graphic {
    fun print()
}
/** "Composite" */
class CompositeGraphic(
    private val graphics: ArrayList<Graphic> = ArrayList()
) : Graphic {

    override fun print() = graphics.forEach(Graphic::print)

    fun add(graphic: Graphic) {
        graphics.add(graphic)
    }

    fun remove(graphic: Graphic) {
        graphics.remove(graphic)
    }
}
class Ellipse : Graphic {
    override fun print() = println("Ellipse")
}
class Square : Graphic {
    override fun print() = println("Square")
}