package fridgepay.hfu.raysono.com.routes

import fridgepay.hfu.raysono.com.model.shoppingCartItemDao
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.put
import io.ktor.server.routing.route
import java.util.UUID

@kotlinx.serialization.Serializable
private data class AddItemRequest(
    val productId: String,
)

fun Route.shoppingCartRouting() {
    route("/v1/cart/{id}") {
        get {
            ifAuthorized { cartId ->
                call.respond(
                    shoppingCartItemDao.itemsByCartId(cartId)
                )
            }
        }

        put {
            ifAuthorized { cartId ->
                val item = call.receive<AddItemRequest>()
                shoppingCartItemDao.addItem(
                    id = UUID.randomUUID().toString(),
                    productId = item.productId,
                    cartId = cartId,
                )?.let { _ ->
                    call.respond(HttpStatusCode.OK)
                } ?: call.respond(HttpStatusCode.BadRequest)
            }
        }

        delete {
            ifAuthorized { cartId ->
                shoppingCartItemDao.deleteAllByCartId(cartId)
                call.respond(HttpStatusCode.OK)
            }
        }
    }
}
