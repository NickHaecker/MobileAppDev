package com.raysono.hfu.fridgepay.feature.products.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.raysono.hfu.fridgepay.domain.Product
import com.raysono.hfu.fridgepay.domain.allProducts

@Composable
fun ProductsScreen(products: List<Product>) {
    val scrollState = rememberLazyListState()
    LazyColumn(state = scrollState) {
        items(products.sortedBy { it.name }) { product ->
            ProductItem(product)
        }
    }
}

@Preview
@Composable
fun ProductsScreen_Preview() {
    ProductsScreen(allProducts)
}
