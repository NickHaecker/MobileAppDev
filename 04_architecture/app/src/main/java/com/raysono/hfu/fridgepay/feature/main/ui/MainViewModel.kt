package com.raysono.hfu.fridgepay.feature.main.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.raysono.hfu.fridgepay.domain.ObserveShoppingCartUseCase
import com.raysono.hfu.fridgepay.domain.ShoppingCart

class MainViewModel : ViewModel() {
    fun bindUi(): LiveData<Int> {
        val cart: LiveData<ShoppingCart> = ObserveShoppingCartUseCase()()
        val totalCountState: LiveData<Int> = Transformations.map(cart) {
            it.products.entries.sumOf { it.value }
        }
        return totalCountState
    }
}
