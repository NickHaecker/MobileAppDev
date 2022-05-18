package fridgepay.hfu.raysono.com.model

import fridgepay.hfu.raysono.com.model.DatabaseFactory.dbQuery
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select

@Serializable
data class ShoppingCartItem(
    val id: String,
    val productId: String,
    val cartId: String,
)

object ShoppingCartItems : Table() {
    val id = varchar("id", 128)
    val productId = varchar("productId", 128)
    val cartId = varchar("cartId", 128)
    override val primaryKey = PrimaryKey(id)
}

interface ShoppingCartItemDao {
    suspend fun itemsByCartId(id: String): List<ShoppingCartItem>
    suspend fun addItem(
        id: String,
        productId: String,
        cartId: String,
    ): ShoppingCartItem?

    suspend fun deleteAllByCartId(id: String): Int
}

class ShoppingCartItemDaoImpl : ShoppingCartItemDao {
    override suspend fun itemsByCartId(id: String): List<ShoppingCartItem> = dbQuery {
        ShoppingCartItems.select {
            ShoppingCartItems.cartId.eq(id)
        }.map(::resultRowToShoppingCartItem)
    }

    override suspend fun addItem(id: String, productId: String, cartId: String): ShoppingCartItem? = dbQuery {
        val insertStatement = ShoppingCartItems.insert {
            it[ShoppingCartItems.id] = id
            it[ShoppingCartItems.productId] = productId
            it[ShoppingCartItems.cartId] = cartId
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToShoppingCartItem)
    }

    override suspend fun deleteAllByCartId(id: String): Int = dbQuery {
        ShoppingCartItems.deleteWhere {
            ShoppingCartItems.cartId.eq(id)
        }
    }

    private fun resultRowToShoppingCartItem(row: ResultRow) = ShoppingCartItem(
        id = row[ShoppingCartItems.id],
        productId = row[ShoppingCartItems.productId],
        cartId = row[ShoppingCartItems.cartId],
    )
}

val shoppingCartItemDao: ShoppingCartItemDao = ShoppingCartItemDaoImpl()
