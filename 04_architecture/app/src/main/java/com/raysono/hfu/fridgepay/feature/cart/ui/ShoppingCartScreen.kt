package com.raysono.hfu.fridgepay.feature.cart.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.raysono.hfu.fridgepay.R
import com.raysono.hfu.fridgepay.domain.ShoppingCart
import com.raysono.hfu.fridgepay.domain.allProducts
import java.text.NumberFormat

@Composable
fun ShoppingCartScreen(viewModel: ShoppingCartViewModel = viewModel()) {
    val cart by viewModel.bindUI().observeAsState(
        ShoppingCart(emptyMap())
    )
    ShoppingCartUI(cart, viewModel::onPayCart)
}

@Composable
private fun ShoppingCartUI(cart: ShoppingCart, payCart: () -> Unit) {
    val scrollState = rememberLazyListState()
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth(),
    ) {
        LazyColumn(
            state = scrollState,
            modifier = Modifier.weight(1f),
        ) {
            items(cart.products.entries.sortedBy { it.key.name }) { (product, count) ->
                ShoppingCartItem(product, count)
            }
        }
        Divider()
        val totalCost = cart.products.entries.sumOf { (product, count) -> product.price * count }
        Button(
            modifier = Modifier.padding(16.dp),
            enabled = totalCost > 0,
            onClick = { payCart() },
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        painterResource(R.drawable.ic_shopping_cart_checkout_white_24dp),
                        contentDescription = stringResource(R.string.cart_cta_pay),
                        modifier = Modifier.padding(end = 8.dp),
                    )
                    Text(stringResource(R.string.cart_cta_pay))
                }
                Text(
                    NumberFormat.getCurrencyInstance().format(totalCost),
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
}

@Preview
@Composable
fun ShoppingCartScreen_Preview() {
    ShoppingCartUI(
        ShoppingCart(
            products = mapOf(allProducts.first() to 42)
        )
    ) {}
}
