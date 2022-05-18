package com.raysono.hfu.fridgepay.domain

import com.raysono.hfu.fridgepay.data.ProductsRepository
import com.raysono.hfu.fridgepay.data.network.WebService
import com.raysono.hfu.fridgepay.domain.model.BuyableProduct
import com.raysono.hfu.fridgepay.domain.model.Product
import com.raysono.hfu.fridgepay.domain.model.ProductIcon
import com.raysono.hfu.fridgepay.domain.model.ProductId
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DownloadProductsUseCase(
    private val webService: WebService,
    private val productsRepository: ProductsRepository,
) {

    suspend operator fun invoke() = withContext(Dispatchers.Default) {
        kotlin.runCatching {
            webService.getProducts().mapNotNull {
                Product.create(
                    ProductId(it.id),
                    it.name,
                    icon = ProductIcon.Remote(it.imageUrl),
                    description = it.description,
                )?.let { product ->
                    BuyableProduct.create(product, it.price)
                }
            }.forEach {
                productsRepository.addBuyableProduct(it)
            }
        }
    }
}
