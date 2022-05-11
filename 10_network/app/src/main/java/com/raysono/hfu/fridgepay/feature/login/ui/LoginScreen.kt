package com.raysono.hfu.fridgepay.feature.login.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.raysono.hfu.fridgepay.R

@Composable
fun LoginScreen(viewModel: LoginViewModel = viewModel()) {
    LoginScreenUi(viewModel::onLogin, viewModel::onSignUp)
}

@Composable
private fun LoginScreenUi(
    onLogin: (username: String, password: String) -> Unit,
    onSignUp: (username: String, password: String) -> Unit,
) {
    var userName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
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
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(
                    text = stringResource(R.string.login_label_or),
                    style = MaterialTheme.typography.subtitle1,
                )
            }
            Button(
                modifier = Modifier.padding(16.dp),
                enabled = userName.isNotBlank() && password.isNotBlank(),
                onClick = { onSignUp(userName, password) },
            ) {
                Text(
                    stringResource(R.string.login_button_signup),
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
}

@Preview
@Composable
fun LoginScreen_Preview() {
    LoginScreenUi({ _, _ -> }, { _, _ -> })
}
