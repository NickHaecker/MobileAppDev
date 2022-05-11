package com.raysono.hfu.fridgepay.domain

import com.raysono.hfu.fridgepay.data.UserSettingsRepository
import com.raysono.hfu.fridgepay.data.network.WebService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SignUpUseCase(
    private val webService: WebService,
    private val userSettingsRepository: UserSettingsRepository,
    private val downloadProductsUseCase: DownloadProductsUseCase,
) {
    suspend operator fun invoke(username: String, password: String) = withContext(Dispatchers.Default) {
        // TODO
        //  store credentials (state == logging-in)
        //  send username/password to backend
        //  store cart ID from response
        //  download products
        //  set login-state to logged-in
    }
}
