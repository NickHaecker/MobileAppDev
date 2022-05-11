package com.raysono.hfu.fridgepay.feature.cart.ui

import androidx.annotation.DrawableRes
import com.raysono.hfu.fridgepay.domain.model.ProductId

class ShoppingCartScreenUI(
    val products: List<ShoppingCartScreenProductUI>,
    val totalCost: Double,
)

class ShoppingCartScreenProductUI(
    val id: ProductId,
    val name: String,
    val description: String,
    val amount: Int,
    val totalCost: Double,
    @DrawableRes val icon: Int?,
    val iconUrl: String?,
)
