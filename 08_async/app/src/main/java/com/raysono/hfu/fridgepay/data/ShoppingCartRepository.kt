package com.raysono.hfu.fridgepay.data

import com.raysono.hfu.fridgepay.domain.model.ShoppingCart
import com.raysono.hfu.fridgepay.domain.model.ShoppingCartId
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

val cartRepo = ShoppingCartRepository()

class ShoppingCartRepository {

    private val currentCart = MutableStateFlow(
        BoxedShoppingCart(
            ShoppingCart.Empty(ShoppingCartId("dfb2c57d-8ff7-4e44-a35d-54ec169dd54a"))
        )
    )

    suspend fun getCurrentCart(): ShoppingCart = currentCart.value.cart

    fun observeCurrentCart(): Flow<ShoppingCart> = currentCart.map { it.cart }

    suspend fun updateCart(newCart: ShoppingCart): ShoppingCart {
        currentCart.value = BoxedShoppingCart(newCart)
        return getCurrentCart()
    }
}

private class BoxedShoppingCart(val cart: ShoppingCart) {
    override fun equals(other: Any?): Boolean = false

    override fun hashCode(): Int {
        return cart.hashCode()
    }
}
