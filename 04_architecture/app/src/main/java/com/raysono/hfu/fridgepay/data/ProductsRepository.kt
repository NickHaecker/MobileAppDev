package com.raysono.hfu.fridgepay.data

import com.raysono.hfu.fridgepay.R
import com.raysono.hfu.fridgepay.domain.Product

val productRepo = ProductsRepository()

class ProductsRepository {

    private val allProducts = listOf(
        Product(
            id = "spezi",
            name = "Spezi 0.5L",
            icon = R.drawable.spezi,
            description = "Yummy halber Liter Spezi",
            price = 0.8,
        ),
        Product(
            id = "mate",
            name = "Club Mate 0.5L",
            icon = R.drawable.clubmate,
            description = "",
            price = 1.2,
        ),
        Product(
            id = "balisto-purple",
            name = "Lila Balisto",
            icon = R.drawable.balisto_purple,
            description = "Zweimal lecker Balisto. Nom nom nom",
            price = 0.6,
        ),
    )

    fun getAllProducts() = allProducts

    fun getProductById(id: String): Product? = allProducts.firstOrNull {
        it.id == id
    }
}
