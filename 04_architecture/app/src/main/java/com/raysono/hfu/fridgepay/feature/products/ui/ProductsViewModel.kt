package com.raysono.hfu.fridgepay.feature.products.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.raysono.hfu.fridgepay.domain.BuyProductUseCase
import com.raysono.hfu.fridgepay.domain.GetProductsUseCase
import com.raysono.hfu.fridgepay.domain.Product

class ProductsViewModel : ViewModel() {
    fun bindUi(): LiveData<List<Product>> {
        val state = MutableLiveData(
            GetProductsUseCase()()
        )
        return state
    }

    fun onAddProduct(productId: String) {
        BuyProductUseCase()(productId)
    }
}
