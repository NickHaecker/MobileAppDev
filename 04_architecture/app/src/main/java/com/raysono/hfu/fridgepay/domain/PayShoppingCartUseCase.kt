package com.raysono.hfu.fridgepay.domain

import com.raysono.hfu.fridgepay.data.cartRepo

class PayShoppingCartUseCase {

    operator fun invoke(): Boolean {
        val cart = cartRepo.getCurrentCart()
        val updatedCart = removeAllProducts(cart)
        cartRepo.updateCart(updatedCart)
        return true
    }
}

fun removeAllProducts(cart: ShoppingCart): ShoppingCart {
    return cart.copy(
        products = emptyMap(),
    )
}
