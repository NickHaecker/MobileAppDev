package com.raysono.hfu.fridgepay.domain

import com.raysono.hfu.fridgepay.data.cartRepo
import com.raysono.hfu.fridgepay.data.productRepo

class BuyProductUseCase {

    operator fun invoke(productId: String): Boolean {
        val product: Product? = productRepo.getProductById(productId)
        if (product == null) {
            return false
        }

        val cart = cartRepo.getCurrentCart()
        val updatedCart = addProduct(cart, product)
        cartRepo.updateCart(updatedCart)
        return true
    }
}

fun addProduct(cart: ShoppingCart, product: Product): ShoppingCart {
    val mutableProducts = cart.products.toMutableMap()
    mutableProducts[product] = mutableProducts.getOrDefault(product, 0) + 1
    return cart.copy(
        products = mutableProducts,
    )
}
