package com.example.bettergithubmobile.domain

class LoginUseCase(
    private val webService: WebService,
    private val userSettingsRepository: UserSettingsRepository
){
    suspend operator fun invoke(
        username: String,
        password: String,
    ) = withContext(Dispatchers.Default){

    }
}