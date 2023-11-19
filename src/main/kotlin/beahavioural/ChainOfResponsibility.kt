package beahavioural

/**
 * The Chain of Responsibility pattern is a behavioral design pattern that creates a chain of handler objects
 * to process a request. The request travels through this chain until it's handled by an appropriate handler.
 *
 * The Chain of Responsibility design pattern offers several advantages and may also have some drawbacks:
 *
 * Advantages:
 *
 *     Decoupling of Request and Handlers: It decouples the sender of a request from its receivers,
 *     allowing multiple objects to handle the request. The sender doesn't need to know which object will handle
 *     the request; it only sends it to the first handler in the chain.
 *
 *     Flexibility and Extensibility: It provides flexibility in adding, removing, or reordering handlers in the
 *     chain without affecting the client code. New handlers can be easily added or existing ones modified
 *     to accommodate new requirements.
 *
 *     Promotes Reusability: Handlers can be reused across different chains or scenarios, promoting code reusability.
 *     Once a handler is defined, it can be integrated into different chains to handle similar types of requests.
 *
 *     Allows Dynamic Behavior Changes: The pattern allows runtime changes in the chain, enabling dynamic
 *     modifications in the order of handling requests or changing the composition of the chain.
 *
 * Disadvantages:
 *
 *     Uncertainty in Handling: There might be uncertainty in handling a request. If the chain is not properly
 *     configured or if there's no handler capable of handling the request, it may go unprocessed,
 *     leading to potential issues.
 *
 *     Performance Overhead: The request might traverse through the entire chain, even if the suitable handler
 *     is at the end. This can introduce a performance overhead, especially in long chains or if there are
 *     frequent changes in the chain's structure.
 *
 *     Complexity and Debugging: Debugging can become complex in larger chains where a request passes through
 *     multiple handlers. Identifying which handler is processing the request and tracing the flow can be challenging.
 *
 *     Potential for Misuse: Overusing the Chain of Responsibility pattern might lead to unnecessarily complex code,
 *     especially when simpler alternatives, like a simple if-else or switch-case statement,
 *     could effectively handle the situation.
 *
 *
 * In summary, the Chain of Responsibility pattern offers flexibility and decoupling, enabling multiple handlers
 * to handle requests without the sender knowing the specific handler. However, it's essential to carefully design
 * the chain to avoid uncertainties in handling, minimize performance overhead, and maintain code simplicity
 * and readability.
 *
 */
fun main() {
    val chain = FirstHandler(
        SecondHandler(null)
    )
    listOf("Request1", "Request2", "Request3").forEach {
        if (!chain.handleRequest(it)) {
            println("$it was not handled")
        }
    }
}

abstract class Handler(
    open var nextHandler: Handler?
) {
    abstract fun handleRequest(request: String): Boolean
}

class FirstHandler(
    override var nextHandler: Handler?
) : Handler(nextHandler) {

    override fun handleRequest(request: String): Boolean {
        if (request == "Request1") {
            println("FirstHandler handled $request")
            return true
        }
        return nextHandler?.handleRequest(request) ?: false
    }
}

class SecondHandler(
    override var nextHandler: Handler?
) : Handler(nextHandler) {

    override fun handleRequest(request: String): Boolean {
        if (request == "Request2") {
            println("SecondHandler handled $request")
            return true
        }
        return nextHandler?.handleRequest(request) ?: false
    }
}