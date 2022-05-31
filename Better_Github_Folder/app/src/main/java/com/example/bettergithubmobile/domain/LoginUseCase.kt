package com.example.bettergithubmobile.domain

import com.example.bettergithubmobile.data.UserSettingsRepository
import java.util.Base64
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginUseCase(
    private val userSettingsRepository: UserSettingsRepository
){
    suspend operator fun invoke(
        username: String,
        password: String,
    ) = withContext(Dispatchers.Default){
        val credentials = Base64.getEncoder().encodeToString("$username:$password".toByteArray())

        userSettingsRepository.updateSettings{
            it.copy(
                loginState = LoginState.LoggingIn(credentials),
            )
        }
        val jwtToken = jwtProvider.createJWT()
        val gitHub = GitHubBuilder().withJwtToken(jwtToken)

    }
}