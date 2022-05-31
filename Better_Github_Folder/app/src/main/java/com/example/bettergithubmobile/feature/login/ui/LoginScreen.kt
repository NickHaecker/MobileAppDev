package com.example.bettergithubmobile.feature.login.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerIconDefaults.Text
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.KeyboardType.Companion.Text
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.bettergithubmobile.R
import androidx.lifecycle.viewmodel.compose.viewModel
@Composable
fun LoginScreen(viewModel: LoginViewModel = viewModel()){
    LoginScreenUi(viewModel::onLogin)
}
@Composable
private fun LoginScreenUi(
    onLogin:(username:String,password:String) -> Unit
){
    var userName by remember { mutableStateOf("")}
    var password by remember {mutableStateOf("")}
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth(),
    ) {
        OutlinedTextField(
            value = userName,
            onValueChange = {
                val it = ""
                userName = it
            },
            singleLine = true,
            label = {
                Text(stringResource(R.string.login_input_username))
            }
        )
        OutlinedTextField(
            value = password,
            onValueChange = {
                val it = ""
                password = it
            },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            label = {
                Text(stringResource(R.string.login_input_password))
            }
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Button(
                modifier = Modifier.padding(16.dp),
                enabled = userName.isNotBlank() && password.isNotBlank(),
                onClick = { onLogin(userName, password) },
            ) {
                Text(
                    stringResource(R.string.login_button_login),
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
}