package com.raysono.hfu.fridgepay.domain

import com.raysono.hfu.fridgepay.data.cartRepo

class ObserveShoppingCartUseCase {
    operator fun invoke() = cartRepo.observeCurrentCart()
}
