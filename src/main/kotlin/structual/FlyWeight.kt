package structual

import structual.CarFactory.getRaceCar

fun main(args: Array<String>) {
    val raceCars = arrayOf(
        RaceCarClient("Midget"),
        RaceCarClient("Midget"),
        RaceCarClient("Midget"),
        RaceCarClient("Sprint"),
        RaceCarClient("Sprint"),
        RaceCarClient("Sprint")
    )
    raceCars[0].moveCar(29, 3112)
    raceCars[1].moveCar(39, 2002)
    raceCars[2].moveCar(49, 1985)
    raceCars[3].moveCar(59, 2543)
    raceCars[4].moveCar(69, 2322)
    raceCars[5].moveCar(79, 2135)
    /*Output and observe the number of instances created*/
    println("Midget Car Instances: " + FlyweightMidgetCar.num)
    println("Sprint Car Instances: " + FlyweightSprintCar.num)
}

abstract class RaceCar {
    var name: String? = null
    var speed = 0
    var horsePower = 0

    abstract fun moveCar(currentX: Int, currentY: Int, newX: Int, newY: Int)
}

class FlyweightMidgetCar : RaceCar() {
    init {
        num++
    }

    override fun moveCar(currentX: Int, currentY: Int, newX: Int, newY: Int) {
        println("New location of $name is X$newX - Y$newY")
    }

    companion object {
        var num = 0
    }
}

class FlyweightSprintCar : RaceCar() {
    init {
        num++
    }

    override fun moveCar(currentX: Int, currentY: Int, newX: Int, newY: Int) {
        println("New location of $name is X$newX - Y$newY")
    }

    companion object {
        var num = 0
    }
}

object CarFactory {

    private val flyweights: MutableMap<String, RaceCar> = HashMap()

    fun getRaceCar(key: String): RaceCar? {
        if (flyweights.containsKey(key)) {
            return flyweights[key]
        }
        val raceCar: RaceCar
        when (key) {
            "Midget" -> {
                raceCar = FlyweightMidgetCar()
                raceCar.name = "Midget Car"
                raceCar.speed = 140
                raceCar.horsePower = 400
            }
            "Sprint" -> {
                raceCar = FlyweightSprintCar()
                raceCar.name = "Sprint Car"
                raceCar.speed = 160
                raceCar.horsePower = 1000
            }
            else -> throw IllegalArgumentException("Unsupported car type.")
        }
        flyweights[key] = raceCar
        return raceCar
    }
}

class RaceCarClient(name: String?) {
    private val raceCar: RaceCar?
    private var currentX = 0
    private var currentY = 0

    init {
        raceCar = getRaceCar(name!!)
    }

    fun moveCar(newX: Int, newY: Int) {
        raceCar!!.moveCar(
            currentX,
            currentY, newX, newY
        )
        currentX = newX
        currentY = newY
    }
}