package com.raysono.hfu.fridgepay.data.database

import com.raysono.hfu.fridgepay.domain.model.ShoppingCart
import com.raysono.hfu.fridgepay.domain.model.ShoppingCartId

fun shoppingCartFromDb(id: ShoppingCartId, productsAndAmount: List<ProductAndAmount>): ShoppingCart {
    return if (productsAndAmount.isEmpty()) {
        ShoppingCart.Empty(id)
    } else {

        val buyableProducts = productsAndAmount.mapNotNull {
            buyableProductFromDb(it.product)
        }

        val products = buyableProducts.associateWith { buyableProduct ->
            productsAndAmount.firstOrNull { it.product.id == buyableProduct.product.id.value }?.cartProduct?.amount ?: 0
        }

        ShoppingCart.Unpayed.create(
            id = id,
            products = products,
            totalCost = products.entries.sumOf { it.key.price * it.value },
        )
    }
}
