package com.raysono.hfu.fridgepay.feature.main.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.raysono.hfu.fridgepay.domain.ObserveShoppingCartUseCase
import com.raysono.hfu.fridgepay.domain.model.ShoppingCart

class MainViewModel : ViewModel() {
    fun bindUi(): LiveData<Int> {
        val observableCart: LiveData<ShoppingCart> = ObserveShoppingCartUseCase()()
        return Transformations.map(observableCart) { cart ->
            when (cart) {
                is ShoppingCart.Empty -> 0
                is ShoppingCart.Unpayed -> cart.products.entries.sumOf { it.value }
            }
        }
    }
}
