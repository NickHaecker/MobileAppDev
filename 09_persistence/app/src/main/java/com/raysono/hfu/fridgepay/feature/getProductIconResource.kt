package com.raysono.hfu.fridgepay.feature

import android.content.Context
import com.raysono.hfu.fridgepay.domain.model.BuyableProduct
import com.raysono.hfu.fridgepay.domain.model.ProductIcon

fun getProductIconResource(buyableProduct: BuyableProduct, context: Context) = when (val icon = buyableProduct.product.icon) {
    is ProductIcon.Local -> context.resources.getIdentifier(icon.name, "drawable", context.packageName)
    is ProductIcon.Remote,
    ProductIcon.Unknown -> 0
}
