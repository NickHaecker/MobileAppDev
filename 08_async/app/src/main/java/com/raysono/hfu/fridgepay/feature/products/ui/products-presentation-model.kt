package com.raysono.hfu.fridgepay.feature.products.ui

import androidx.annotation.DrawableRes
import com.raysono.hfu.fridgepay.domain.model.ProductId

class ProductUI(
    val id: ProductId,
    val name: String,
    val description: String,
    @DrawableRes val icon: Int,
)
