package com.raysono.hfu.fridgepay.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.ZonedDateTime

@Entity(tableName = "product")
data class ProductDb(
    @PrimaryKey
    val id: String,
    val name: String,
    val iconName: String?,
    val iconUrl: String?,
    val description: String,
    val created: ZonedDateTime,
    val updated: ZonedDateTime,
    val deleted: ZonedDateTime,
    val price: Double?,
)
