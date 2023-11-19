package structual

/**
 * The Facade pattern is a structural design pattern that provides a simplified interface to a complex system,
 * providing a higher-level interface that makes the system easier to use.
 * It encapsulates the complexities of subsystems and provides a single unified interface.
 *
 * The Facade design pattern offers several advantages and may also have some drawbacks:
 *
 * Advantages:
 *
 *     Simplified Interface: Facade provides a simplified and unified interface to a complex system,
 *     making it easier to use and reducing the learning curve for users or client code.
 *
 *     Encapsulation of Complexity: It encapsulates the complex interactions and operations of multiple
 *     subsystems behind a single facade class. Users don't need to understand the intricacies of the subsystems,
 *     enhancing abstraction and hiding implementation details.
 *
 *     Decoupling and Reduced Dependency: Facade promotes loose coupling between client code and subsystems
 *     by abstracting away the dependencies. This allows changes within subsystems without affecting clients
 *     using the facade.
 *
 *     Enhanced Maintainability: It improves code maintainability by centralizing the code that interacts with
 *     ultiple subsystems. Changes in the complex system can be localized within the facade, minimizing
 *     the impact on other parts of the codebase.
 *
 * Disadvantages:
 *
 *     Limited Flexibility: As the facade provides a simplified interface, it might not cover all functionalities
 *     or variations provided by the subsystems. Users relying solely on the facade might find it restrictive
 *     if they need more advanced or specific functionalities.
 *
 *     Potential Violation of SRP: Facade classes might grow in complexity if they take on too many responsibilities.
 *     This can lead to a violation of the Single Responsibility Principle (SRP), making the class harder
 *     to maintain and understand.
 *
 *     Additional Abstraction Layer: Introducing a facade adds an additional abstraction layer, which could
 *     potentially introduce performance overhead, especially if the facade is repeatedly used for small, simple tasks.
 *
 *     Coupling with Changes in Subsystems: If the facade closely reflects the internal structure of subsystems and
 *     those subsystems undergo frequent changes, it might require constant updates to the facade, potentially
 *     reducing its benefits.
 *
 * In summary, the Facade pattern provides a convenient, simplified interface to a complex system,
 * enhancing usability and encapsulating complexities. However, it's crucial to balance its usage,
 * considering potential limitations like reduced flexibility, increased abstraction layers, and ensuring
 * that it does not violate principles like the Single Responsibility Principle.
 *
 */


// Usage example
fun main() {
    val dvdPlayer = DVDPlayer()
    val amplifier = Amplifier()
    val speakers = Speakers()
    val lights = Lights()

    val homeTheater = HomeTheaterFacade(dvdPlayer, amplifier, speakers, lights)

    homeTheater.watchMovie("The Avengers")
    homeTheater.endMovie()
}

// Complex subsystems
class DVDPlayer {
    fun on() {
        println("DVD player turned on")
    }

    fun play(movie: String) {
        println("Playing movie: $movie")
    }

    fun off() {
        println("DVD player turned off")
    }
}

class Amplifier {
    fun on() {
        println("Amplifier turned on")
    }

    fun setDVD() {
        println("Amplifier setting DVD")
    }

    fun setSurroundSound() {
        println("Amplifier setting surround sound")
    }

    fun off() {
        println("Amplifier turned off")
    }
}

class Speakers {
    fun on() {
        println("Speakers turned on")
    }

    fun setVolume(volume: Int) {
        println("Speakers set volume to $volume")
    }

    fun off() {
        println("Speakers turned off")
    }
}

class Lights {
    fun dim() {
        println("Lights dimmed")
    }

    fun off() {
        println("Lights turned off")
    }
}

// Facade providing a simple interface
class HomeTheaterFacade(
    private val dvdPlayer: DVDPlayer,
    private val amplifier: Amplifier,
    private val speakers: Speakers,
    private val lights: Lights
) {
    fun watchMovie(movie: String) {
        println("Get ready to watch a movie...")
        lights.dim()
        dvdPlayer.on()
        amplifier.on()
        amplifier.setDVD()
        amplifier.setSurroundSound()
        speakers.on()
        speakers.setVolume(10)
        dvdPlayer.play(movie)
    }

    fun endMovie() {
        println("Shutting down the home theater...")
        dvdPlayer.off()
        amplifier.off()
        speakers.off()
        lights.off()
    }
}