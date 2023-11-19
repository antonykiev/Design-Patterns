package beahavioural

/**
 * In the Strategy pattern using the Template Method design, you create a family of algorithms, encapsulate each one,
 * and make them interchangeable. In Kotlin, you can combine the Strategy pattern with the Template Method pattern
 * to create a flexible system.
 *
 * Advantages:
 *
 *     Enhanced Flexibility: It allows you to define a family of algorithms, encapsulate each one as a separate class,
 *     and make them interchangeable. This flexibility enables the selection of an appropriate algorithm at runtime
 *     or based on specific conditions.
 *
 *     Encapsulation of Algorithms: Algorithms are encapsulated within their own classes, promoting better code
 *     organization and allowing for easier maintenance and testing of individual strategies.
 *
 *     Open/Closed Principle: It follows the Open/Closed Principle, allowing new strategies to be added without
 *     changing the context class, ensuring that the code is open for extension but closed for modification.
 *
 *     Promotes Code Reusability: Different contexts can use the same strategy, promoting code reuse and reducing
 *     duplication across multiple classes that require similar behavior.
 *
 * Disadvantages:
 *
 *     Increased Number of Objects: Implementing the Strategy pattern might lead to an increased number of objects
 *     if each strategy is implemented as a separate class. This could potentially impact memory usage and performance.
 *
 *     Complexity and Indirection: The use of multiple classes for different strategies can increase the complexity
 *     of the codebase, especially if there are many strategies and context classes involved. Indirection might make the code harder to follow.
 *
 *     Potential Overhead: Depending on the granularity of strategies and the frequency of switching between them,
 *     there might be a runtime overhead associated with the selection and execution of different strategies.
 *
 *     Choosing the Right Strategy: Selecting the appropriate strategy at runtime might require additional logic or
 *     decision-making, which can sometimes add complexity to the system.
 *
 *
 * In summary, while the Strategy pattern provides a clean way to encapsulate and interchange algorithms, offering
 * flexibility and promoting code reuse, it's important to carefully consider its usage, especially in cases where
 * there might be a trade-off between complexity and flexibility.
 *
 */

// Usage example
fun main() {
    val dataToSort = intArrayOf(9, 2, 5, 1, 7)

    val bubbleSort = BubbleSortStrategy()
    val quickSort = QuickSortStrategy()

    val sorterUsingBubbleSort = Sorter(bubbleSort)
    val sorterUsingQuickSort = Sorter(quickSort)

    println("Sorting with Bubble Sort:")
    sorterUsingBubbleSort.sortData(dataToSort.copyOf())

    println("\nSorting with Quick Sort:")
    sorterUsingQuickSort.sortData(dataToSort.copyOf())
}

// Strategy interface
interface SortingStrategy {
    fun sort(arr: IntArray)
}

// Concrete sorting strategies
class BubbleSortStrategy : SortingStrategy {
    override fun sort(arr: IntArray) {
        println("Sorting using Bubble Sort")
        // Bubble sort implementation
        // (for brevity, a simple sorting algorithm is used)
        arr.sort()
    }
}

class QuickSortStrategy : SortingStrategy {
    override fun sort(arr: IntArray) {
        println("Sorting using Quick Sort")
        // Quick sort implementation
        // (for brevity, a simple sorting algorithm is used)
        arr.sort()
    }
}

// Context class utilizing the strategy pattern with the template method
class Sorter(private val sortingStrategy: SortingStrategy) {
    // Template method defining the sorting algorithm
    fun sortData(data: IntArray) {
        sortingStrategy.sort(data)
        println("Sorted Data: ${data.joinToString()}")
    }
}