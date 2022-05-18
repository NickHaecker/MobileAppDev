package com.raysono.hfu.fridgepay.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
abstract class ShoppingCartDao {

    @Transaction
    open suspend fun upsert(item: ShoppingCartProductDb) {
        val rowId = insert(item)
        if (rowId == -1L) {
            update(item)
        }
    }

    @Transaction
    open suspend fun upsert(items: List<ShoppingCartProductDb>) {
        items.forEach { upsert(it) }
    }

    @Update
    abstract suspend fun update(item: ShoppingCartProductDb)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun insert(item: ShoppingCartProductDb): Long

    @Query("DELETE FROM shopping_cart_product")
    abstract suspend fun deleteAll()

    @Transaction
    @Query("SELECT * FROM shopping_cart_product")
    abstract suspend fun getAll(): List<ProductAndAmount>

    @Transaction
    @Query("SELECT * FROM shopping_cart_product")
    abstract fun observeAll(): Flow<List<ProductAndAmount>>
}
