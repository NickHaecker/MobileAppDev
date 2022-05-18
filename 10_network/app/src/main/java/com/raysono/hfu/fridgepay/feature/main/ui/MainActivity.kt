package com.raysono.hfu.fridgepay.feature.main.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.raysono.hfu.fridgepay.data.LoginState
import com.raysono.hfu.fridgepay.feature.login.ui.LoginScreen
import com.raysono.hfu.fridgepay.ui.theme.FridgePayTheme

class MainActivity : ComponentActivity() {

    private val viewModel: AppViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val loginState by viewModel.isLoggedIn().observeAsState()

            FridgePayTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    if (loginState is LoginState.LoggedIn) {
                        MainScreen()
                    } else if (loginState != null) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxWidth().fillMaxHeight(),
                        ) {
                            LoginScreen()
                            if (loginState is LoginState.LoggingIn) {
                                CircularProgressIndicator()
                            }
                        }
                    }
                }
            }
        }
    }
}
