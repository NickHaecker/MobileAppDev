package com.raysono.hfu.fridgepay.feature.main.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.raysono.hfu.fridgepay.feature.cart.ui.ShoppingCartScreen
import com.raysono.hfu.fridgepay.feature.main.model.AppState
import com.raysono.hfu.fridgepay.feature.products.ui.ProductsScreen

@Composable
fun MainNavigationGraph(state: AppState, navController: NavHostController) {
    NavHost(navController, startDestination = "products") {
        composable(BottomNavigationItem.Products.routeName) {
            ProductsScreen(state.products)
        }
        composable(BottomNavigationItem.Cart.routeName) {
            ShoppingCartScreen(state.cart)
        }
    }
}
