package com.raysono.hfu.fridgepay.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.raysono.hfu.fridgepay.domain.model.ShoppingCart
import com.raysono.hfu.fridgepay.domain.model.ShoppingCartId

val cartRepo = ShoppingCartRepository()

class ShoppingCartRepository {

    private var currentCart: ShoppingCart = ShoppingCart.Empty(ShoppingCartId("dfb2c57d-8ff7-4e44-a35d-54ec169dd54a"))

    private val _currentCart = MutableLiveData(currentCart)

    fun getCurrentCart(): ShoppingCart = currentCart

    fun observeCurrentCart(): LiveData<ShoppingCart> = _currentCart

    fun updateCart(newCart: ShoppingCart): ShoppingCart {
        currentCart = newCart
        _currentCart.value = newCart
        return getCurrentCart()
    }
}
