package fridgepay.hfu.raysono.com.routes

import fridgepay.hfu.raysono.com.model.Product
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.route

fun Route.productRouting() {
    route("/v1/product") {
        get {
            call.respond(products)
        }
    }
}

val products = listOf(
    Product(
        id = "a59c0e7b-3a58-4859-934d-1a0393831637",
        name = "Spezi 0.5L",
        icon = "productimage/spezi.jpg",
        description = "Yummy halber Liter Spezi",
        price = 0.8,
    ),
    Product(
        id = "867e5af2-aa53-4e46-9cfd-a1bc9b2929cb",
        name = "Club Mate 0.5L",
        icon = "productimage/clubmate.jpg",
        description = "",
        price = 1.8,
    ),
    Product(
        id = "f16cdf15-6528-4a0b-993c-24d5bf8007a7",
        name = "Lila Balisto",
        icon = "productimage/balisto_purple.png",
        description = "Zweimal lecker Balisto. Nom nom nom",
        price = 0.9,
    ),
)
