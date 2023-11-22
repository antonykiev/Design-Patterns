package beahavioural

/**
 * The Command design pattern is a behavioral design pattern that encapsulates a request as an object,
 * thereby allowing for parameterization of clients with queues, requests, and operations.
 *
 * Advantages:
 *
 *     Decoupling: It separates the requester of an operation from the object that executes the operation,
 *     promoting loose coupling between sender and receiver.
 *
 *     Flexibility: Commands can be easily extended, added, or modified without affecting the client's code,
 *     allowing for easier maintenance and updates.
 *
 *     Undo/Redo Functionality: The pattern allows for easy implementation of undo and redo operations by
 *     storing command history.
 *
 *     Queueing and Logging: Commands can be queued for execution, enabling features like logging,
 *     transactional behavior, and support for rollback mechanisms.
 *
 *     Collaboration: It promotes collaboration between objects by making them unaware of each other's
 *     implementation details, allowing them to work together effectively.
 *
 * Disadvantages:
 *
 *     Increased Complexity: Implementing the Command pattern can introduce additional classes, leading to increased
 *     code complexity and potentially reduced readability.
 *
 *     Overhead: The use of Command objects might lead to increased memory consumption, especially when dealing
 *     with a large number of commands.
 *
 *     Maintenance Challenges: As the number of commands increases, managing and maintaining command classes might
 *     become cumbersome.
 *
 *     Potential Performance Impact: Depending on the implementation, there might be a performance overhead
 *     due to the creation and management of command objects.
 *
 *     Design Overkill: For simple tasks or applications, applying the Command pattern might be unnecessary
 *     and could introduce unnecessary complexity.
 *
 * While the Command design pattern offers several advantages in terms of flexibility, decoupling, and enabling
 * features like undo/redo functionality, it's essential to weigh these benefits against the potential drawbacks,
 * such as increased complexity and maintenance challenges, to determine its suitability for a particular
 * application or system.
 */


fun main() {
    val light = Light()

    val turnOnCommand = TurnOnCommand(light)
    val turnOffCommand = TurnOffCommand(light)

    val remote = RemoteControl()

    remote.setCommand(turnOnCommand)
    remote.pressButton() // Output: Light is turned on

    remote.setCommand(turnOffCommand)
    remote.pressButton() // Output: Light is turned off
}

// Receiver
class Light {
    fun turnOn() {
        println("Light is turned on")
    }

    fun turnOff() {
        println("Light is turned off")
    }
}

// Command interface
interface Command {
    fun execute()
}

// Concrete commands
class TurnOnCommand(private val light: Light) : Command {
    override fun execute() {
        light.turnOn()
    }
}

class TurnOffCommand(private val light: Light) : Command {
    override fun execute() {
        light.turnOff()
    }
}

// Invoker
class RemoteControl {
    private var command: Command? = null

    fun setCommand(cmd: Command) {
        command = cmd
    }

    fun pressButton() {
        command?.execute()
    }
}