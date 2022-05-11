package com.better_github_mobile.feature.main.model

import androidx.compose.runtime.mutableStateOf
import com.raysono.hfu.fridgepay.domain.Product
import com.raysono.hfu.fridgepay.domain.ShoppingCart
import com.raysono.hfu.fridgepay.domain.allProducts

data class AppState(
    val products: List<Product>,
    val cart: ShoppingCart,
)

var appState = mutableStateOf(
    AppState(
        products = allProducts,
        cart = ShoppingCart(
            products = emptyMap(),
        )
    )
)

fun addProduct(product: Product) {
    appState.value = appState.value.copy(
        cart = appState.value.cart.copy(
            products = appState.value.cart.products.toMutableMap().apply {
                set(product, appState.value.cart.products.getOrDefault(product, 0) + 1)
            },
        ),
    )
}

fun pay() {
    appState.value = appState.value.copy(
        cart = appState.value.cart.copy(
            products = emptyMap(),
        ),
    )
}
