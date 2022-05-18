package fridgepay.hfu.raysono.com.plugins

import io.ktor.server.application.Application
import io.ktor.server.application.ApplicationCallPipeline
import kotlin.random.Random
import kotlin.time.Duration.Companion.milliseconds
import kotlinx.coroutines.delay

fun Application.configureSlowResponses() {
    intercept(ApplicationCallPipeline.Plugins) {
        delay(Random.nextLong(0, 1_000).milliseconds)
    }
}
