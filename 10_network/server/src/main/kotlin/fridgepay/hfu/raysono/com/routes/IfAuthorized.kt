package fridgepay.hfu.raysono.com.routes

import fridgepay.hfu.raysono.com.model.userDao
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.application.call
import io.ktor.server.auth.UserIdPrincipal
import io.ktor.server.auth.principal
import io.ktor.server.response.respond
import io.ktor.util.pipeline.PipelineContext

suspend fun PipelineContext<*, ApplicationCall>.ifAuthorized(
    f: suspend PipelineContext<*, ApplicationCall>.(cartId: String) -> Unit
) {
    call.principal<UserIdPrincipal>()?.name?.let { userId ->
        userDao.userById(userId)?.cartId?.let { cartId ->
            if (cartId == call.parameters["id"]) {
                f(cartId)
            } else {
                call.respond(HttpStatusCode.NotFound)
            }
        }  ?: call.respond(HttpStatusCode.Unauthorized)
    } ?: call.respond(HttpStatusCode.Unauthorized)
}
