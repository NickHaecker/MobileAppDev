package com.raysono.hfu.fridgepay.feature.profile.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raysono.hfu.fridgepay.App
import com.raysono.hfu.fridgepay.data.cartRepo
import com.raysono.hfu.fridgepay.data.productRepo
import com.raysono.hfu.fridgepay.domain.LogoutUseCase
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {

    fun onLogout() {
        viewModelScope.launch {
            LogoutUseCase(
                App.userSettingsRepo,
                productRepo,
                cartRepo,
            )()
        }
    }
}
