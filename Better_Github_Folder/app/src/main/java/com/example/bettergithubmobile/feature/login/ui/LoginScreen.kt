package com.example.bettergithubmobile.feature.login.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.OutlinedTextField
import androidx.compose.ui.res.stringResource

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
            onValueChange = { userName = it },
            singleLine = true,
            label = {
                Text(stringResource(R.string.login_input_username))
            }
        )
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
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