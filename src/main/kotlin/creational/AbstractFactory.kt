package creational

/**
 * https://in-kotlin.com/design-patterns/abstract-factory/
 *
 * Lets your produce families or related objects
 * without specifying their concrete classes
 *
 *
 */
open class Vegetation
class Cactus : Vegetation()
class Tree : Vegetation()


open class Terrain
class Sand : Terrain()
class Grass : Terrain()

/**
 * Factory Interface
 */
interface Factory {
    fun createTerrain(): Terrain
    fun createVegetation(): Vegetation
}

/**
 * Desert Factory
 */
class DesertFactory : Factory {

    override fun createTerrain(): Terrain {
        return Sand()
    }

    override fun createVegetation(): Vegetation {
        return Cactus()
    }
}

/**
 * Forrest Factory
 */
class ForrestFactory : Factory {

    override fun createTerrain(): Terrain {
        return Grass()
    }

    override fun createVegetation(): Vegetation {
        return Tree()
    }
}

class Game(private val factory: Factory) {
    private var terrain: Terrain = factory.createTerrain()
    private var tree: Vegetation = factory.createVegetation()
}