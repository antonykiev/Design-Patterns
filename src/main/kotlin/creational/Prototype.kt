package creational

/**
 * Lets you copy existing objects without
 * making your code dependent on their classes
 *
 * Advantages:
 *
 *     Reduced Object Creation Overhead: Cloning an existing object can be faster and more efficient than creating
 *     a new object from scratch, especially for complex objects.
 *
 *     Flexibility in Object Creation: Allows for creating new objects by copying existing ones without knowing their
 *     concrete classes, providing flexibility in object creation.
 *
 *     Customization and Variation: Enables the creation of new objects with customized properties or variations
 *     by altering the cloned objects without affecting the original prototype.
 *
 *     Improves Performance: Can improve performance when the cost of initializing an object is high, as it avoids
 *     repetitive initialization.
 *
 *     Encourages Loose Coupling: Reduces dependencies between client code and concrete classes by relying on cloning
 *     mechanisms rather than directly instantiating classes.
 *
 * Disadvantages:
 *
 *     Deep Copy Complexity: Creating deep copies of complex objects with interconnected references
 *     might be challenging and error-prone.
 *
 *     Shallow Copy Limitations: Some languages provide only shallow copy mechanisms by default, leading to potential
 *     issues when dealing with nested objects and references.
 *
 *     Maintaining Prototypes: Managing and maintaining a collection of prototypes can become complex, especially
 *     when dealing with a large number of varying objects.
 *
 *     Difficulty in Identifying Cloned Objects: It might be challenging to identify differences between cloned
 *     objects and their prototypes in some cases.
 *
 *     Security Concerns: Depending on the implementation, direct access to prototype instances might lead
 *     to security risks if modifications are made to the original prototypes unintentionally.
 *
 * While the Prototype pattern offers advantages such as reduced object creation overhead, flexibility,
 * and customization, it's important to consider potential complexities and limitations, especially regarding object
 * copying mechanisms and the management of prototypes. Evaluating whether the benefits of cloning objects outweigh
 * the associated complexities is essential when deciding to use the Prototype pattern in a particular context.
 */

fun main() {
    val project = Project(1, "testName", "testSource")
    println(project.toString())
    println("\n====================\n")
    val copyProject = ProjectFactory(project).project
    println(copyProject.toString())
    println("\n====================\n")
}

interface Copyable {
    fun copy(): Any
}

class Project(
    val id: Int,
    val projectName: String,
    val sourceCode: String,
) : Copyable {

    override fun copy(): Any {
        return Project(id, projectName, sourceCode)
    }

    override fun toString(): String {
        return "Project(id=$id, projectName='$projectName', sourceCode='$sourceCode')"
    }
}

class ProjectFactory(
    val project: Project
) {
    fun cloneProject(): Project {
        return project.copy() as Project
    }
}