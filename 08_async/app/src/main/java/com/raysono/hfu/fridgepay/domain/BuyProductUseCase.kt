package com.raysono.hfu.fridgepay.domain

import com.raysono.hfu.fridgepay.data.cartRepo
import com.raysono.hfu.fridgepay.data.productRepo
import com.raysono.hfu.fridgepay.domain.model.ProductId
import com.raysono.hfu.fridgepay.domain.model.addProduct
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BuyProductUseCase {

    suspend operator fun invoke(productId: ProductId): Boolean = withContext(Dispatchers.Default) {
        val product = productRepo.getBuyableProductById(productId)
        if (product == null) {
            return@withContext false
        }

        val cart = cartRepo.getCurrentCart()
        val updatedCart = addProduct(cart, product)
        cartRepo.updateCart(updatedCart)
        return@withContext true
    }
}
