package com.raysono.hfu.fridgepay.feature.products.ui

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.raysono.hfu.fridgepay.App
import com.raysono.hfu.fridgepay.data.network.WebService
import com.raysono.hfu.fridgepay.domain.BuyProductUseCase
import com.raysono.hfu.fridgepay.domain.ObserveProductsUseCase
import com.raysono.hfu.fridgepay.domain.model.ProductIcon
import com.raysono.hfu.fridgepay.domain.model.ProductId
import com.raysono.hfu.fridgepay.feature.getProductIconResource
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class ProductsViewModel : ViewModel() {
    fun bindUi(context: Context): LiveData<List<ProductUI>> =
        ObserveProductsUseCase()().map {
            it.map { buyableProduct ->
                ProductUI(
                    id = buyableProduct.product.id,
                    name = buyableProduct.product.name,
                    description = buyableProduct.product.description,
                    icon = (buyableProduct.product.icon as? ProductIcon.Local)?.let { getProductIconResource(buyableProduct, context) },
                    iconUrl = (buyableProduct.product.icon as? ProductIcon.Remote)?.let { "${WebService.BASE_URL}${it.url}" },
                )
            }.sortedBy { it.name }
        }.asLiveData()

    fun onAddProduct(productId: ProductId) {
        viewModelScope.launch {
            BuyProductUseCase(
                App.webService,
                App.userSettingsRepo,
            )(productId)
        }
    }
}
