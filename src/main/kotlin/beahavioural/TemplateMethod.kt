package beahavioural

/**
 *     The Template Method pattern is a behavioral design pattern that defines the skeleton of an algorithm in a method,
 *     deferring some steps to subclasses.
 *
 *     Advantages:
 *
 *     Reusability: It promotes code reuse by providing a common structure (template method) in the base class,
 *     allowing subclasses to reuse the same algorithm while customizing specific steps as needed.
 *
 *     Encapsulation: It helps in encapsulating the algorithm's structure in one place (the template method)
 *     within the base class, making it easier to understand and maintain.
 *
 *     Flexibility: It allows subclasses to implement specific steps differently without changing the overall
 *     algorithm's structure, providing flexibility in how different parts of the algorithm are executed.
 *
 *     Consistency: It enforces a consistent algorithm across multiple subclasses, ensuring that the core steps are
 *     followed in the same sequence in different implementations.
 *
 *     Disadvantages:
 *
 *     Limited Flexibility: The pattern might restrict flexibility in certain cases since it defines a fixed structure
 *     for the algorithm. If there's a need for significantly different algorithms, it might not be suitable.
 *
 *     Difficulty in Understanding: Understanding the flow of execution might become complex as the actual sequence
 *     of steps is spread across both the base class (template method) and its subclasses.
 *
 *     Inheritance Issues: Extending the template method through inheritance might lead to a class hierarchy that
 *     becomes too deep, which can make the system harder to maintain and extend.
 *
 *     Violation of Single Responsibility Principle: The template method might violate the Single Responsibility
 *     Principle by bundling multiple steps into a single method, potentially making the code harder to maintain and test.
 *
 */

// Usage example
fun main() {
    val cricket = Cricket()
    val football = Football()

    println("Playing Cricket:")
    cricket.play()

    println("\nPlaying Football:")
    football.play()
}

// Abstract class defining the template method
abstract class Game {
    abstract fun initialize()
    abstract fun startPlay()
    abstract fun endPlay()

    // Template method defining the algorithm skeleton
    fun play() {
        initialize()
        startPlay()
        endPlay()
    }
}

// Concrete implementation 1
class Cricket : Game() {
    override fun initialize() {
        println("Cricket Game Initialized! Start playing.")
    }

    override fun startPlay() {
        println("Cricket Game Started. Enjoy the game!")
    }

    override fun endPlay() {
        println("Cricket Game Finished!")
    }
}

// Concrete implementation 2
class Football : Game() {
    override fun initialize() {
        println("Football Game Initialized! Start playing.")
    }

    override fun startPlay() {
        println("Football Game Started. Enjoy the game!")
    }

    override fun endPlay() {
        println("Football Game Finished!")
    }
}