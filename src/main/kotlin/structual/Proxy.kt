package structual

/**
 * The Proxy design pattern is used to control access to an object. It involves creating a representative object (proxy)
 * that can perform actions on behalf of the original object.
 *
 * Advantages:
 *
 *     Remote Access: Enables access to an object located remotely, allowing for the management of interactions
 *     and communication with the remote object.
 *
 *     Controlled Access: Provides a level of control over the access to the real object by acting as a gatekeeper,
 *     allowing additional logic to be applied before granting access.
 *
 *     Enhanced Security: Can serve as a security barrier by restricting access or providing additional
 *     authentication/authorization checks before allowing access to the real object.
 *
 *     Lazy Initialization: Allows the creation of the real object only when it's needed, reducing resource usage
 *     and improving performance.
 *
 *     Caching and Optimization: Proxies can implement caching mechanisms to store results from expensive operations,
 *     improving performance by serving cached results.
 *
 * Disadvantages:
 *
 *     Complexity in Implementation: Implementing proxies might introduce additional complexity, especially when
 *     dealing with different types of proxies and their behaviors.
 *
 *     Potential Overhead: Depending on the type of proxy and the additional logic applied, there might
 *     be performance overhead due to the extra processing required.
 *
 *     Synchronization Issues: In multithreaded environments, managing synchronization between the proxy and
 *     the real object can introduce complexities and potential issues.
 *
 *     Potential Bottleneck: If a proxy is responsible for too much functionality or has a high demand,
 *     it might become a bottleneck in the system.
 *
 *     Increased Code Duplication: Introducing multiple proxies for different functionalities might lead to code
 *     duplication and maintenance challenges.
 *
 * While the Proxy pattern offers advantages such as controlled access, enhanced security, and improved performance,
 * it's crucial to consider potential drawbacks such as increased complexity and potential overhead.
 * Careful consideration of the specific use case and the balance between added functionality and performance
 * impact is essential when deciding to use the Proxy pattern.
 */


// Client
fun main() {
    val realInternet = RealInternet()
    val internetProxy = InternetProxy(realInternet)

    internetProxy.connectTo("open.com") // Output: Connecting to open.com
    internetProxy.connectTo("restricted.com") // Output: Access to restricted.com is restricted.
    internetProxy.connectTo("allowed.com") // Output: Connecting to allowed.com
    internetProxy.connectTo("blocked.org") // Output: Access to blocked.org is restricted.
}


// Subject interface
interface Internet {
    fun connectTo(url: String)
}

// Real subject
class RealInternet : Internet {
    override fun connectTo(url: String) {
        println("Connecting to $url")
    }
}

// Proxy
class InternetProxy(private val realInternet: RealInternet) : Internet {
    private val bannedSites = listOf("restricted.com", "blocked.org")

    override fun connectTo(url: String) {
        if (bannedSites.contains(url)) {
            println("Access to $url is restricted.")
        } else {
            realInternet.connectTo(url)
        }
    }
}
