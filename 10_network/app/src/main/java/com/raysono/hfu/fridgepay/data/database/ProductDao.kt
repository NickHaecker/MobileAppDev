package com.raysono.hfu.fridgepay.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Insert
    suspend fun insert(product: ProductDb)

    @Query("SELECT * FROM product")
    suspend fun getAll(): List<ProductDb>

    @Query("SELECT * FROM product")
    fun observeAll(): Flow<List<ProductDb>>

    @Query("SELECT * FROM product WHERE id = :id")
    suspend fun getById(id: String): ProductDb?

    @Query("DELETE FROM product")
    suspend fun deleteAll()
}
