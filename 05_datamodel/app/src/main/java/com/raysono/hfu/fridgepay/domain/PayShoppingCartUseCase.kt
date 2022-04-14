package com.raysono.hfu.fridgepay.domain

import com.raysono.hfu.fridgepay.data.cartRepo
import com.raysono.hfu.fridgepay.domain.model.removeAllProducts

class PayShoppingCartUseCase {

    operator fun invoke(): Boolean {
        val cart = cartRepo.getCurrentCart()
        val updatedCart = removeAllProducts(cart)
        cartRepo.updateCart(updatedCart)
        return true
    }
}
