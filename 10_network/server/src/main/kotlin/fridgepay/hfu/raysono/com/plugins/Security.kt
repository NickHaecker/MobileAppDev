package fridgepay.hfu.raysono.com.plugins

import fridgepay.hfu.raysono.com.model.userDao
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.auth.Authentication
import io.ktor.server.auth.UserIdPrincipal
import io.ktor.server.auth.basic

fun Application.configureSecurity() {
    install(Authentication) {
        basic("auth-basic") {
            realm = "fridgepay"
            validate { credentials ->
                val user = userDao.userByName(credentials.name)
                if (user?.password == credentials.password) {
                    UserIdPrincipal(user.id)
                } else {
                    null
                }
            }
        }
    }
}
