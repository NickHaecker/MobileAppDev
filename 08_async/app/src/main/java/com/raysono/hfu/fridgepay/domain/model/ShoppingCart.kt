package com.raysono.hfu.fridgepay.domain.model

import kotlin.math.max

@JvmInline
value class ShoppingCartId(val value: String)

sealed class ShoppingCart {
    abstract val id: ShoppingCartId

    class Empty(override val id: ShoppingCartId) : ShoppingCart()
    class Unpayed private constructor(
        override val id: ShoppingCartId,
        val products: Map<BuyableProduct, Int>,
        val totalCost: Double,
    ) : ShoppingCart() {
        companion object {
            fun create(
                id: ShoppingCartId,
                products: Map<BuyableProduct, Int>,
                totalCost: Double,
            ): ShoppingCart = Unpayed(
                id,
                products,
                max(0.0, totalCost),
            )
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Unpayed

            if (id != other.id) return false

            return true
        }

        override fun hashCode(): Int {
            return id.hashCode()
        }
    }
}

fun addProduct(cart: ShoppingCart, product: BuyableProduct): ShoppingCart {
    return when (cart) {
        is ShoppingCart.Empty -> ShoppingCart.Unpayed.create(
            cart.id, mapOf(product to 1), product.price,
        )
        is ShoppingCart.Unpayed -> {
            val mutableProducts = cart.products.toMutableMap()
            mutableProducts[product] = mutableProducts.getOrDefault(product, 0) + 1
            val totalCost = mutableProducts.entries.sumOf { (product, count) -> product.price * count }
            return ShoppingCart.Unpayed.create(
                cart.id,
                mutableProducts,
                totalCost,
            )
        }
    }
}

fun removeAllProducts(cart: ShoppingCart): ShoppingCart = ShoppingCart.Empty(cart.id)
