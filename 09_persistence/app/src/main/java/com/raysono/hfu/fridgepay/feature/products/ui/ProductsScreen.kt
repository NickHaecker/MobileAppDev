package com.raysono.hfu.fridgepay.feature.products.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.raysono.hfu.fridgepay.domain.model.ProductId

@Composable
fun ProductsScreen(viewModel: ProductsViewModel = viewModel()) {
    val products by viewModel.bindUi(LocalContext.current).observeAsState(emptyList())
    ProductsScreenUI(products, viewModel::onAddProduct)
}

@Composable
private fun ProductsScreenUI(products: List<ProductUI>, buyProduct: (ProductId) -> Unit) {
    val scrollState = rememberLazyListState()
    LazyColumn(state = scrollState) {
        items(products) { product ->
            ProductItem(product, buyProduct)
        }
    }
}

@Preview
@Composable
fun ProductsScreen_Preview() {
    ProductsScreenUI(emptyList()) {}
}
