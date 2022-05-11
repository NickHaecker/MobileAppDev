package com.raysono.hfu.fridgepay.domain

import com.raysono.hfu.fridgepay.data.cartRepo

class GetShoppingCartUseCase {

    operator fun invoke(): ShoppingCart {
        return cartRepo.getCurrentCart()
    }
}
