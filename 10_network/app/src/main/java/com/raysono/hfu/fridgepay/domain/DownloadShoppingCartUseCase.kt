package com.raysono.hfu.fridgepay.domain

import com.raysono.hfu.fridgepay.data.ProductsRepository
import com.raysono.hfu.fridgepay.data.ShoppingCartRepository
import com.raysono.hfu.fridgepay.data.UserSettingsRepository
import com.raysono.hfu.fridgepay.data.network.WebService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DownloadShoppingCartUseCase(
    private val webService: WebService,
    private val userSettingsRepository: UserSettingsRepository,
    private val productsRepository: ProductsRepository,
    private val shoppingCartRepository: ShoppingCartRepository,
) {

    suspend operator fun invoke() = withContext(Dispatchers.Default) {
        kotlin.runCatching {
            // TODO download shopping cart
        }
    }
}
