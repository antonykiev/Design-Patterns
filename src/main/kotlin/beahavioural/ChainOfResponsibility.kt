package beahavioural

/**
 * Definition
 * The Chain of Responsibility Design Pattern decouples the sender from the receiver by allowing multiple objects
 * to handle the request. Each handler decides whether to process the request or pass it along the chain to the
 * next handler.
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