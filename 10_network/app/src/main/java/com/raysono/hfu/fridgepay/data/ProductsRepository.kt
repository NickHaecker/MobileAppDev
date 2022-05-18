package com.raysono.hfu.fridgepay.data

import com.raysono.hfu.fridgepay.App
import com.raysono.hfu.fridgepay.data.database.ProductDao
import com.raysono.hfu.fridgepay.data.database.buyableProductFromDb
import com.raysono.hfu.fridgepay.data.database.buyableProductToDb
import com.raysono.hfu.fridgepay.data.database.productFromDb
import com.raysono.hfu.fridgepay.domain.model.BuyableProduct
import com.raysono.hfu.fridgepay.domain.model.Product
import com.raysono.hfu.fridgepay.domain.model.ProductId
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val productRepo = ProductsRepository(App.database.productDao())

class ProductsRepository(
    private val dao: ProductDao,
) {
    suspend fun getAllProducts(): List<Product> = dao.getAll().mapNotNull { productFromDb(it) }

    fun observeAllBuyableProduct(): Flow<List<BuyableProduct>> = dao.observeAll().map { it.mapNotNull(::buyableProductFromDb) }

    suspend fun getProductById(id: ProductId): Product? = dao.getById(id.value)?.let { productFromDb(it) }

    suspend fun getBuyableProductById(id: ProductId): BuyableProduct? = dao.getById(id.value)?.let { buyableProductFromDb(it) }

    suspend fun addBuyableProduct(buyableProduct: BuyableProduct) {
        dao.insert(
            buyableProductToDb(buyableProduct)
        )
    }

    suspend fun deleteAll() {
        dao.deleteAll()
    }
}
