package com.raysono.hfu.fridgepay.domain

import com.raysono.hfu.fridgepay.data.ProductsRepository
import com.raysono.hfu.fridgepay.data.network.WebService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DownloadProductsUseCase(
    private val webService: WebService,
    private val productsRepository: ProductsRepository,
) {

    suspend operator fun invoke() = withContext(Dispatchers.Default) {
        kotlin.runCatching {
            // TODO download products
        }
    }
}
