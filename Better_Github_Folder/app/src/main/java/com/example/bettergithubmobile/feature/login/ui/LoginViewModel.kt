package com.example.bettergithubmobile.feature.login.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bettergithubmobile.domain.LoginUseCase
import kotlinx.coroutines.launch


class LoginViewModel:ViewModel(){
    fun onLogin(username: String, password:String){
        viewModelScope.launch{
            LoginUseCase(
                App.userSettingsRepo
            )(username, password)
        }
    }
}