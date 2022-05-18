package fridgepay.hfu.raysono.com.plugins

import fridgepay.hfu.raysono.com.routes.assets
import fridgepay.hfu.raysono.com.routes.loginRouting
import fridgepay.hfu.raysono.com.routes.productRouting
import fridgepay.hfu.raysono.com.routes.shoppingCartRouting
import fridgepay.hfu.raysono.com.routes.signupRouting
import io.ktor.server.application.Application
import io.ktor.server.auth.authenticate
import io.ktor.server.routing.routing

fun Application.configureRouting() {
    routing {
        assets()
        signupRouting()
        authenticate("auth-basic") {
            loginRouting()
            productRouting()
            shoppingCartRouting()
        }
    }
}
