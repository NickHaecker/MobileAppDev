package com.better_github_mobile.feature.main.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.raysono.hfu.fridgepay.feature.cart.ui.ShoppingCartScreen
import com.raysono.hfu.fridgepay.feature.products.ui.ProductsScreen

@Composable
fun MainNavigationGraph(navController: NavHostController) {
    NavHost(navController, startDestination = "products") {
        composable(BottomNavigationItem.Products.routeName) {
            ProductsScreen()
        }
        composable(BottomNavigationItem.Cart.routeName) {
            ShoppingCartScreen()
        }
    }
}
