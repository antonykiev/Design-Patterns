package beahavioural

/**
 * The Mediator pattern is a behavioral design pattern that promotes loose coupling by allowing communication between
 * different components or colleagues through a central mediator object.
 * Advantages:
 *
 *     Decoupling Components: It promotes loose coupling among components by encapsulating their interaction through a
 *     central mediator object. This reduces direct dependencies between individual components, making the system more
 *     maintainable and easier to extend.
 *
 *     Centralized Control: The Mediator pattern centralizes control and coordination logic in a single class.
 *     This simplifies the understanding of communication flows and allows easier modification or extension
 *     of communication behaviors.
 *
 *     Simplified Communication: It provides a single interface for communication between components,
 *     making the communication protocols more structured and easier to manage. New communication protocols
 *     can be added or modified within the mediator without affecting the individual components.
 *
 *     Reusability and Extensibility: As the mediator encapsulates communication logic, it can be reused across
 *     different contexts or extended to support new functionalities without impacting existing components.
 *
 * Disadvantages:
 *
 *     Complexity of the Mediator: The mediator can become complex when handling communication between numerous
 *     components or implementing various communication scenarios. This complexity might make the mediator class
 *     itself harder to maintain and understand.
 *
 *     Centralization of Logic: While centralizing communication logic is advantageous, it may lead to a monolithic
 *     mediator responsible for multiple functionalities. This could violate the Single Responsibility Principle (SRP)
 *     and make the mediator class more difficult to maintain.
 *
 *     Potential Performance Impact: Introducing a mediator could add a performance overhead, especially if there's
 *     frequent communication between components. This could become a bottleneck if the mediator becomes a single point
 *     of contention for communication.
 *
 *     Increased Indirectness: Using a mediator introduces indirect communication between components, which could
 *     reduce the visibility of interactions. This might make it harder to track or understand
 *     the flow of communication between components.
 *
 * In summary, while the Mediator pattern offers advantages such as decoupling, centralized control,
 * and simplified communication, it's essential to consider the trade-offs in terms of complexity,
 * potential performance impact, and increased indirectness of interactions that come with the introduction
 * of a central mediator. Careful design and consideration of the system's requirements are important
 * when employing this pattern.
 *
 */

// Usage example
fun main() {
    val mediator = ChatMediatorImpl()

    val user1 = ChatUser("User 1", mediator)
    val user2 = ChatUser("User 2", mediator)
    val user3 = ChatUser("User 3", mediator)

    mediator.addUser(user1)
    mediator.addUser(user2)
    mediator.addUser(user3)

    user1.send("Hello, everyone!")
    user2.send("Hi, User 1!")
}

// Mediator interface
interface ChatMediator {
    fun sendMessage(message: String, user: User)
}

// Concrete Mediator
class ChatMediatorImpl : ChatMediator {
    private val users = mutableListOf<User>()

    fun addUser(user: User) {
        users.add(user)
    }

    override fun sendMessage(message: String, user: User) {
        // Send message to all users except the sender
        users.filter { it != user }.forEach { receiver ->
            receiver.receive(message)
        }
    }
}

// Colleague interface
abstract class User(val mediator: ChatMediator) {
    abstract fun send(message: String)
    abstract fun receive(message: String)
}

// Concrete Colleague
class ChatUser(private val name: String, mediator: ChatMediator) : User(mediator) {
    override fun send(message: String) {
        println("$name sending message: $message")
        mediator.sendMessage(message, this)
    }

    override fun receive(message: String) {
        println("$name received message: $message")
    }
}
