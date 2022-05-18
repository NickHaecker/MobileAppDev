package com.raysono.hfu.fridgepay.domain

import com.raysono.hfu.fridgepay.data.LoginState
import com.raysono.hfu.fridgepay.data.UserSettingsRepository
import com.raysono.hfu.fridgepay.data.network.WebService
import com.raysono.hfu.fridgepay.domain.model.ShoppingCartId
import java.util.Base64
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginUseCase(
    private val webService: WebService,
    private val userSettingsRepository: UserSettingsRepository,
    private val downloadProductsUseCase: DownloadProductsUseCase,
    private val downloadShoppingCartUseCase: DownloadShoppingCartUseCase,
) {

    suspend operator fun invoke(
        username: String,
        password: String,
    ) = withContext(Dispatchers.Default) {
        val credentials = Base64.getEncoder().encodeToString("$username:$password".toByteArray())
        userSettingsRepository.updateSettings {
            it.copy(
                loginState = LoginState.LoggingIn(credentials),
            )
        }

        val response = webService.login()

        userSettingsRepository.updateSettings {
            it.copy(
                cartId = ShoppingCartId(response.cartId)
            )
        }

        downloadProductsUseCase()
        downloadShoppingCartUseCase()

        userSettingsRepository.updateSettings {
            it.copy(
                loginState = LoginState.LoggedIn(credentials),
            )
        }
    }
}
