package structual

/**
 * The bridge pattern allows the Abstraction and the Implementation
 * to be developed independently and the client code can access
 * only the Abstraction part without being concerned about the
 * Implementation part.
 *
 * Advantages:
 *
 *     Decoupling Abstraction and Implementation: Separates the abstraction (interface) from its implementation,
 *     allowing changes in one without affecting the other.
 *
 *     Enhanced Extensibility: Provides the flexibility to extend both the abstraction and implementation independently,
 *     enabling easier system evolution.
 *
 *     Promotes Encapsulation: Encapsulates details of implementation within its own hierarchy, making the codebase more
 *     maintainable and adaptable to changes.
 *
 *     Improved Reusability: Allows the reuse of both abstraction and implementation hierarchies separately,
 *     reducing redundancy and promoting code reusability.
 *
 *     Simplifies Complex Systems: Helps in managing complex class hierarchies by splitting them into
 *     separate hierarchies, making the system easier to understand and manage.
 *
 * Disadvantages:
 *
 *     Increased Complexity: Implementing the Bridge pattern might introduce additional classes and hierarchies,
 *     potentially increasing code complexity.
 *
 *     Potential Overhead: Depending on the system's structure, using the Bridge pattern might introduce an overhead
 *     due to the additional abstraction and indirection.
 *
 *     Design Overkill for Simple Systems: For simpler systems or where changes in implementation and abstraction
 *     are unlikely, using the Bridge pattern might be an unnecessary abstraction layer.
 *
 *     Requires Careful Design: Designing the abstraction and implementation hierarchies properly can be challenging
 *     and requires careful consideration to prevent over-engineering.
 *
 *     Possible Indirection Challenges: The level of indirection introduced by the Bridge pattern might make it harder
 *     to navigate and understand the codebase for some developers.
 *
 * While the Bridge pattern offers advantages in terms of decoupling, extensibility, and encapsulation, it's important
 * to carefully assess the system's complexity and future requirements. Overuse of the Bridge pattern might lead
 * to unnecessary complexity, while using it appropriately can lead to a more modular and maintainable codebase.
 * Evaluating the trade-offs between flexibility and potential complexities is crucial when deciding to use
 * the Bridge pattern in a particular context.
 */

interface Switch {
    var appliance: Appliance
    fun turnOn()
}

interface Appliance {
    fun run()
}

class RemoteControl(override var appliance: Appliance) : Switch {
    override fun turnOn() = appliance.run()
}

class TV : Appliance {
    override fun run() = println("TV turned on")
}
class VacuumCleaner : Appliance {
    override fun run() = println("VacuumCleaner turned on")
}

fun main(args: Array<String>) {
    val tvRemoteControl = RemoteControl(appliance = TV())
    tvRemoteControl.turnOn()
    val fancyVacuumCleanerRemoteControl = RemoteControl(appliance = VacuumCleaner())
    fancyVacuumCleanerRemoteControl.turnOn()
}