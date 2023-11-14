package structual

/**
 * https://ahmed-shaaban-elhdah.medium.com/proxy-design-pattern-in-kotlin-structure-design-pattern-1-df1f7a1b01ce
 */
class NetworkService {
    fun callApi(item: String): Boolean = true
    fun getItemInCart(): String = ""
}

class DatabaseClass {
    fun saveResult(result: String): Boolean = true
    fun getResult(): String = ""
}

interface CartRepo {
    fun addToCart(item: String): Boolean
}

class AuthorizedUserCartRepo(
    private val networkSerivce: NetworkService,
    private val databaseClass: DatabaseClass
) : CartRepo {
    override fun addToCart(item: String): Boolean {
        val result = networkSerivce.callApi(item)
        databaseClass.saveResult(item)
        return result
    }
}

class GuestUserCartRepo(
    private val databaseClass: DatabaseClass
) : CartRepo {
    override fun addToCart(item: String): Boolean {
        return databaseClass.saveResult(item)
    }
}

class ProxyCartRepo(
    val pref: SharedPreferences,
    val networkSerivce: NetworkService,
    val databaseClass: DatabaseClass
) : CartRepo {

    var repo: CartRepo? = null

    override fun addToCart(item: String): Boolean {
        val type = pref.getString("type", "guest")

        if (type.equals("authorized")) {
            if (repo == null)
                repo = AuthorizedUserCartRepo(networkSerivce, databaseClass)
        } else
            if (repo == null)
                repo = GuestUserCartRepo(databaseClass)

        return repo!!.addToCart(item)
    }
}

class SharedPreferences {
    fun getString(key: String, defValue: String): String {
        return "authorized"
    }
}

