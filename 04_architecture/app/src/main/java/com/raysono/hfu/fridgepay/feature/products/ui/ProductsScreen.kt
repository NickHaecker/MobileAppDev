package com.raysono.hfu.fridgepay.feature.products.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.raysono.hfu.fridgepay.domain.Product
import com.raysono.hfu.fridgepay.domain.allProducts

@Composable
fun ProductsScreen(viewModel: ProductsViewModel = viewModel()) {
    val products by viewModel.bindUi().observeAsState(emptyList())
    ProductsScreenUI(products, viewModel::onAddProduct)
}

@Composable
private fun ProductsScreenUI(products: List<Product>, buyProduct: (String) -> Unit) {
    val scrollState = rememberLazyListState()
    LazyColumn(state = scrollState) {
        items(products.sortedBy { it.name }) { product ->
            ProductItem(product, buyProduct)
        }
    }
}

@Preview
@Composable
fun ProductsScreen_Preview() {
    ProductsScreenUI(allProducts) {}
}
