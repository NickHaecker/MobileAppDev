package com.raysono.hfu.fridgepay.feature.main.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.raysono.hfu.fridgepay.feature.main.navigation.BottomNavigationItem.Cart
import com.raysono.hfu.fridgepay.feature.main.navigation.BottomNavigationItem.Products
import com.raysono.hfu.fridgepay.feature.main.navigation.MainBottomNavigation
import com.raysono.hfu.fridgepay.feature.main.navigation.MainNavigationGraph

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route
            TopAppBar(
                title = {
                    when (currentRoute) {
                        Products.routeName -> Text(stringResource(Products.title))
                        Cart.routeName -> Text(stringResource(Cart.title))
                    }
                },
            )
        },
        bottomBar = { MainBottomNavigation(navController) }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            MainNavigationGraph(navController)
        }
    }
}

@Composable
@Preview
fun MainScreen_Preview() {
    MainScreen()
}
