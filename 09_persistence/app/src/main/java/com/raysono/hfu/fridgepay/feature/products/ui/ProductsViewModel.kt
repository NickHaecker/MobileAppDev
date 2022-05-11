package com.raysono.hfu.fridgepay.feature.products.ui

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.raysono.hfu.fridgepay.domain.BuyProductUseCase
import com.raysono.hfu.fridgepay.domain.GetProductsUseCase
import com.raysono.hfu.fridgepay.domain.model.ProductId
import com.raysono.hfu.fridgepay.feature.getProductIconResource
import kotlinx.coroutines.launch

class ProductsViewModel : ViewModel() {
    fun bindUi(context: Context): LiveData<List<ProductUI>> = liveData {
        val result = GetProductsUseCase()().map { buyableProduct ->
            ProductUI(
                id = buyableProduct.product.id,
                name = buyableProduct.product.name,
                description = buyableProduct.product.description,
                icon = getProductIconResource(buyableProduct, context)
            )
        }.sortedBy { it.name }
        emit(result)
    }

    fun onAddProduct(productId: ProductId) {
        viewModelScope.launch {
            BuyProductUseCase()(productId)
        }
    }
}
