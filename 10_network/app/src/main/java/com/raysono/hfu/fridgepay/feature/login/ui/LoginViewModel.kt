package com.raysono.hfu.fridgepay.feature.login.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raysono.hfu.fridgepay.App
import com.raysono.hfu.fridgepay.data.cartRepo
import com.raysono.hfu.fridgepay.data.productRepo
import com.raysono.hfu.fridgepay.domain.DownloadProductsUseCase
import com.raysono.hfu.fridgepay.domain.DownloadShoppingCartUseCase
import com.raysono.hfu.fridgepay.domain.LoginUseCase
import com.raysono.hfu.fridgepay.domain.SignUpUseCase
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    fun onSignUp(username: String, password: String) {
        viewModelScope.launch {
            SignUpUseCase(
                App.webService,
                App.userSettingsRepo,
                DownloadProductsUseCase(
                    App.webService,
                    productRepo,
                ),
            )(username, password)
        }
    }

    fun onLogin(username: String, password: String) {
        viewModelScope.launch {
            LoginUseCase(
                App.webService,
                App.userSettingsRepo,
                DownloadProductsUseCase(
                    App.webService,
                    productRepo,
                ),
                DownloadShoppingCartUseCase(
                    App.webService,
                    App.userSettingsRepo,
                    productRepo,
                    cartRepo,
                ),
            )(username, password)
        }
    }
}
