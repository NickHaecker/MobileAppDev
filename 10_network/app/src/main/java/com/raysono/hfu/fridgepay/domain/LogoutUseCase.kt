package com.raysono.hfu.fridgepay.domain

import com.raysono.hfu.fridgepay.data.ProductsRepository
import com.raysono.hfu.fridgepay.data.ShoppingCartRepository
import com.raysono.hfu.fridgepay.data.UserSettingsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LogoutUseCase(
    private val userSettingsRepository: UserSettingsRepository,
    private val productsRepository: ProductsRepository,
    private val shoppingCartRepository: ShoppingCartRepository,
) {

    suspend operator fun invoke() = withContext(Dispatchers.Default) {
        // TODO
        //  set login-state to logged-out
        //  clear shopping cart ID
        //  delete all products
        //  delete all shopping cart items
    }
}
