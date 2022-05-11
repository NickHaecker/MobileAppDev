package com.raysono.hfu.fridgepay.domain

import com.raysono.hfu.fridgepay.data.cartRepo
import com.raysono.hfu.fridgepay.data.network.WebService
import com.raysono.hfu.fridgepay.domain.model.removeAllProducts
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PayShoppingCartUseCase(
    private val webService: WebService,
) {

    suspend operator fun invoke(): Boolean = withContext(Dispatchers.Default) {
        val cart = cartRepo.getCurrentCart()
        val updatedCart = removeAllProducts(cart)
        cartRepo.updateCart(updatedCart)

        // TODO delete all products from cart in backend

        true
    }
}
