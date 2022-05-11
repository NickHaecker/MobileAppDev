package com.raysono.hfu.fridgepay.domain

import com.raysono.hfu.fridgepay.data.productRepo

class GetProductsUseCase {
    operator fun invoke() = productRepo.getAllBuyableProduct()
}
