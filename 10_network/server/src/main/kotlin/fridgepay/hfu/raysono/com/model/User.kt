package fridgepay.hfu.raysono.com.model

import fridgepay.hfu.raysono.com.model.DatabaseFactory.dbQuery
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select

@Serializable
data class User(
    val id: String,
    val name: String,
    val password: String,
    val cartId: String,
)

object Users : Table() {
    val id = varchar("id", 128)
    val name = varchar("name", 1024)
    val password = varchar("password", 1024)
    val cartId = varchar("cartId", 1024)
    override val primaryKey = PrimaryKey(id)
}

interface UserDao {
    suspend fun userByName(id: String): User?
    suspend fun userById(id: String): User?
    suspend fun addUser(
        id: String,
        name: String,
        password: String,
        cartId: String,
    ): User?
}

class UserDaoImpl : UserDao {
    override suspend fun userByName(id: String): User? = dbQuery {
        Users.select {
            Users.name.eq(id)
        }.firstOrNull()?.let(::resultRowToUser)
    }
    override suspend fun userById(id: String): User? = dbQuery {
        Users.select {
            Users.id.eq(id)
        }.firstOrNull()?.let(::resultRowToUser)
    }

    override suspend fun addUser(id: String, name: String, password: String, cartId: String): User? = dbQuery {
        val insertStatement = Users.insert {
            it[Users.id] = id
            it[Users.name] = name
            it[Users.password] = password
            it[Users.cartId] = cartId
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToUser)
    }

    private fun resultRowToUser(row: ResultRow) = User(
        id = row[Users.id],
        name = row[Users.name],
        password = row[Users.password],
        cartId = row[Users.cartId],
    )
}

val userDao: UserDao = UserDaoImpl()
