package com.raysono.hfu.fridgepay.domain

import com.raysono.hfu.fridgepay.data.productRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn

class ObserveProductsUseCase {
    operator fun invoke() = productRepo.observeAllBuyableProduct().flowOn(Dispatchers.Default)
}
