package com.raysono.hfu.fridgepay.feature.main.navigation

import com.raysono.hfu.fridgepay.R

sealed class BottomNavigationItem {
    abstract val routeName: String
    abstract val title: Int
    abstract val icon: Int

    object Products : BottomNavigationItem() {
        override val routeName = "products"
        override val title = R.string.products_title_navigation
        override val icon = R.drawable.ic_restaurant_white_24dp
    }

    object Cart : BottomNavigationItem() {
        override val title = R.string.cart_title_navigation
        override val icon = R.drawable.ic_baseline_shopping_cart_24
        override val routeName = "cart"
    }
}
