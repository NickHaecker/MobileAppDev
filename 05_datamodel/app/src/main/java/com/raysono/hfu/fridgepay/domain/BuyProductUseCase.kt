package com.raysono.hfu.fridgepay.domain

import com.raysono.hfu.fridgepay.data.cartRepo
import com.raysono.hfu.fridgepay.data.productRepo
import com.raysono.hfu.fridgepay.domain.model.ProductId
import com.raysono.hfu.fridgepay.domain.model.addProduct

class BuyProductUseCase {

    operator fun invoke(productId: ProductId): Boolean {
        val product = productRepo.getBuyableProductById(productId)
        if (product == null) {
            return false
        }

        val cart = cartRepo.getCurrentCart()
        val updatedCart = addProduct(cart, product)
        cartRepo.updateCart(updatedCart)
        return true
    }
}
