package fridgepay.hfu.raysono.com.model

import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {
    fun init() {
        val driverClassName = "org.h2.Driver"
        val jdbcUrl = "jdbc:h2:file:./db.h2"
        val database = Database.connect(jdbcUrl, driverClassName)
        transaction(database) {
            SchemaUtils.create(Users)
            SchemaUtils.create(ShoppingCartItems)
        }
    }

    suspend fun <T> dbQuery(f: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { f() }
}
