package com.raysono.hfu.fridgepay.domain

import com.raysono.hfu.fridgepay.data.cartRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn

class ObserveShoppingCartUseCase {
    operator fun invoke() = cartRepo.observeCurrentCart().flowOn(Dispatchers.Default)
}
