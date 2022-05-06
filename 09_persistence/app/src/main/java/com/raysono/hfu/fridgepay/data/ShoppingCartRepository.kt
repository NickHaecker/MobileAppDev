package com.raysono.hfu.fridgepay.data

import com.raysono.hfu.fridgepay.App
import com.raysono.hfu.fridgepay.data.database.ShoppingCartDao
import com.raysono.hfu.fridgepay.data.database.ShoppingCartProductDb
import com.raysono.hfu.fridgepay.data.database.shoppingCartFromDb
import com.raysono.hfu.fridgepay.domain.model.ShoppingCart
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val cartRepo = ShoppingCartRepository(App.userSettingsRepo, App.database.shoppingCartDao())

class ShoppingCartRepository(
    private val userSettingsRepository: UserSettingsRepository,
    private val dao: ShoppingCartDao,
) {
    private suspend fun getId() = userSettingsRepository.getSettings().cartId

    suspend fun getCurrentCart(): ShoppingCart = shoppingCartFromDb(getId(), dao.getAll())

    fun observeCurrentCart(): Flow<ShoppingCart> = dao.observeAll().map {
        shoppingCartFromDb(getId(), it)
    }

    suspend fun updateCart(newCart: ShoppingCart): ShoppingCart {
        when (newCart) {
            is ShoppingCart.Empty -> dao.deleteAll()
            is ShoppingCart.Unpayed -> {
                val items = newCart.products.map { (product, amount) ->
                    ShoppingCartProductDb(product.product.id.value, amount)
                }
                dao.upsert(items)
            }
        }
        return newCart
    }
}
