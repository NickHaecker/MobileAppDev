package com.raysono.hfu.fridgepay.feature.products.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.raysono.hfu.fridgepay.R
import com.raysono.hfu.fridgepay.domain.Product
import com.raysono.hfu.fridgepay.domain.allProducts

@Composable
fun ProductItem(product: Product, buyProduct: (id: String) -> Unit) {
    Card(
        elevation = 3.dp,
        modifier = Modifier.padding(8.dp),
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Image(
                painter = painterResource(product.icon),
                contentDescription = product.name,
                modifier = Modifier
                    .size(50.dp)
                    .padding(end = 8.dp),
            )
            Column(
                modifier = Modifier.weight(1f),
            ) {
                Text(
                    text = product.name,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    Text(
                        text = product.description,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.subtitle1,
                    )
                }
            }
            Box(
                modifier = Modifier.align(Alignment.CenterVertically),
            ) {
                IconButton(
                    onClick = { buyProduct(product.id) },
                ) {
                    Icon(painterResource(R.drawable.ic_baseline_add_shopping_cart_24), contentDescription = "add product")
                }
            }
        }
    }
}

@Preview
@Composable
fun ProductItem_Preview() {
    ProductItem(allProducts.first()) {}
}
