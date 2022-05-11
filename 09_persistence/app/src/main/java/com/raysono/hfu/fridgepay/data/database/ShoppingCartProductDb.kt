package com.raysono.hfu.fridgepay.data.database

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Relation

@Entity(
    tableName = "shopping_cart_product",
    primaryKeys = ["productId"],
    foreignKeys = [
        ForeignKey(
            entity = ProductDb::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("productId"),
            onDelete = ForeignKey.CASCADE,
        )
    ],
)
data class ShoppingCartProductDb(
    val productId: String,
    val amount: Int,
)

data class ProductAndAmount(
    @Embedded
    val cartProduct: ShoppingCartProductDb,
    @Relation(
        parentColumn = "productId",
        entityColumn = "id",
    )
    val product: ProductDb,
)
