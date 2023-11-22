package structual

/**
 *
 * The Adapter design pattern is a structural pattern that allows incompatible interfaces to work together.
 * It converts the interface of one class into another interface that clients expect. Here are its advantages
 * and disadvantages:
 * Advantages:
 *
 *     Compatibility between Interfaces: Allows two incompatible interfaces to work together, enabling communication
 *     between classes that couldn't otherwise collaborate.
 *
 *     Reuse of Existing Classes: Enables the use of existing classes that have different interfaces without modifying
 *     their original code, promoting code reusability.
 *
 *     Solves Interface Mismatch: Helps in adapting or wrapping incompatible interfaces to make them compatible,
 *     eliminating the need for significant refactoring or changing existing code.
 *
 *     Improves System Flexibility: Introduces flexibility by allowing classes to work together despite
 *     differing interfaces, making the system more adaptable to changes.
 *
 *     Easy Integration of Third-Party Libraries: Facilitates the integration of third-party libraries or
 *     components with different interfaces into the existing codebase.
 *
 * Disadvantages:
 *
 *     Complexity in Code Understanding: Introducing adapters might increase code complexity, especially when multiple
 *     adapters are used, making the system harder to understand.
 *
 *     Potential Performance Overhead: Depending on the implementation, using adapters might introduce a performance
 *     overhead due to the additional layers of abstraction and redirection.
 *
 *     Increased Design Complexity: Adapters add an additional layer between classes, which might increase the overall
 *     design complexity of the system.
 *
 *     Potential Overuse: Overuse of adapters can lead to a codebase with numerous adapters, which might be harder
 *     to manage and maintain.
 *
 *     Possible Decrease in Readability: The use of adapters might make the code less readable if not properly
 *     documented or if naming conventions are unclear.
 *
 * While the Adapter pattern offers advantages in terms of interoperability and reusability by bridging
 * incompatible interfaces, it's essential to consider potential drawbacks such as increased complexity and
 * reduced performance. Careful consideration of the specific use case and implementation is crucial to determine
 * whether the use of adapters is beneficial for the system's overall architecture and maintainability.
 *
 */

interface MediaPlayer {
    fun play(audioType: String, fileName: String)
}

interface AdvancedMediaPlayer {
    fun playVlc(fileName: String)
    fun playMp4(fileName: String)
}

class VlcPlayer : AdvancedMediaPlayer {
    override fun playVlc(fileName: String) {
        println("Playing vlc file. Name: $fileName")
    }

    override fun playMp4(fileName: String) {
    }
}

class Mp4Player : AdvancedMediaPlayer {
    override fun playVlc(fileName: String) {
    }

    override fun playMp4(fileName: String) {
        println("Playing mp4 file. Name: $fileName")
    }
}

class MediaAdapter(audioType: String) : MediaPlayer {
    private var advancedMediaPlayer: AdvancedMediaPlayer? = null

    init {
        if (audioType.equals("vlc", true)) {
            advancedMediaPlayer = VlcPlayer()
        } else if (audioType.equals("mp4", true)) {
            advancedMediaPlayer = Mp4Player()
        }
    }

    override fun play(audioType: String, fileName: String) {
        if (audioType.equals("vlc", true)) {
            advancedMediaPlayer?.playVlc(fileName)
        } else if (audioType.equals("mp4", true)) {
            advancedMediaPlayer?.playMp4(fileName)
        }
    }
}

class AudioPlayer : MediaPlayer {
    private var mediaAdapter: MediaAdapter? = null

    override fun play(audioType: String, fileName: String) {
        if (audioType.equals("mp3", true)) {
            println("Playing mp3 file. Name: $fileName")
        } else if (audioType.equals("vlc", true) || audioType.equals("mp4", true)) {
            mediaAdapter = MediaAdapter(audioType)
            mediaAdapter?.play(audioType, fileName)
        } else {
            println("Invalid media. $audioType format not supported")
        }
    }
}