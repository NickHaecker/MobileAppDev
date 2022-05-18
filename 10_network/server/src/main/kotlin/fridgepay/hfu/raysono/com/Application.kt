package fridgepay.hfu.raysono.com

import fridgepay.hfu.raysono.com.plugins.configureDatabase
import fridgepay.hfu.raysono.com.plugins.configureRouting
import fridgepay.hfu.raysono.com.plugins.configureSecurity
import fridgepay.hfu.raysono.com.plugins.configureSerialization
import fridgepay.hfu.raysono.com.plugins.configureSlowResponses
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

fun main() {
    embeddedServer(Netty, port = 8080, host = "127.0.0.1") {
        configureDatabase()
        configureRouting()
        configureSecurity()
        configureSerialization()
        configureSlowResponses()
    }.start(wait = true)
}
