package creational

/**
 * Lets your ensure that a class has only one instance,
 * while providing a global access point to this instance
 *
 * Advantages:
 *
 *     Single Instance: Ensures that only one instance of the class exists throughout the application,
 *     promoting efficient resource utilization.
 *
 *     Global Access: Provides a global access point to the single instance, allowing easy access to its methods
 *     and attributes.
 *
 *     Lazy Initialization: Allows for lazy initialization, meaning the instance is created only when it's
 *     first requested, reducing memory usage until needed.
 *
 *     Thread Safety (when implemented properly): Properly implemented Singleton patterns can handle concurrent
 *     access in a multi-threaded environment, ensuring thread safety.
 *
 *     Controls Access to Resources: Useful for managing shared resources such as database connections,
 *     file systems, or system settings.
 *
 * Disadvantages:
 *
 *     Global State: The Singleton pattern introduces a global state, which might make the application harder
 *     to debug and test due to dependencies on this shared state.
 *
 *     Potential for Tight Coupling: Excessive use of singletons can lead to tight coupling and dependencies
 *     throughout the codebase, making it harder to modify and maintain.
 *
 *     Can Hide Dependencies: Singletons can hide class dependencies, making it unclear what other classes depend on,
 *     potentially leading to maintenance issues.
 *
 *     Can Violate Single Responsibility Principle: Singleton pattern might violate the Single Responsibility Principle
 *     by handling both its primary functionality and managing its lifecycle as a single instance.
 *
 *     Difficult to Unit Test: Due to the global state, testing becomes more complex as the state of a Singleton
 *     instance may persist between tests, leading to unpredictable behavior.
 *
 * While the Singleton pattern offers advantages such as single instance management and global access, its use can
 * also lead to challenges related to maintaining state, testing, and managing dependencies. Careful consideration
 * of its necessity and impact on the application's architecture is crucial when deciding to use the Singleton pattern.
 * Additionally, alternative patterns or dependency injection techniques might be preferred in certain scenarios to
 * address these disadvantages.
 */

// Companion Object
class Singleton0 private constructor() {
    companion object {

        @Volatile
        private var instance: Singleton0? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: Singleton0().also { instance = it }
            }
    }

    fun doSomething() = "Doing something"
}

// Eager Initialization
object Singleton1 {
    fun doSomething() = "Doing something"
}

// Lazy Initialization
class Singleton2 private constructor() {

    companion object {
        val instance:Singleton2 by lazy {
            Singleton2()
        }
    }

    fun doSomething() = "Doing something"
}

// Enum
enum class Singleton3 {
    INSTANCE;

    fun doSomething() = "Doing something"
}