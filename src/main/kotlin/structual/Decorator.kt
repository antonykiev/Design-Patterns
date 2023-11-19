package structual

/**
 * The Decorator pattern is a structural design pattern that allows behavior to be added
 * to individual objects dynamically, without affecting the behavior of other objects of the same class.
 * It is used to extend or enhance the functionality of objects at runtime.
 *
 * The Decorator design pattern offers several advantages but also comes with some trade-offs:
 *
 * Advantages:
 *
 *     Flexibility and Extensibility: It allows for the dynamic addition of behaviors or responsibilities to objects
 *     at runtime without modifying their structure. New functionalities can be added by creating new decorator classes,
 *     promoting extensibility.
 *
 *     Single Responsibility Principle (SRP): The pattern follows the SRP by having each decorator class responsible
 *     for a single concern. This ensures that functionalities are separated into distinct classes, making the codebase
 *     more maintainable and easier to understand.
 *
 *     Open/Closed Principle (OCP): The Decorator pattern supports the OCP as it allows the addition of new behaviors
 *     without modifying existing code. Existing objects remain unchanged while new functionalities are added through
 *     decorators.
 *
 *     Component Independence: Decorators can be stacked or combined in various ways, allowing a high degree
 *     of flexibility in assembling and composing different behaviors. Multiple decorators can be used to create
 *     complex combinations of features.
 *
 * Disadvantages:
 *
 *     Complexity by Abundance of Small Objects: The pattern might lead to a large number of small,
 *     specialized objects when multiple decorators are used, potentially increasing the complexity of the system.
 *     This could make it harder to manage and debug.
 *
 *     Ordering of Decorators: The order in which decorators are applied can be crucial. Changing the order may lead
 *     to different behaviors, and managing this order can become challenging, especially in larger systems with many
 *     decorators.
 *
 *     Potential Overuse: Improper use of the Decorator pattern can lead to unnecessary complexity if simple
 *     inheritance or other design patterns could achieve the same results more effectively. Using decorators
 *     for every small feature might overcomplicate the system.
 *
 *     Duplication of Code: If multiple decorators implement similar functionalities, it could lead to code duplication.
 *     Careful design and consolidation of similar functionalities are necessary to prevent redundancy.
 *
 *
 * In summary, the Decorator pattern is beneficial for dynamically adding responsibilities to objects while keeping
 * their structure intact. However, its usage should be balanced with the complexity it introduces, ensuring that it
 * aligns with the specific requirements and doesn’t lead to unnecessary code intricacies or duplications.
 */


// Usage example
fun main() {
    // Creating a base beverage (Espresso)
    var beverage: Beverage = Espresso()
    println("Base beverage: ${beverage.getDescription()} - Cost: $${beverage.cost()}")

    // Decorating with Milk
    beverage = Milk(beverage)
    println("Decorated beverage: ${beverage.getDescription()} - Cost: $${beverage.cost()}")

    // Decorating with Sugar
    beverage = Sugar(beverage)
    println("Decorated beverage: ${beverage.getDescription()} - Cost: $${beverage.cost()}")
}

// Component interface
interface Beverage {
    fun getDescription(): String
    fun cost(): Double
}

// Concrete Component
class Espresso : Beverage {
    override fun getDescription(): String {
        return "Espresso"
    }

    override fun cost(): Double {
        return 1.99
    }
}

// Decorator (abstract class)
abstract class CondimentDecorator(
    private val beverage: Beverage
) : Beverage {
    abstract override fun getDescription(): String
}

// Concrete Decorators
class Milk(
    private val beverage: Beverage
) : CondimentDecorator(beverage) {
    override fun getDescription(): String {
        return "${beverage.getDescription()}, Milk"
    }

    override fun cost(): Double {
        return beverage.cost() + 0.5
    }
}

class Sugar(private val beverage: Beverage) : CondimentDecorator(beverage) {
    override fun getDescription(): String {
        return "${beverage.getDescription()}, Sugar"
    }

    override fun cost(): Double {
        return beverage.cost() + 0.2
    }
}