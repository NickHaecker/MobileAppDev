package com.raysono.hfu.fridgepay

import android.app.Application
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.Room
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.raysono.hfu.fridgepay.data.LoginState
import com.raysono.hfu.fridgepay.data.UserSettingsRepository
import com.raysono.hfu.fridgepay.data.database.AppDatabase
import com.raysono.hfu.fridgepay.data.network.WebService
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

/**
 * Main entry point into the application process.
 * Registered in the AndroidManifest.xml file.
 */
class App : Application() {

    private val userSettingsStore: DataStore<Preferences> by preferencesDataStore(name = "userSettings")

    override fun onCreate() {
        super.onCreate()
        userSettingsRepo = UserSettingsRepository(userSettingsStore)
        database = Room
            .databaseBuilder(this, AppDatabase::class.java, "app")
            .apply {
                if (BuildConfig.DEBUG) fallbackToDestructiveMigration()
            }
            .build()

        webService = Retrofit.Builder()
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(
                        HttpLoggingInterceptor {
                            Log.d("OKHTTP", it)
                        }.apply { level = HttpLoggingInterceptor.Level.BODY }
                    )
                    .addInterceptor { chain ->
                        val credentials = runBlocking {
                            when (val state = userSettingsRepo.getSettings().loginState) {
                                is LoginState.LoggedIn -> state.credentials
                                LoginState.LoggedOut -> ""
                                is LoginState.LoggingIn -> state.credentials
                            }
                        }
                        val request = chain.request().newBuilder()
                            .addHeader("Authorization", "Basic $credentials")
                            .build()
                        chain.proceed(request)
                    }
                    .build()
            )
            .baseUrl(WebService.BASE_URL)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(WebService::class.java)
    }

    companion object {
        /** Singleton [UserSettingsRepository] instance. */
        lateinit var userSettingsRepo: UserSettingsRepository
        lateinit var database: AppDatabase
        lateinit var webService: WebService
    }
}
