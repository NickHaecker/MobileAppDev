package com.raysono.hfu.fridgepay.domain

import com.raysono.hfu.fridgepay.data.ProductsRepository
import com.raysono.hfu.fridgepay.data.ShoppingCartRepository
import com.raysono.hfu.fridgepay.data.UserSettingsRepository
import com.raysono.hfu.fridgepay.data.network.WebService
import com.raysono.hfu.fridgepay.domain.model.ProductId
import com.raysono.hfu.fridgepay.domain.model.ShoppingCart
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
            val cartId = userSettingsRepository.getSettings().cartId
            val response = webService.getCartById(cartId.value)
            val groups = response.groupingBy {
                it.productId
            }
            val itemAndCount = groups.eachCount()

            if (itemAndCount.isEmpty()) {
                val cart = ShoppingCart.Empty(cartId)
                shoppingCartRepository.updateCart(cart)
            } else {
                val products = itemAndCount.mapKeys { (productId, _) ->
                    productsRepository.getBuyableProductById(ProductId(productId))
                }.filterKeys { it != null }.mapKeys { it.key!! }
                val cart = ShoppingCart.Unpayed.create(
                    cartId,
                    products,
                    products.entries.sumOf { it.key.price * it.value }
                )
                shoppingCartRepository.updateCart(cart)
            }
        }
    }
}
