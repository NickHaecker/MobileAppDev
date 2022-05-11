package com.raysono.hfu.fridgepay.data.database

import com.raysono.hfu.fridgepay.domain.model.BuyableProduct
import com.raysono.hfu.fridgepay.domain.model.Product
import com.raysono.hfu.fridgepay.domain.model.ProductIcon
import com.raysono.hfu.fridgepay.domain.model.ProductId

fun productToDb(product: Product): ProductDb = ProductDb(
    id = product.id.value,
    name = product.name,
    iconName = (product.icon as? ProductIcon.Local)?.name,
    iconUrl = (product.icon as? ProductIcon.Remote)?.url,
    description = product.description,
    created = product.created,
    updated = product.updated,
    deleted = product.deleted,
    price = null,
)

fun productFromDb(product: ProductDb): Product? {
    val icon = when {
        product.iconName != null -> ProductIcon.Local(product.iconName)
        product.iconUrl != null -> ProductIcon.Remote(product.iconUrl)
        else -> ProductIcon.Unknown
    }
    return Product.create(
        id = ProductId(product.id),
        name = product.name,
        icon = icon,
        description = product.description,
    )
}

fun buyableProductToDb(buyableProduct: BuyableProduct): ProductDb {
    return productToDb(buyableProduct.product).copy(price = buyableProduct.price)
}

fun buyableProductFromDb(productDb: ProductDb): BuyableProduct? {
    val product = productFromDb(productDb)
    return if (product != null && productDb.price != null) {
        BuyableProduct(product, productDb.price)
    } else {
        null
    }
}
