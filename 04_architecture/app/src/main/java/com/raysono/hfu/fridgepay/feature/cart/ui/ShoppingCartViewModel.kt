package com.raysono.hfu.fridgepay.feature.cart.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.raysono.hfu.fridgepay.domain.ObserveShoppingCartUseCase
import com.raysono.hfu.fridgepay.domain.PayShoppingCartUseCase
import com.raysono.hfu.fridgepay.domain.ShoppingCart

class ShoppingCartViewModel : ViewModel() {

    fun bindUI(): LiveData<ShoppingCart> {
        return ObserveShoppingCartUseCase()()
    }

    fun onPayCart() {
        PayShoppingCartUseCase()()
    }
}
