package com.raysono.hfu.fridgepay.feature.main.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.raysono.hfu.fridgepay.App
import kotlinx.coroutines.flow.map

class AppViewModel : ViewModel() {

    fun isLoggedIn() = App.userSettingsRepo
        .observeSettings()
        .map { it.loginState }
        .asLiveData()
}
