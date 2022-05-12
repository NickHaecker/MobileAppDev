package com.raysono.hfu.fridgepay.domain

import com.raysono.hfu.fridgepay.data.LoginState
import com.raysono.hfu.fridgepay.data.ProductsRepository
import com.raysono.hfu.fridgepay.data.ShoppingCartRepository
import com.raysono.hfu.fridgepay.data.UserSettingsRepository
import com.raysono.hfu.fridgepay.domain.model.ShoppingCartId
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LogoutUseCase(
    private val userSettingsRepository: UserSettingsRepository,
    private val productsRepository: ProductsRepository,
    private val shoppingCartRepository: ShoppingCartRepository,
) {

    suspend operator fun invoke() = withContext(Dispatchers.Default) {
        userSettingsRepository.updateSettings {
            it.copy(
                loginState = LoginState.LoggedOut,
                cartId = ShoppingCartId(""),
            )
        }

        productsRepository.deleteAll()
        shoppingCartRepository.deleteAll()
    }
}
