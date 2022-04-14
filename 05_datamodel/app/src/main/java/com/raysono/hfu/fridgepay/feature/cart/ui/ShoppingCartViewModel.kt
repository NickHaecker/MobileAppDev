package com.raysono.hfu.fridgepay.feature.cart.ui

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.raysono.hfu.fridgepay.domain.ObserveShoppingCartUseCase
import com.raysono.hfu.fridgepay.domain.PayShoppingCartUseCase
import com.raysono.hfu.fridgepay.domain.model.ShoppingCart
import com.raysono.hfu.fridgepay.feature.getProductIconResource

class ShoppingCartViewModel : ViewModel() {

    fun bindUI(context: Context): LiveData<ShoppingCartScreenUI> {
        return Transformations.map(ObserveShoppingCartUseCase()()) { cart ->
            when (cart) {
                is ShoppingCart.Empty -> ShoppingCartScreenUI(
                    products = emptyList(),
                    totalCost = 0.0,
                )
                is ShoppingCart.Unpayed -> ShoppingCartScreenUI(
                    products = cart.products.entries.map { (buyableProduct, amount) ->
                        ShoppingCartScreenProductUI(
                            id = buyableProduct.product.id,
                            name = buyableProduct.product.name,
                            description = buyableProduct.product.description,
                            amount = amount,
                            totalCost = buyableProduct.price * amount,
                            icon = getProductIconResource(buyableProduct, context)
                        )
                    }.sortedBy { it.name },
                    totalCost = cart.totalCost,
                )
            }
        }
    }

    fun onPayCart() {
        PayShoppingCartUseCase()()
    }
}
