package com.raysono.hfu.fridgepay.domain

import com.raysono.hfu.fridgepay.data.productRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetProductsUseCase {
    suspend operator fun invoke() = withContext(Dispatchers.Default) { productRepo.getAllBuyableProduct() }
}
