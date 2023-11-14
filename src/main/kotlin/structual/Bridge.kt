package structual

/**
 * The bridge pattern allows the Abstraction and the Implementation
 * to be developed independently and the client code can access
 * only the Abstraction part without being concerned about the
 * Implementation part.
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