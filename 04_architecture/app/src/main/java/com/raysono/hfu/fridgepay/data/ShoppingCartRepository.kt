package com.raysono.hfu.fridgepay.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.raysono.hfu.fridgepay.domain.ShoppingCart

val cartRepo = ShoppingCartRepository()

class ShoppingCartRepository {

    private var currentCart = ShoppingCart(
        products = emptyMap(),
    )

    private val _currentCart = MutableLiveData(currentCart)

    fun getCurrentCart(): ShoppingCart = currentCart

    fun observeCurrentCart(): LiveData<ShoppingCart> = _currentCart

    fun updateCart(newCart: ShoppingCart): ShoppingCart {
        currentCart = newCart
        _currentCart.value = newCart
        return getCurrentCart()
    }
}
