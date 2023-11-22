package creational

/**
 * The Abstract Factory design pattern provides an interface to create families of related or dependent objects
 * without specifying their concrete classes.
 *
 * Advantages:
 *
 *     Abstraction and Encapsulation: Abstract Factory hides the creation logic of objects, allowing clients to create
 *     families of related objects without being concerned about their specific implementations.
 *
 *     Consistency in Object Creation: Ensures that the created objects are compatible and belong to the same family,
 *     maintaining consistency across the created objects.
 *
 *     Flexibility and Extensibility: Allows easy addition of new variants of product families by introducing
 *     new concrete factories without altering existing code.
 *
 *     Supports Dependency Injection: Abstract Factory can be used to inject a factory into other components,
 *     enabling easier unit testing and dependency management.
 *
 *     Promotes Loose Coupling: Clients depend on the abstract interface of the factory and product objects,
 *     reducing dependencies on specific classes and promoting interchangeable components.
 *
 * Disadvantages:
 *
 *     Complexity: Implementing the Abstract Factory pattern can lead to increased complexity, especially
 *     when dealing with numerous related families of objects.
 *
 *     Increased Number of Classes: Introducing new product variants might require creating additional concrete
 *     factories and product classes, potentially leading to a larger number of classes.
 *
 *     Limited Extensibility for Adding New Products: Adding new products to the existing product families might
 *     require modifying the interfaces of factories, which could impact existing code and clients.
 *
 *     Runtime Efficiency: Depending on the implementation, there might be a slight performance overhead due
 *     to the use of multiple layers of abstraction and indirection.
 *
 *     Difficulty in Navigating Object Creation: For more complex systems, understanding the relationships between
 *     abstract factories, concrete factories, and product families might be challenging.
 *
 * While the Abstract Factory pattern provides benefits in terms of abstraction, consistency, and flexibility
 * in creating families of related objects, it's important to consider the potential drawbacks, such as increased
 * complexity and limited extensibility when adding new products. Evaluating the trade-offs can help determine whether
 * the pattern suits the specific requirements and complexity of the system being developed.
 *
 *
 *
 */
open class Vegetation
class Cactus : Vegetation()
class Tree : Vegetation()


open class Terrain
class Sand : Terrain()
class Grass : Terrain()

/**
 * Factory Interface
 */
interface Factory {
    fun createTerrain(): Terrain
    fun createVegetation(): Vegetation
}

/**
 * Desert Factory
 */
class DesertFactory : Factory {

    override fun createTerrain(): Terrain {
        return Sand()
    }

    override fun createVegetation(): Vegetation {
        return Cactus()
    }
}

/**
 * Forrest Factory
 */
class ForrestFactory : Factory {

    override fun createTerrain(): Terrain {
        return Grass()
    }

    override fun createVegetation(): Vegetation {
        return Tree()
    }
}

class Game(private val factory: Factory) {
    private var terrain: Terrain = factory.createTerrain()
    private var tree: Vegetation = factory.createVegetation()
}