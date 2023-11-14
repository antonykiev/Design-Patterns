package structual

/**
 * Lets you attach new behaviours to objects
 * by placing these objects inside special wrapper objects
 * that contains the behaviour
 */

fun main(args: Array<String>) {
    val peanutMilkShake = PeanutButterMilkShake(ConcreteMilkShake())
    peanutMilkShake.getTaste()
    val bananaMilkShake = BananaMilkShake(ConcreteMilkShake())
    bananaMilkShake.getTaste()
}

interface MilkShake {
    fun getTaste()
}

class ConcreteMilkShake : MilkShake {
    override fun getTaste() {
        println("It’s milk !")
    }
}

open class MilkShakeDecorator(private var milkShake: MilkShake) : MilkShake {
    override fun getTaste() {
        this.milkShake.getTaste()
    }
}

class BananaMilkShake(m: MilkShake) : MilkShakeDecorator(m) {
    override fun getTaste() {
        super.getTaste()
        println(" Adding Banana flavor to the milk shake !")
        println(" It’s Banana milk shake !")
    }
}

class PeanutButterMilkShake(m: MilkShake) : MilkShakeDecorator(m) {
    override fun getTaste() {
        super.getTaste()
        println(" Adding Peanut butter flavor to the milk shake !");
        println(" It’s Peanut butter milk shake !");
    }
}