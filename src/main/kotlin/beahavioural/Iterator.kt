package beahavioural

/**
 * The Iterator design pattern is a behavioral pattern that provides a way to access elements of a collection
 * sequentially without exposing its underlying representation. Here are its advantages and disadvantages:
 *
 * Advantages:
 *
 *     Encapsulation: It encapsulates the logic for traversing a collection, hiding the underlying structure
 *     and implementation details.
 *
 *     Flexibility: It allows various traversal methods (e.g., sequential, reverse, etc.) without affecting
 *     the client code using the iterator.
 *
 *     Ease of Use: Simplifies client code by providing a standard way to iterate over different types of collections.
 *
 *     Supports Multiple Iterators: Collections can have multiple iterators simultaneously without interfering
 *     with each other.
 *
 *     Separation of Concerns: The pattern separates the iteration logic from the collection, promoting better code
 *     organization and maintainability.
 *
 *     Enhanced Compatibility: Clients can work with different types of collections implementing a common iterator
 *     interface, promoting code reusability.
 *
 * Disadvantages:
 *
 *     Performance Overhead: Depending on the implementation, using iterators might introduce some performance
 *     overhead due to extra function calls and object creations.
 *
 *     Complexity in Implementation: Implementing iterators might require additional code and complexity,
 *     especially in scenarios with complex data structures.
 *
 *     Potential Modification Issues: If the underlying collection structure changes, it might affect the iterator,
 *     potentially leading to unexpected behavior.
 *
 *     Limited Functionality: Some types of iterators might not support operations like deletion or addition
 *     of elements during traversal.
 *
 *     Increased Memory Usage: Iterators might consume additional memory, especially if they store information
 *     about the current position or state.
 *
 * While the Iterator pattern provides numerous benefits, such as encapsulation, flexibility, and ease of use,
 * it's essential to consider potential drawbacks like performance overhead and implementation complexity,
 * especially in scenarios where the cost of using iterators outweighs the benefits. Overall,
 * the Iterator pattern is particularly useful when dealing with collections and simplifying the iteration process,
 * promoting code maintainability and reusability.
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