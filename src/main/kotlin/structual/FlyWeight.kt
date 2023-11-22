package structual

/**
 * The Flyweight design pattern is a structural pattern that minimizes memory usage and improves performance by
 * sharing objects with similar intrinsic characteristics. Here are its advantages and disadvantages:
 * Advantages:
 *
 *     Memory Efficiency: Reduces memory usage by sharing common data/state among multiple objects, especially
 *     when dealing with a large number of similar objects.
 *
 *     Performance Improvement: Improves performance by reusing shared objects instead of creating new instances,
 *     reducing object creation overhead.
 *
 *     Reduction in Object Count: Reduces the overall number of objects in the system by sharing common state,
 *     leading to a more efficient use of resources.
 *
 *     Enhanced Scalability: Allows the system to handle a larger number of objects without exhausting memory resources,
 *     improving system scalability.
 *
 *     Simplified Object Creation: Separates intrinsic and extrinsic state, simplifying the creation of objects
 *     by focusing on the unique extrinsic state.
 *
 * Disadvantages:
 *
 *     Increased Complexity: Implementing the Flyweight pattern may introduce additional complexity, especially when
 *     managing shared state and ensuring proper object identity.
 *
 *     State Management Challenges: Managing shared state across multiple objects can become complex and error-prone,
 *     especially in concurrent or multithreaded environments.
 *
 *     Potential Trade-off with CPU Usage: While the pattern reduces memory usage, it might lead to increased CPU usage
 *     due to additional processing required for sharing and managing state.
 *
 *     Limits in Sharing: Not all objects may be suitable for sharing common state, and determining which state
 *     to share can be challenging.
 *
 *     Maintaining Object Integrity: Ensuring that changes to shared state do not affect the integrity of other objects
 *     sharing that state can be difficult to manage.
 *
 * The Flyweight pattern is beneficial in scenarios where a large number of similar objects need to be managed efficiently,
 * leading to reduced memory usage and improved performance. However, the implementation complexity and challenges
 * in managing shared state should be carefully considered when deciding to use the pattern in a particular context.
 *
 */


// Client code
fun main() {
    val factory = CoffeeFlavorFactory()

    // Orders for coffee flavors
    val orders = listOf(
        "Espresso", "Cappuccino", "Latte", "Espresso", "Espresso",
        "Cappuccino", "Cappuccino", "Latte", "Latte", "Espresso"
    )

    val tableNumbers = (1..orders.size).toList()

    // Serve coffees based on orders
    for ((index, order) in orders.withIndex()) {
        val flavor = factory.getCoffeeFlavor(order)
        flavor.serveCoffee(tableNumbers[index])
    }

    println("Total coffee flavors made: ${factory.getTotalCoffeeFlavorsMade()}")
}

// Flyweight interface
interface CoffeeOrder {
    fun serveCoffee(tableNumber: Int)
}

// Concrete flyweight
class CoffeeFlavor(private val flavorName: String) : CoffeeOrder {
    override fun serveCoffee(tableNumber: Int) {
        println("Serving $flavorName coffee to table number $tableNumber")
    }
}

// Flyweight factory
class CoffeeFlavorFactory {
    private val flavors: MutableMap<String, CoffeeFlavor> = mutableMapOf()

    fun getCoffeeFlavor(flavorName: String): CoffeeFlavor {
        return flavors.getOrPut(flavorName) { CoffeeFlavor(flavorName) }
    }

    fun getTotalCoffeeFlavorsMade(): Int {
        return flavors.size
    }
}
