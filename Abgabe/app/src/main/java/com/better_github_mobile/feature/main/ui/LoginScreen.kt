package com.better_github_mobile.feature.main.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.TextField
import androidx.compose.material.*
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.better_github_mobile.feature.main.navigation.BottomNavigationItem.Cart
import com.better_github_mobile.feature.main.navigation.BottomNavigationItem.Products
import com.better_github_mobile.feature.main.navigation.MainBottomNavigation
import com.better_github_mobile.feature.main.navigation.MainNavigationGraph


@Composable
fun LoginScreen() {
    var text by rememberSaveable { mutableStateOf("") }

    TextField(
        value = text,
        onValueChange = { text = it }
    )
}

@Composable
@Preview
fun LoginScreen_Preview() {
    LoginScreen()
}
