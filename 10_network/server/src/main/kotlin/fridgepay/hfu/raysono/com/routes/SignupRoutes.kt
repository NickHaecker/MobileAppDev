package fridgepay.hfu.raysono.com.routes

import fridgepay.hfu.raysono.com.model.userDao
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import java.util.UUID

@kotlinx.serialization.Serializable
data class SignupRequest(
    val userName: String,
    val password: String,
)

@kotlinx.serialization.Serializable
data class SignupResponse(
    val cartId: String,
)

fun Route.signupRouting() {
    route("/v1/user") {
        post {
            val userData = call.receive<SignupRequest>()
            if (userData.userName.isBlank() || userData.password.isBlank()) {
                call.respond(HttpStatusCode.BadRequest)
            } else {
                if (userDao.userByName(userData.userName) != null) {
                    call.respond(HttpStatusCode.BadRequest, "User already exists")
                } else {
                    userDao.addUser(
                        UUID.randomUUID().toString(),
                        userData.userName,
                        userData.password,
                        UUID.randomUUID().toString(),
                    )?.let { newUser ->
                        call.respond(SignupResponse(newUser.cartId))
                    } ?: call.respond(HttpStatusCode.InternalServerError)
                }
            }
        }
    }
}
