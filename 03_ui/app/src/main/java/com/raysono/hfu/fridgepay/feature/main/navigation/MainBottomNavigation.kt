package com.raysono.hfu.fridgepay.feature.main.navigation

import androidx.compose.material.Badge
import androidx.compose.material.BadgedBox
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.raysono.hfu.fridgepay.domain.ShoppingCart

@Composable
fun MainBottomNavigation(navController: NavController, cart: ShoppingCart) {
    BottomNavigation {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        listOf(
            BottomNavigationItem.Products,
            BottomNavigationItem.Cart,
        ).forEach { navItem ->
            BottomNavigationItem(
                selected = currentRoute == navItem.routeName,
                icon = {
                    if (navItem == BottomNavigationItem.Cart && cart.products.isNotEmpty()) {
                        BadgedBox(
                            badge = {
                                Badge {
                                    Text("${cart.products.entries.sumOf { it.value }}")
                                }
                            }
                        ) {
                            Icon(painter = painterResource(navItem.icon), contentDescription = navItem.routeName)
                        }
                    } else {
                        Icon(painter = painterResource(navItem.icon), contentDescription = navItem.routeName)
                    }
                },
                onClick = {
                    navController.navigate(navItem.routeName) {
                        navController.graph.startDestinationRoute?.let { screen_route ->
                            popUpTo(screen_route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
            )
        }
    }
}
