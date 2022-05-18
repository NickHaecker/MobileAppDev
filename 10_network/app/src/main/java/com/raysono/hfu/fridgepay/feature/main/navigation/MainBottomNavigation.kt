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

@Composable
fun MainBottomNavigation(navController: NavController, totalCount: Int) {
    BottomNavigation {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        listOf(
            BottomNavigationItem.Products,
            BottomNavigationItem.Cart,
            BottomNavigationItem.Profile,
        ).forEach { navItem ->
            BottomNavigationItem(
                selected = currentRoute == navItem.routeName,
                icon = {
                    if (navItem == BottomNavigationItem.Cart && totalCount > 0) {
                        BadgedBox(
                            badge = {
                                Badge {
                                    Text("$totalCount")
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
