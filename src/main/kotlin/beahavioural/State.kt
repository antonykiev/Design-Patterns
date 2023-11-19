package beahavioural

/**
 * The State pattern allows an object to alter its behavior when its internal state changes.
 *
 * Advantages:
 *
 *     Clean Code and Structure: It helps in maintaining clean and organized code by encapsulating each state behavior
 *     into separate classes. This separation makes the codebase more modular and easier to understand.
 *
 *     Open/Closed Principle: It follows the Open/Closed Principle, allowing new states to be added or existing ones
 *     to be modified without altering the context class. This promotes code extensibility and maintainability.
 *
 *     Simplifies Complex Logic: It simplifies large conditional statements by encapsulating the logic associated with
 *     different states into separate classes. This leads to a clearer and more manageable codebase.
 *
 *     Promotes Reusability: State classes can be reused across different contexts or objects, fostering code reuse
 *     and reducing duplication of state-specific logic.
 *
 * Disadvantages:
 *
 *     Potential Increase in Classes: Implementing states as separate classes might lead to an increase in the number
 *     of classes, which could make the system more complex and harder to manage, especially for simpler state
 *     transitions.
 *
 *     Complexity of State Transitions: If there are complex state transitions or a large number of states, managing
 *     the transitions between states might become complex and difficult to visualize.
 *
 *     Potential Performance Overhead: The state pattern might introduce some overhead due to the dynamic changes in
 *     behavior. Switching between different states could result in additional method calls or state changes,
 *     impacting performance.
 *
 *     Difficulty in Debugging: Debugging might become more challenging, especially when multiple states and
 *     ransitions are involved, as it might be harder to trace the flow of the application.
 *
 *
 *
 * In summary, while the State pattern helps in managing object behavior based on its internal state,
 * promoting modularity and extensibility, it should be used judiciously, considering the trade-offs
 * in terms of code complexity and performance impact.
 *
 */


// Usage example
fun main() {
    val vendingMachine = VendingMachine()

    vendingMachine.selectItem("A01") // Output: Please insert money first.

    vendingMachine.insertMoney(5) // Output: 5 credits inserted.

    vendingMachine.selectItem("A01") // Output: Item A01 selected.

    vendingMachine.dispenseItem() // Output: Item dispensed. Thank you for your purchase.

    vendingMachine.dispenseItem() // Output: Please select an item first.
}

// Interface defining the state behavior
interface VendingMachineState {
    fun insertMoney(amount: Int)
    fun selectItem(itemCode: String)
    fun dispenseItem()
}

// Concrete state classes
class IdleState(private val vendingMachine: VendingMachine) : VendingMachineState {
    override fun insertMoney(amount: Int) {
        println("$amount credits inserted.")
        vendingMachine.changeState(AcceptingMoneyState(vendingMachine))
    }

    override fun selectItem(itemCode: String) {
        println("Please insert money first.")
    }

    override fun dispenseItem() {
        println("Please select an item first.")
    }
}

class AcceptingMoneyState(private val vendingMachine: VendingMachine) : VendingMachineState {
    override fun insertMoney(amount: Int) {
        println("$amount credits inserted.")
    }

    override fun selectItem(itemCode: String) {
        println("Item $itemCode selected.")
        vendingMachine.changeState(DispensingItemState(vendingMachine))
    }

    override fun dispenseItem() {
        println("Please select an item first.")
    }
}

class DispensingItemState(private val vendingMachine: VendingMachine) : VendingMachineState {
    override fun insertMoney(amount: Int) {
        println("Please wait, dispensing item...")
    }

    override fun selectItem(itemCode: String) {
        println("Please wait, dispensing item...")
    }

    override fun dispenseItem() {
        println("Item dispensed. Thank you for your purchase.")
        vendingMachine.changeState(IdleState(vendingMachine))
    }
}

// Context class
class VendingMachine {
    private var currentState: VendingMachineState = IdleState(this)

    fun insertMoney(amount: Int) {
        currentState.insertMoney(amount)
    }

    fun selectItem(itemCode: String) {
        currentState.selectItem(itemCode)
    }

    fun dispenseItem() {
        currentState.dispenseItem()
    }

    fun changeState(newState: VendingMachineState) {
        currentState = newState
    }
}
