package com.raysono.hfu.fridgepay.feature.products.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ProductsScreen() {
    val scrollState = rememberLazyListState()
    LazyColumn(state = scrollState) {
        items(42) {
            ProductItem()
        }
    }
}

@Preview
@Composable
fun ProductsScreen_Preview() {
    ProductsScreen()
}
