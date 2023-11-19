package structual

/**
 * The Decorator pattern is a structural design pattern that allows behavior to be added
 * to individual objects dynamically, without affecting the behavior of other objects of the same class.
 * It is used to extend or enhance the functionality of objects at runtime.
 *
 *
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