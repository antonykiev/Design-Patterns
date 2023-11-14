package beahavioural

/**
 * Definition
 * The Command Design Pattern is a software design pattern that allows the encapsulation of a request as an object.
 * This enables the client to parameterize and execute the request at a later time. This pattern is useful when
 * building applications where the client needs to queue, log, or undo operations.
 *
 * Example
 * https://www.baeldung.com/kotlin/command-design-pattern
 */
fun main() {
    val clipboard = Clipboard()
    val editor = TextEditor("Baeldung")
    val invoker = TextEditorInvoker()

    invoker.executeCommand(CutCommand(editor, clipboard)) // cuts last character of initial content to clipboard
    invoker.executeCommand(CopyCommand(editor, clipboard)) // copies current content to clipboard
    invoker.executeCommand(PasteCommand(editor, clipboard)) // pastes current clipboard content to text editor

    editor.print() // prints "BaeldunBaeldun"

    invoker.undo() // undoes the paste command

    editor.print() // prints "Baeldun"

}

interface Command {
    fun execute()
    fun undo()
}

class CutCommand(
    private val receiver: TextEditor,
    private val clipboard: Clipboard
) : Command {

    override fun execute() {
        clipboard.content = receiver.cut()
    }

    override fun undo() {
        receiver.write(clipboard.content)
        clipboard.content = ""
    }
}

class CopyCommand(
    private val receiver: TextEditor,
    private val clipboard: Clipboard
) : Command {

    override fun execute() {
        clipboard.content = receiver.copy()
    }

    override fun undo() {
        clipboard.content = ""
    }
}

class PasteCommand(
    private val receiver: TextEditor,
    private val clipboard: Clipboard
) : Command {

    override fun execute() {
        receiver.write(clipboard.content)
    }

    override fun undo() {
        receiver.delete(clipboard.content)
    }
}

class TextEditorInvoker {
    private val commands = mutableListOf<Command>()

    fun executeCommand(command: Command) {
        commands.add(command)
        command.execute()
    }

    fun undo() {
        if (commands.isNotEmpty()) {
            commands.removeLast().undo()
        }
    }
}

class TextEditor(initialContent: String) {

    private var content = initialContent

    fun cut(): String {
        val cutContent = content.takeLast(1)
        content = content.dropLast(1)
        return cutContent
    }

    fun copy(): String {
        return String(content.toCharArray())
    }

    fun write(text: String) {
        content += text
    }

    fun delete(text: String) {
        content = content.removeSuffix(text)
    }

    fun print() {
        println(content)
    }
}

data class Clipboard(var content: String = "")


