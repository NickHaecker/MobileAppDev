package com.raysono.hfu.fridgepay.feature.main.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.raysono.hfu.fridgepay.domain.ObserveShoppingCartUseCase
import com.raysono.hfu.fridgepay.domain.model.ShoppingCart
import kotlinx.coroutines.flow.map

class MainViewModel : ViewModel() {
    fun bindUi(): LiveData<Int> = ObserveShoppingCartUseCase()()
        .map { cart ->
            when (cart) {
                is ShoppingCart.Empty -> 0
                is ShoppingCart.Unpayed -> cart.products.entries.sumOf { it.value }
            }
        }
        .asLiveData()
}
