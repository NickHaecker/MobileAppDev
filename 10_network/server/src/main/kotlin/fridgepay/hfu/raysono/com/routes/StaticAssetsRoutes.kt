package fridgepay.hfu.raysono.com.routes

import io.ktor.server.http.content.resources
import io.ktor.server.http.content.static
import io.ktor.server.http.content.staticBasePackage
import io.ktor.server.routing.Route

fun Route.assets() {
    static("/") {
        resources(".")
    }
}
