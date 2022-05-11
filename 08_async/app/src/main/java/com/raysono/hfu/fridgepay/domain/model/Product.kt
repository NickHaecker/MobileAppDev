package com.raysono.hfu.fridgepay.domain.model

import java.time.ZonedDateTime
import kotlin.math.max

@JvmInline
value class ProductId(val value: String)

sealed class ProductIcon {
    object Unknown : ProductIcon()
    class Local(val name: String) : ProductIcon()
    class Remote(val url: String) : ProductIcon()
}

class Product private constructor(
    val id: ProductId,
    val name: String,
    val icon: ProductIcon,
    val description: String,
    val created: ZonedDateTime,
    val updated: ZonedDateTime,
    val deleted: ZonedDateTime,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Product

        if (id != other.id) return false

        return true
    }
    override fun hashCode(): Int {
        return id.hashCode()
    }

    companion object {
        fun create(
            id: ProductId,
            name: String,
            icon: ProductIcon,
            description: String,
        ): Product? {
            if (name.isBlank()) return null
            val now = ZonedDateTime.now()
            return Product(id, name, icon, description, now, now, now)
        }
    }
}

data class BuyableProduct internal constructor(
    val product: Product,
    val price: Double,
) {
    companion object {
        fun create(
            product: Product,
            price: Double,
        ): BuyableProduct = BuyableProduct(product, max(0.0, price))
    }
}
