package com.raysono.hfu.fridgepay.feature.products.ui

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.raysono.hfu.fridgepay.domain.BuyProductUseCase
import com.raysono.hfu.fridgepay.domain.GetProductsUseCase
import com.raysono.hfu.fridgepay.domain.model.ProductId
import com.raysono.hfu.fridgepay.feature.getProductIconResource

class ProductsViewModel : ViewModel() {
    fun bindUi(context: Context): LiveData<List<ProductUI>> {
        val state = MutableLiveData(
            GetProductsUseCase()().map { buyableProduct ->
                ProductUI(
                    id = buyableProduct.product.id,
                    name = buyableProduct.product.name,
                    description = buyableProduct.product.description,
                    icon = getProductIconResource(buyableProduct, context)
                )
            }.sortedBy { it.name }
        )
        return state
    }

    fun onAddProduct(productId: ProductId) {
        BuyProductUseCase()(productId)
    }
}
