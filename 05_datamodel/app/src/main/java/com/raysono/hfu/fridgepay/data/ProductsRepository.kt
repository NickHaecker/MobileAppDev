package com.raysono.hfu.fridgepay.data

import com.raysono.hfu.fridgepay.domain.model.BuyableProduct
import com.raysono.hfu.fridgepay.domain.model.Product
import com.raysono.hfu.fridgepay.domain.model.ProductIcon
import com.raysono.hfu.fridgepay.domain.model.ProductId

val productRepo = ProductsRepository()

class ProductsRepository {

    private val allProducts = listOf(
        Product.create(
            id = ProductId("a59c0e7b-3a58-4859-934d-1a0393831637"),
            name = "Spezi 0.5L",
            icon = ProductIcon.Local("spezi"),
            description = "Yummy halber Liter Spezi",
        ),
        Product.create(
            id = ProductId("867e5af2-aa53-4e46-9cfd-a1bc9b2929cb"),
            name = "Club Mate 0.5L",
            icon = ProductIcon.Local("clubmate"),
            description = "",
        ),
        Product.create(
            id = ProductId("f16cdf15-6528-4a0b-993c-24d5bf8007a7"),
            name = "Lila Balisto",
            icon = ProductIcon.Local("balisto_purple"),
            description = "Zweimal lecker Balisto. Nom nom nom",
        ),
    ).filterNotNull()

    fun getAllProducts() = allProducts

    fun getAllBuyableProduct(): List<BuyableProduct> = listOf(
        BuyableProduct(allProducts[0], 0.8),
        BuyableProduct(allProducts[1], 1.2),
        BuyableProduct(allProducts[2], 0.6),
    )

    fun getProductById(id: ProductId): Product? = allProducts.firstOrNull {
        it.id == id
    }

    fun getBuyableProductById(id: ProductId): BuyableProduct? = getAllBuyableProduct().firstOrNull {
        it.product.id == id
    }
}
