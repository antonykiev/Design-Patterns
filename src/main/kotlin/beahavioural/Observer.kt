package beahavioural

/**
 * The Observer pattern is a behavioral design pattern where an object, known as the subject,
 * maintains a list of its dependents, called observers, and notifies them of any state changes,
 * usually by calling one of their methods.
 *
 * The Observer design pattern has various advantages and a few potential drawbacks:
 *
 * Advantages:
 *
 *     Loose Coupling: It establishes a one-to-many relationship between subjects and observers,
 *     allowing them to remain loosely coupled. Subjects and observers can interact without having explicit knowledge
 *     of each other's details, promoting flexibility and easier maintenance.
 *
 *     Supports Broadcast Communication: Observers can receive updates and notifications from a single subject,
 *     enabling broadcast communication to multiple interested parties simultaneously.
 *
 *     Modularity and Reusability: It promotes modularity by separating concerns; the subject (publisher)
 *     is responsible for managing state and notifying observers, while observers (subscribers) can be easily added
 *     or removed without affecting the subject's behavior.
 *
 *     Reduces Duplication of Code: When multiple objects need to respond to changes in a single subject,
 *     the Observer pattern helps avoid code duplication by centralizing the update logic within the observers.
 *
 * Disadvantages:
 *
 *     Potential Memory Leaks: If observers are not properly removed or deregistered from subjects, it can lead
 *     to memory leaks as subject references may keep observers from being garbage collected even when they are no
 *     longer needed.
 *
 *     Ordering Issues: The order in which observers are notified might matter in some scenarios.
 *     The pattern itself does not define a specific order of notification among observers, which might be
 *     a disadvantage in cases where a particular order of updates is crucial.
 *
 *     Unexpected Updates: Observers might receive updates that are irrelevant to them. In larger systems,
 *     this could lead to inefficiencies if observers need to filter out updates they don’t care about.
 *
 *     Complexity in Debugging: In systems with numerous observers and complex interactions, debugging might become
 *     challenging due to the indirect nature of communication between subjects and observers.
 *
 *
 * Overall, the Observer pattern is beneficial for implementing reactive systems where changes in one object trigger
 * actions in others. However, it's essential to be mindful of memory management, potential issues with
 * unexpected updates, and the debugging complexity associated with this pattern in larger systems.
 *
 */


// Usage example
fun main() {
    val newspaper = Newspaper()

    val subscriber1 = Subscriber(newspaper, "Subscriber 1")
    val subscriber2 = Subscriber(newspaper, "Subscriber 2")
    val subscriber3 = Subscriber(newspaper, "Subscriber 3")

    newspaper.setLatestEdition("New Edition 1")
    newspaper.setLatestEdition("New Edition 2")

    newspaper.removeObserver(subscriber2)

    newspaper.setLatestEdition("New Edition 3")
}

// Observer interface
interface Observer {
    fun update()
}

// Subject interface
interface Subject {
    fun registerObserver(observer: Observer)
    fun removeObserver(observer: Observer)
    fun notifyObservers()
}

// Concrete Subject (Newspaper)
class Newspaper : Subject {
    private val observers = mutableListOf<Observer>()
    private var latestEdition: String = ""

    fun setLatestEdition(edition: String) {
        latestEdition = edition
        notifyObservers()
    }

    override fun registerObserver(observer: Observer) {
        observers.add(observer)
    }

    override fun removeObserver(observer: Observer) {
        observers.remove(observer)
    }

    override fun notifyObservers() {
        observers.forEach { observer ->
            observer.update()
        }
    }

    fun getLatestEdition(): String {
        return latestEdition
    }
}

// Concrete Observer (Subscriber)
class Subscriber(private val newspaper: Newspaper, private val name: String) : Observer {
    init {
        newspaper.registerObserver(this)
    }

    override fun update() {
        val latestEdition = newspaper.getLatestEdition()
        println("$name: Received the latest edition of the newspaper - '$latestEdition'")
    }
}