package creational

/**
 * Lets your construct complex objects step by step.
 * The pattern allows to produce different types and representations
 * of an object using the same construction code
 *
 * Advantages:
 *
 *     Separation of Concerns: It separates the construction of an object from its representation, allowing different
 *     representations to be created using the same construction process.
 *
 *     Complex Object Creation: It simplifies the creation of complex objects by breaking down the construction
 *     process into smaller, manageable steps.
 *
 *     Flexibility in Object Creation: Builders can create different variations of the same object by varying the order
 *     or steps of construction, or by using different builder implementations.
 *
 *     Encapsulation: It encapsulates the construction process within the builder, keeping the client code clean
 *     and hiding the complexities of object construction.
 *
 *     Reusability: Builders can be reused across different objects, allowing the creation of various object
 *     configurations without duplicating construction logic.
 *
 *     Improves Readability: It enhances code readability by providing named methods for each step of construction,
 *     making the code more understandable and maintainable.
 *
 * Disadvantages:
 *
 *     Increased Code Complexity: Implementing the Builder pattern may lead to increased code complexity, especially
 *     for simple objects, due to the creation of separate builder classes.
 *
 *     Potential Overhead: Depending on the implementation, using the Builder pattern might introduce some performance
 *     overhead, especially if there are many steps involved in constructing an object.
 *
 *     Requires Implementation Overhead: Developers need to create and maintain the builder interface and concrete
 *     builder classes, which could add overhead to the development process.
 *
 *     May Not Be Ideal for Simple Objects: For simple object creation, using the Builder pattern might be excessive
 *     and could add unnecessary complexity.
 *
 *     Possible Object State Inconsistency: If the construction process is not correctly implemented in the builder,
 *     it might result in creating objects in an inconsistent state.
 *
 * While the Builder pattern offers advantages like flexibility, encapsulation, and separation of concerns in
 * constructing complex objects, it's essential to carefully evaluate its use in relation to the complexity
 * of the objects being built. For complex objects with varying configurations, the Builder pattern can significantly
 * improve the construction process and maintainability of the code. However, for simpler objects, it might introduce
 * unnecessary overhead and complexity.
 */

class FoodOrder private constructor(
    val bread: String = "Flat bread",
    val condiments: String?,
    val meat: String?,
    val fish: String?
) {

    data class Builder(
        var bread: String? = null,
        var condiments: String? = null,
        var meat: String? = null,
        var fish: String? = null
    ) {

        fun bread(bread: String) = apply { this.bread = bread }

        fun condiments(condiments: String) = apply { this.condiments = condiments }

        fun meat(meat: String) = apply { this.meat = meat }

        fun fish(fish: String) = apply { this.fish = fish }

        fun build() = FoodOrder(bread.orEmpty(), condiments, meat, fish)

        fun randomBuild() = bread(bread ?: "dry")
            .condiments(condiments ?: "pepper")
            .meat(meat ?: "beef")
            .fish(fish ?: "Tilapia")
            .build()
    }
}