package com.example.bettergithubmobile.feature.login.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

class LoginViewModel:ViewModel(){
    fun onLogin(username: String, password:String){
        viewModelScope.launch{

        }
    }
}