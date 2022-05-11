package fridgepay.hfu.raysono.com.plugins

import fridgepay.hfu.raysono.com.model.DatabaseFactory
import io.ktor.server.application.Application

fun Application.configureDatabase() {
    DatabaseFactory.init()
}
