package creational

/**
 * Lets you copy existing objects without
 * making your code dependent on their classes
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