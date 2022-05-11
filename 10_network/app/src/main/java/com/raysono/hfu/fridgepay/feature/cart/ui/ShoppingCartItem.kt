package com.raysono.hfu.fridgepay.feature.cart.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raysono.hfu.fridgepay.R
import com.raysono.hfu.fridgepay.domain.model.ProductId
import java.text.NumberFormat

@Composable
fun ShoppingCartItem(product: ShoppingCartScreenProductUI) {
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
            if (product.icon != null) {
                // TODO replace with AsyncImage to show product.iconUrl
                Image(
                    painter = painterResource(product.icon),
                    contentDescription = product.name,
                    modifier = Modifier
                        .size(50.dp)
                        .padding(end = 8.dp),
                )
            }
            Column(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .weight(1f),
            ) {
                Text(
                    text = product.name,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    Text(
                        text = LocalContext.current.resources.getQuantityString(R.plurals.cart_item_count, product.amount, product.amount),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.subtitle1,
                    )
                }
            }
            Text(
                text = NumberFormat.getCurrencyInstance().format(product.totalCost),
                fontWeight = FontWeight.Light,
                style = MaterialTheme.typography.body1.copy(fontSize = 18.sp),
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
    }
}

@Preview
@Composable
fun ShoppingCartItem_Preview() {
    ShoppingCartItem(
        ShoppingCartScreenProductUI(
            id = ProductId("foo"),
            name = "spezi",
            description = "description foobar",
            amount = 17,
            totalCost = 42.0,
            icon = R.drawable.clubmate,
            iconUrl = null,
        )
    )
}
