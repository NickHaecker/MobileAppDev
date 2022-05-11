package com.raysono.hfu.fridgepay.domain

import com.raysono.hfu.fridgepay.data.ProductsRepository
import com.raysono.hfu.fridgepay.data.productRepo
import com.raysono.hfu.fridgepay.domain.model.BuyableProduct
import com.raysono.hfu.fridgepay.domain.model.Product
import com.raysono.hfu.fridgepay.domain.model.ProductIcon
import com.raysono.hfu.fridgepay.domain.model.ProductId
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AddDemoProductsUseCase(
    private val productsRepository: ProductsRepository,
) {
    suspend operator fun invoke() = withContext(Dispatchers.Default) {
        if (productsRepository.getAllProducts().isNotEmpty()) return@withContext

        listOfNotNull(
            Product.create(
                id = ProductId("a59c0e7b-3a58-4859-934d-1a0393831637"),
                name = "Spezi 0.5L",
                icon = ProductIcon.Local("spezi"),
                description = "Yummy halber Liter Spezi",
            )?.let { BuyableProduct(it, 0.8) },
            Product.create(
                id = ProductId("867e5af2-aa53-4e46-9cfd-a1bc9b2929cb"),
                name = "Club Mate 0.5L",
                icon = ProductIcon.Local("clubmate"),
                description = "",
            )?.let { BuyableProduct(it, 1.8) },
            Product.create(
                id = ProductId("f16cdf15-6528-4a0b-993c-24d5bf8007a7"),
                name = "Lila Balisto",
                icon = ProductIcon.Local("balisto_purple"),
                description = "Zweimal lecker Balisto. Nom nom nom",
            )?.let { BuyableProduct(it, 0.9) },
        ).forEach {
            productsRepository.addBuyableProduct(it)
        }
    }
}
