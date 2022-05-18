package com.raysono.hfu.fridgepay.feature.main.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.raysono.hfu.fridgepay.feature.cart.ui.ShoppingCartScreen
import com.raysono.hfu.fridgepay.feature.products.ui.ProductsScreen
import com.raysono.hfu.fridgepay.feature.profile.ui.ProfileScreen

@Composable
fun MainNavigationGraph(navController: NavHostController) {
    NavHost(navController, startDestination = BottomNavigationItem.Products.routeName) {
        composable(BottomNavigationItem.Products.routeName) {
            ProductsScreen()
        }
        composable(BottomNavigationItem.Cart.routeName) {
            ShoppingCartScreen()
        }
        composable(BottomNavigationItem.Profile.routeName) {
            ProfileScreen()
        }
    }
}
