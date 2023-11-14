package creational

/**
 * Lets your ensure that a class has only one instance,
 * while providing a global access point to this instance
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