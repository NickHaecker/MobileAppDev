package com.better_github_mobile.domain

import com.raysono.hfu.fridgepay.R

data class Product(
    val id: String,
    val name: String,
    val icon: Int,
    val description: String,
    val price: Double,
)

val allProducts = listOf(
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
