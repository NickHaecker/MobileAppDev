package com.raysono.hfu.fridgepay.domain

import com.raysono.hfu.fridgepay.data.UserSettingsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginUseCase(
    private val userSettingsRepository: UserSettingsRepository,
    private val downloadProductsUseCase: DownloadProductsUseCase,
    private val downloadShoppingCartUseCase: DownloadShoppingCartUseCase,
) {

    suspend operator fun invoke(
        username: String,
        password: String,
    ) = withContext(Dispatchers.Default) {
        // TODO
        //  store credentials (state == logging-in)
        //  get cart-id of the user
        //  download products
        //  download shopping cart
        //  set login-state to logged-in
    }
}
