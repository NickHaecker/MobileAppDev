package com.raysono.hfu.fridgepay.feature.cart.ui

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.raysono.hfu.fridgepay.App
import com.raysono.hfu.fridgepay.data.network.WebService
import com.raysono.hfu.fridgepay.domain.ObserveShoppingCartUseCase
import com.raysono.hfu.fridgepay.domain.PayShoppingCartUseCase
import com.raysono.hfu.fridgepay.domain.model.ProductIcon
import com.raysono.hfu.fridgepay.domain.model.ShoppingCart
import com.raysono.hfu.fridgepay.feature.getProductIconResource
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class ShoppingCartViewModel : ViewModel() {

    fun bindUI(context: Context): LiveData<ShoppingCartScreenUI> = ObserveShoppingCartUseCase()().map { cart ->
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
                        icon = (buyableProduct.product.icon as? ProductIcon.Local)?.let { getProductIconResource(buyableProduct, context) },
                        iconUrl = (buyableProduct.product.icon as? ProductIcon.Remote)?.let { "${WebService.BASE_URL}${it.url}" },
                    )
                }.sortedBy { it.name },
                totalCost = cart.totalCost,
            )
        }
    }.asLiveData()

    fun onPayCart() {
        viewModelScope.launch {
            PayShoppingCartUseCase(
                App.webService,
                App.userSettingsRepo,
            )()
        }
    }
}
