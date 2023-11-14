package beahavioural

/**
 * Definition
 * Iterator pattern is a behavioral design pattern. It provides a way to traverse the elements of a collection
 * without knowing the internal representation of this collection.
 * This collection may be Array, List, Map, Tree, or any other thing.
 *
 * Example
 * https://medium.com/@mahmoud.ibrahim016_51919/iterator-pattern-using-kotlin-937d02beace
 *
 */
fun main() {
    val collection = ProductListCollection()
//    val collection = ProductArrayCollection()

    collection.add(Product(1, "Product 1"))
    collection.add(Product(2, "Product 2"))
    collection.add(Product(3, "Product 3"))

    val iterator = collection.createIterator()

    while (iterator.hasNext()){
        val currentProduct = iterator.next()
        println(currentProduct.toString())
    }
}

interface Iterator {
    fun hasNext(): Boolean
    fun next(): Product
}

data class Product(
    private val id: Int,
    private val name: String
)

class ProductArrayCollection {
    private val products = arrayOfNulls<Product>(10)
    private var count = 0

    fun add(product: Product) {
        if(count < products.size)
            products[count++] = product
    }

    fun createIterator() = ArrayIterator(this)

    class ArrayIterator(private val collection: ProductArrayCollection) : Iterator {

        private var index = 0

        override fun next(): Product {
            return collection.products[index++]!!
        }

        override fun hasNext(): Boolean {
            return index < collection.count
        }
    }
}

class ProductListCollection {
    private val products = ArrayList<Product>()

    fun add(product: Product) {
        products.add(product)
    }

    fun createIterator() = ListIterator(this)

    class ListIterator(private val collection: ProductListCollection) : Iterator {

        private var index = 0

        override fun next(): Product {
            return collection.products[index++]
        }

        override fun hasNext(): Boolean {
            return index < collection.products.size
        }
    }
}