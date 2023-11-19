package beahavioural

/**
 * The Memento pattern is a behavioral design pattern that allows you to capture an object's internal state without
 * exposing its internal structure and then restore the object to that state later.
 *
 * The Memento design pattern offers several advantages but also comes with some limitations:
 *
 * Advantages:
 *
 *     State Capture and Restoration: It allows capturing an object's internal state without exposing its implementation
 *     details and provides a mechanism to restore the object to a previous state, supporting undo and redo
 *     functionality or restoring to specific checkpoints.
 *
 *     Encapsulation of State: It encapsulates the state within a separate Memento object, maintaining the principle
 *     of encapsulation and preventing direct access to the object's state by external components.
 *
 *     Supports Recovery Mechanisms: It facilitates recovery mechanisms in systems by storing a series of mementos,
 *     enabling the restoration of an object's previous state in case of failures or errors.
 *
 *     Simplifies Originator Class: By delegating the responsibility of managing states to the Memento and Caretaker
 *     classes, the Originator class can focus on its core functionalities rather than managing state history.
 *
 * Disadvantages:
 *
 *     Memory Overhead: If multiple states need to be stored or if the state object is large, it can consume
 *     a significant amount of memory, especially when managing a large number of mementos.
 *
 *     Potential Performance Impact: Maintaining a history of mementos and restoring object states might have
 *     performance implications, especially in resource-constrained environments or when dealing with complex objects.
 *
 *     Limited Access to State: In some cases, the pattern might restrict access to specific internal
 *     states of the object, which could be necessary for certain operations or optimizations.
 *
 *     Complexity in Implementation: Implementing the Memento pattern might introduce additional complexity,
 *     especially when managing the relationship between the Originator, Memento, and Caretaker classes or
 *     dealing with multiple object states.
 *
 *
 *
 * Overall, while the Memento pattern provides an effective way to capture and restore an object's state,
 * it's essential to carefully consider its usage, especially in terms of memory consumption, performance impact,
 * and the added complexity it introduces to the design.
 *
 */

// Usage example
fun main() {
    val history = History()
    val editor = TextEditorMomento("Initial text")

    history.saveMemento(editor.createMemento()) // Save initial state

    editor.text = "Edited text"
    history.saveMemento(editor.createMemento()) // Save edited state

    println("Current text: ${editor.text}")

    // Restore previous state
    editor.restore(history.getMemento(0))
    println("Restored text: ${editor.text}")
}

// Memento class
data class EditorMemento(val text: String)

// Originator class
class TextEditorMomento(var text: String) {
    fun createMemento(): EditorMemento {
        return EditorMemento(text)
    }

    fun restore(memento: EditorMemento) {
        text = memento.text
    }
}

// Caretaker class (responsible for storing and managing mementos)
class History {
    private val mementos = mutableListOf<EditorMemento>()

    fun saveMemento(memento: EditorMemento) {
        mementos.add(memento)
    }

    fun getMemento(index: Int): EditorMemento {
        return mementos[index]
    }
}